package com.bku.training.quizfe.service;

import com.bku.training.quizfe.entities.ForgetPasswordDTO;
import com.bku.training.quizfe.entities.LoginResponse;
import com.bku.training.quizfe.entities.RegisterDTO;

/**
 * @author Nam Tran
* @project Quiz
 **/
public interface LoginService {

    LoginResponse getLoginResponse(String api, String username, String password);
    String registerAccount(String api, RegisterDTO registerDTO);
    String forgetPassword(String api, ForgetPasswordDTO forgetPasswordDTO);
    String verifyAccount(String api, String requestToken);

}
