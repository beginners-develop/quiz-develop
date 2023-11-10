package com.bku.training.quiz.rest;

import com.bku.training.quiz.entities.Member;
import com.bku.training.quiz.mapper.MemberMapper;
import com.bku.training.quiz.services.MemberService;
import com.bku.training.quiz.services.ReCaptchaService;
import com.bku.training.quiz.dto.DeactivateDTO;
import com.bku.training.quiz.dto.MemberDTO;
import com.bku.training.quiz.dto.PasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nam Tran
 * @project QuizBE
 **/
@RestController
@RequestMapping("/api/user")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReCaptchaService reCaptchaService;

    /**
     * Update information of member
     */
    @PutMapping("/update-info")
    public ResponseEntity<?> updateInformation(@RequestBody MemberDTO memberDTO) {
        Member member = memberService.updateInfoMember(memberMapper.dtoToEntity(memberDTO));
        return ResponseEntity.ok(memberMapper.entityToDto(member));
    }

    /**
     * Update password of member
     */
    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody PasswordDTO passwordDTO) {
        Member member = memberService.getMemberById(passwordDTO.getId());
        if (passwordEncoder.matches(passwordDTO.getCurrentPassword(), member.getPassword())) {
            if (passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword())) {
                member.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
                memberService.updateMember(member);
                return ResponseEntity.ok("Change password completed");
            } else {
                return ResponseEntity.badRequest().body("Confirm password is not match");
            }
        }
        return ResponseEntity.badRequest().body("Current password is not correct");
    }

    /**
     * deactivate member's account
     */
    @PutMapping("/deactivate-account")
    public ResponseEntity<?> deactivateAccount(@RequestBody DeactivateDTO deactivateDTO) {
        Member member = memberService.getMemberById(deactivateDTO.getId());
        if (reCaptchaService.validateReCaptcha(deactivateDTO.getReCaptcha())) {
            member.setActivated(false);
            memberService.updateMember(member);
            return ResponseEntity.ok("Your account has been deactivated!");
        }
        return ResponseEntity.badRequest().build();
    }
}
