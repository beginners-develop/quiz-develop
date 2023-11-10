package com.bku.training.quizfe.utils;

import com.bku.training.quizfe.entities.LoginResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.Arrays;

public class HeaderUtils {

    public static <T> HttpEntity<T> getHeader(T object, LoginResponse loginResponse) {
        if (loginResponse == null) {
            loginResponse = new LoginResponse();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", loginResponse.getType() + " " + loginResponse.getToken());
        return new HttpEntity<>(object, headers);
    }
}
