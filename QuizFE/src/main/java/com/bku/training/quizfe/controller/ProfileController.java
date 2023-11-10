package com.bku.training.quizfe.controller;

import com.bku.training.quizfe.entities.DeactivateDTO;
import com.bku.training.quizfe.entities.LoginResponse;
import com.bku.training.quizfe.entities.MemberDTO;
import com.bku.training.quizfe.entities.PasswordDTO;
import com.bku.training.quizfe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Controller
@RequestMapping("/setting")
@SessionAttributes("globalMember")
public class ProfileController extends BaseController{

    @Autowired
    private MemberService memberService;

    /**
     * go to information page to update user's information
     */
    @GetMapping("/information")
    public ModelAndView showInformationPage(@ModelAttribute("globalMember") LoginResponse loginResponse) {
        ModelAndView modelAndView = new ModelAndView("setting/information");
        if (loginResponse == null) {
            modelAndView.addObject("memberObject", new MemberDTO());
        } else {
            modelAndView.addObject("memberObject", loginResponse.getMemberDTO());
        }
        return modelAndView;
    }

    /**
     * Update information of member
     */
    @PostMapping("/updateInfo")
    public ModelAndView updateInformation(@ModelAttribute("memberObject") MemberDTO inputMemberDTO
            , @RequestParam(value = "profile_avatar", required = false) MultipartFile multipartFile
            , @SessionAttribute("globalMember") LoginResponse loginResponse) throws IOException {
        ModelAndView modelAndView = new ModelAndView("setting/information");
        // check if the user input a new avatar?
        if (!multipartFile.isEmpty()) {
            inputMemberDTO.setAvatar(multipartFile.getBytes());
        }
        MemberDTO memberDTO = memberService.updateMember(
                "user/update-info", inputMemberDTO, loginResponse);
        modelAndView.addObject("memberObject", memberDTO);
        // Store globalMember into session
        loginResponse.setMemberDTO(memberDTO);
        modelAndView.addObject("globalMember", loginResponse);
        return modelAndView;
    }

    /**.
     * show change password page
     */
    @GetMapping("/change-password")
    public ModelAndView showChangePasswordPage(@ModelAttribute("globalMember") LoginResponse loginResponse) {
        ModelAndView modelAndView = new ModelAndView("setting/change-password");
        if (loginResponse == null) {
            modelAndView.addObject("memberObject", new MemberDTO());
        } else {
            modelAndView.addObject("memberObject", loginResponse.getMemberDTO());
        }
        return modelAndView;
    }

    /**
     * change user's password
     */
    @PostMapping("/changePassword")
    public ModelAndView doChangePassword(@SessionAttribute("globalMember") LoginResponse loginResponse
            , @RequestParam("currentPassword") String currentPassword
            , @RequestParam("newPassword") String newPassword
            , @RequestParam("confirmPassword") String confirmPassword
            , RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/setting/change-password");
        PasswordDTO passwordDTO = new PasswordDTO(
                loginResponse.getMemberDTO().getId(), currentPassword, newPassword, confirmPassword);
        try {
            String message = memberService.updatePassword(
                "user/update-password", passwordDTO, loginResponse);
            redirectAttributes.addFlashAttribute("successMessage", message);
        } catch (HttpStatusCodeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getResponseBodyAsString());
        }
        return modelAndView;
    }

    /**
     * Display deactivate account page
     */
    @GetMapping("/deactivate-account")
    public ModelAndView showDeactivateAccountPage(@ModelAttribute("globalMember") LoginResponse loginResponse) {
        ModelAndView modelAndView = new ModelAndView("setting/deactivate-account");
        if (loginResponse == null) {
            modelAndView.addObject("memberObject", new MemberDTO());
        } else {
            modelAndView.addObject("memberObject", loginResponse.getMemberDTO());
        }
        return modelAndView;
    }

    /**
     * deactivate account of member
     */
    @PostMapping("/deactivateAccount")
    public ModelAndView deactivateAccount(@SessionAttribute("globalMember") LoginResponse loginResponse
            , @RequestParam("g-recaptcha-response") String reCaptcha
            , SessionStatus sessionStatus
            , RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/setting/deactivate-account");
        DeactivateDTO deactivateDTO = new DeactivateDTO(
                loginResponse.getMemberDTO().getId(), reCaptcha);
        try {
            String message = memberService.deactivateMember(
                    "user/deactivate-account", deactivateDTO, loginResponse);
            redirectAttributes.addFlashAttribute("errorMessage", message);
            // Mark the session is completed
            sessionStatus.setComplete();
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        } catch (HttpStatusCodeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid reCaptcha");
        }
        return modelAndView;
    }
}
