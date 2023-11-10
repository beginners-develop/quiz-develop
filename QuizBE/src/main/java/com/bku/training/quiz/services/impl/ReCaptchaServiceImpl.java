package com.bku.training.quiz.services.impl;

import com.bku.training.quiz.entities.recaptcha.ReCaptchaResponse;
import com.bku.training.quiz.services.ReCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReCaptchaServiceImpl implements ReCaptchaService {

    // Get value from environment application.properties
    @Value("${recaptcha.key.secret}")
    private String keySecret;

    // Get value from environment application.properties
    @Value("${recaptcha.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Validate ReCaptcha Google V2
     */
    @Override
    public boolean validateReCaptcha(String gRecaptchaResponse) {
        String param = "?secret=" + this.keySecret + "&response=" + gRecaptchaResponse;
        ReCaptchaResponse reCaptchaResponse =
                restTemplate.exchange(url + param, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();
        return reCaptchaResponse.isSuccess();
    }
}
