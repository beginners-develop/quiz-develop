package bku.beginners.core.service.impl;

import bku.beginners.core.dto.LoginRequestDto;
import bku.beginners.core.dto.LoginResponseDto;
import bku.beginners.core.entities.User;
import bku.beginners.core.repositories.UserRepository;
import bku.beginners.core.service.iface.AuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LoginResponseDto checkPermission(String token) {
        return null;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws Exception {
        if (loginRequestDto.getUserName() == null || loginRequestDto.getUserName().isEmpty()) {
            throw new Exception("Username not null");
        }
        if (loginRequestDto.getPassword() == null || loginRequestDto.getPassword().isEmpty()) {
            throw new Exception("Password not null");
        }
        Optional<User> userOptional = userRepository.findByUsernameAndIsDeletedFalse(loginRequestDto.getUserName());
        if (userOptional.isPresent() && userOptional.get().getIsDeleted().equals(Boolean.TRUE)) {
            throw new Exception("User invalid");
        }
        LoginResponseDto responseData = new LoginResponseDto();
        responseData.setJwt("jwt");
        responseData.setToken("token");
        return responseData;
    }
}
