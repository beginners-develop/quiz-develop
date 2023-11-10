package com.bku.training.quizfe.service.impl;

import com.bku.training.quizfe.entities.ForgetPasswordDTO;
import com.bku.training.quizfe.entities.LoginResponse;
import com.bku.training.quizfe.entities.RegisterDTO;
import com.bku.training.quizfe.service.LoginService;
import com.bku.training.quizfe.utils.ServerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Get login response information
     */
    @Override
    public LoginResponse getLoginResponse(String api, String username, String password) {
        String url = ServerUtils.DOMAIN + api;
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        return restTemplate.postForEntity(url, map, LoginResponse.class).getBody();
    }

    /**
     * register new account
     */
    @Override
    public String registerAccount(String api, RegisterDTO registerDTO) {
        return restTemplate.postForEntity(ServerUtils.DOMAIN + api, registerDTO, String.class).getBody();
    }

    /**
     *  request generate password and send to user's email
     */
    @Override
    public String forgetPassword(String api, ForgetPasswordDTO forgetPasswordDTO) {
        return restTemplate.postForEntity(ServerUtils.DOMAIN + api, forgetPasswordDTO, String.class).getBody();
    }

    /**
     * Verify new account
     */
    @Override
    public String verifyAccount(String api, String requestToken) {
        return restTemplate.postForEntity(ServerUtils.DOMAIN + api, requestToken, String.class).getBody();
    }
}
