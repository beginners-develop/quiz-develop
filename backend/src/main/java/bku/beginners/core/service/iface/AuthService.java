package bku.beginners.core.service.iface;


import bku.beginners.core.dto.LoginRequestDto;
import bku.beginners.core.dto.LoginResponseDto;

public interface AuthService {
    LoginResponseDto checkPermission(String token);
    LoginResponseDto login(LoginRequestDto dataLogin) throws Exception;
}
