package com.bku.training.quiz.controller;

import com.bku.training.quiz.entities.Member;
import com.bku.training.quiz.services.MemberService;
import com.bku.training.quiz.services.ReCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Controller
@RequestMapping("/setting")
@SessionAttributes("memberAccount")
public class ProfileController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReCaptchaService reCaptchaService;

    @GetMapping("/information")
    public ModelAndView informationPage(@ModelAttribute("memberAccount") Member member) {
        return new ModelAndView("setting/information", "memberObject", member);
    }

    @PostMapping("/updateInfo")
    public ModelAndView saveInfoOfMember(@ModelAttribute("memberObject") Member memberObject
            , @RequestParam("profile_avatar") MultipartFile multipartFile) throws IOException {
        memberObject.setAvatar(multipartFile.getBytes());
        Member member = memberService.updateInfoMember(memberObject);
        return new ModelAndView("setting/information", "memberObject", member);
    }

    @GetMapping("/change-password")
    public ModelAndView changePasswordPage(@ModelAttribute("memberAccount") Member member) {
        return new ModelAndView("setting/change-password", "memberObject", member);
    }

    @PostMapping("/changePassword")
    public ModelAndView doChangePassword(@SessionAttribute("memberAccount") Member member
            , @RequestParam("currentPassword") String currentPassword
            , @RequestParam("newPassword") String newPassword
            , @RequestParam("confirmPassword") String confirmPassword
            , RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("setting/change-password");
        if (passwordEncoder.matches(currentPassword, member.getPassword())) {
            if (newPassword.equals(confirmPassword)) {
                member.setPassword(passwordEncoder.encode(newPassword));
                memberService.updateMember(member);
                redirectAttributes.addFlashAttribute("successMessage", "Change password successfully");
                modelAndView.setViewName("redirect:/logoutSuccessful");
            } else {
                modelAndView.addObject("errorMessage", "Confirm password is not match");
            }
        } else {
            modelAndView.addObject("errorMessage", "Current password is not match");
        }
        modelAndView.addObject("memberObject", member);
        return modelAndView;
    }

    @GetMapping("/deactivate-account")
    public ModelAndView deactivateAccountPage(@ModelAttribute("memberAccount") Member member) {
        return new ModelAndView("setting/deactivate-account", "memberObject", member);
    }

    @PostMapping("/deactivateAccount")
    public ModelAndView deactivateAccount(@SessionAttribute("memberAccount") Member member
            , @RequestParam("g-recaptcha-response") String reCaptcha
            , RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("setting/deactivate-account");
        if (reCaptchaService.validateReCaptcha(reCaptcha)) {
            member.setActivated(false);
            memberService.updateMember(member);
            redirectAttributes.addFlashAttribute("errorMessage", "You're deactivated your account!");
            modelAndView.setViewName("redirect:/logoutSuccessful");
        } else {
            modelAndView.addObject("errorMessage", "Invalid reCaptcha");
        }
        modelAndView.addObject("memberObject", member);
        return modelAndView;
    }

    @ModelAttribute("userDetails")
    public UserDetails userDetails(Principal principal){
        return (UserDetails) ((Authentication) principal).getPrincipal();
    }

    @ModelAttribute("memberAccount")
    public Member memberAccount(@ModelAttribute("userDetails") UserDetails userDetails) {
        return memberService.getMemberByUsername(userDetails.getUsername());
    }

}
