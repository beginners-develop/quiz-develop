package bku.beginners.core.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginRequestDto {
    private String userName;
    private String password;
}
