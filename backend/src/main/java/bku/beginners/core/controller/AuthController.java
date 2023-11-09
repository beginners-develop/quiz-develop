package bku.beginners.core.controller;

import bku.beginners.core.dto.LoginRequestDto;
import bku.beginners.core.dto.LoginResponseDto;
import bku.beginners.core.service.iface.AuthService;


import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/auth")
@Api(value = "Auth API")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginRequestDto dataLogin) {
        try {
            LoginResponseDto data = authService.login(dataLogin);
        } catch (Exception ex)  {
            LOGGER.warn(ex.getMessage());
        }
        return null;
    }

    @PostMapping("/checkPermission")
    public ResponseEntity<?> checkPermission(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginResponseDto dataLogin) {
        try {
            String token = dataLogin.getToken();
            LoginResponseDto data = authService.checkPermission(token);
        } catch (Exception ex) {
            LOGGER.warn(ex.getMessage());
        }
        return null;
    }
}
