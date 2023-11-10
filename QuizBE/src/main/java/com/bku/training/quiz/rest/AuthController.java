package com.bku.training.quiz.rest;

import com.bku.training.quiz.dto.*;
import com.bku.training.quiz.entities.Member;
import com.bku.training.quiz.entities.Token;
import com.bku.training.quiz.exception.ValidateException;
import com.bku.training.quiz.mapper.MemberMapper;
import com.bku.training.quiz.services.EmailService;
import com.bku.training.quiz.services.MemberService;
import com.bku.training.quiz.services.ReCaptchaService;
import com.bku.training.quiz.services.TokenService;
import com.bku.training.quiz.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Nam Tran
 * @project QuizBE
 **/
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private MemberService memberService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ReCaptchaService reCaptchaService;

    @Autowired
    private MemberMapper memberMapper;

    /**
     * Authenticate when user has logged in successfully
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDto) {
        // validate user and authenticate -> throw exception if there is anything error
        Authentication authentication = validateLogin(loginDto);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Member member = memberService.getMemberByUsername(loginDto.getUsername());
        if (member.getFailedLogin() > 0) {
            memberService.resetFailedLogin(member);
        }
        MemberDTO memberDTO = memberMapper.entityToDto(member);
        return ResponseEntity.ok( // Generate JWT token and response to FE
                new JwtResponse(jwtUtils.generateJwtToken(authentication), memberDTO));
    }

    /**
     * Register new account
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@Validated @RequestBody RegisterDTO registerDTO
            , BindingResult bindingResult) throws Exception {
        // Throw exception if there is any error
        if (bindingResult.hasErrors()) {
            throw new ValidateException(bindingResult.getFieldError().getDefaultMessage());
        }
        // Validate reCaptcha
        if (reCaptchaService.validateReCaptcha(registerDTO.getReCaptcha())) {
            Member member = memberService.addNewMember(memberMapper.registerDTOTOEntity(registerDTO));
            if (member == null) {
                return ResponseEntity.badRequest().body("CREATE MEMBER IS FAILED");
            } else {
                // Create and generate token and then send to user's email to verify account
                Token token = new Token(member);
                tokenService.addToken(token);
                String content = "Demo + token = " + token.getToken() + "<br>" +
                        "<b>http://localhost:8080/quiz/verify-account?token=" + token.getToken() + "</b><br>";
//                emailService.sendEmail(member.getEmail(), "CONGRATULATION: REGISTER NEW ACCOUNT SUCCESS", content);
                return ResponseEntity.ok("OK");
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid Google reCaptcha");
        }
    }

    /**
     * handle forget password
     */
    @PostMapping("/forget-password")
    public ResponseEntity<?> forgetPassword(@Validated @RequestBody ForgetPasswordDTO forgetPasswordDTO
            , BindingResult bindingResult) throws Exception {
        // Throw exception if there is any error
        if (bindingResult.hasErrors()) {
            throw new ValidateException(bindingResult.getFieldError().getDefaultMessage());
        }
        // Validate google reCaptcha
        if (reCaptchaService.validateReCaptcha(forgetPasswordDTO.getReCaptcha())) {
            Member member = memberService.getMemberByEmail(forgetPasswordDTO.getEmail());
            // Generate new password
            String newPassword = generatePassword(8);
            member.setPassword(passwordEncoder.encode(newPassword));
            memberService.updateMember(member);
            String content = "This is your new password <b>" + newPassword + "</b> Please change your password.";
//            emailService.sendEmail(member.getEmail()
//                    , "A REQUEST FORGET PASSWORD", content);
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.badRequest().body("Invalid Google reCaptcha");
        }
    }

    /**
     * Verify user's account
     */
    @PostMapping("/verify-account")
    public ResponseEntity<?> verifyAccount(@RequestBody String requestToken) {
        Token token = tokenService.getByToken(requestToken);
        Member member = memberService.getMemberByEmail(token.getMember().getEmail());
        // validate token is out of date?
        if (validateToken(token)) {
            member.setActivated(true);
            memberService.updateMember(member);
            tokenService.deleteToken(token);
            return ResponseEntity.ok("Account " + member.getUsername() + " is verified completely");
            // Invalid token, so we have to delete token and member who didn't verify account
        } else {
            tokenService.deleteToken(token);
            memberService.deleteMember(member);
            return ResponseEntity.badRequest().body("The token has broken! Please register new account");
        }
    }

    /**
     * Validate user before authentication
     */
    private Authentication validateLogin(LoginDTO loginDTO) {
        // get User details, if the username is not exist -> throw UsernameNotFoundException
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());
        Member member = memberService.getMemberByUsername(userDetails.getUsername());
        // validate password
        if (!passwordEncoder.matches(loginDTO.getPassword(), userDetails.getPassword())) {
            // check number of failed login
            if (member.getFailedLogin() < memberService.MAX_FAILED_LOGIN - 1) {
                memberService.increaseFailedLogin(member); // increase failed
                int times = memberService.MAX_FAILED_LOGIN - member.getFailedLogin() - 1;
                throw new BadCredentialsException("The Username or Password is not correct! " +
                        "You have " + times + " times left to login");
                // locked member if number of failed login is equals or greater than {MAX_FAILED_LOGIN}
            } else {
                memberService.lockMember(member);
                String minutes = new SimpleDateFormat("mm").format(memberService.LOCK_TIME);
                throw new LockedException("You're failed to login " + memberService.MAX_FAILED_LOGIN + " times" +
                        ". Please try again after " + minutes + " minutes");
            }
            // throw exception if the user didn't activate account;
//        } else if (!member.isActivated()) {
//            throw new DisabledException("Please activate your account");
            // checking the user's account is locked or not
        } else if (member.isAccountNonLocked()) {
            // unlocked user's account
            if (memberService.unlockWhenOutOfLockTime(member)) {
                throw new LockedException("Your account has been unlocked. Please try again");
            }
            throw new LockedException("Please wait for your account is unlocked");
        }
        // authenticate user if everything is ok
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
    }

    /**
     * Generate new password with requirement is 1 digit, 1 lower, 1 upper and 1 special character
     */
    private String generatePassword(int length) {
        // Create a list of array characters for generate password
        char[] symbols = "!@#$%^&*()".toCharArray();
        char[] lowerCase = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] digits = "0123456789".toCharArray();
        char[] allCharacter = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        Random rand = new SecureRandom();

        char[] password = new char[length];
        // Random character for array password. At least 1 lower, 1 upper, 1 digit, 1 special character
        password[0] = lowerCase[rand.nextInt(lowerCase.length)];
        password[1] = upperCase[rand.nextInt(upperCase.length)];
        password[2] = digits[rand.nextInt(digits.length)];
        password[3] = symbols[rand.nextInt(symbols.length)];

        // The rest of password with random characters
        for (int i = 4; i < length; i++) {
            password[i] = allCharacter[rand.nextInt(allCharacter.length)];
        }

        // Shuffle the characters of the array password
        for (int i = 0; i < password.length; i++) {
            int randPosition = rand.nextInt(password.length);
            char temp = password[i];
            password[i] = password[randPosition];
            password[randPosition] = temp;
        }

        return new String(password);
    }

    /**
     * Validate expire token
     * Make sure token is always valid
     */
    private Boolean validateToken(Token token) {
        Date now = new Date();
        return now.before(token.getExpireDate());
    }
}
