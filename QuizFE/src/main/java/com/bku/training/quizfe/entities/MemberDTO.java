package com.bku.training.quizfe.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDTO {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private byte[] avatar;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private List<String> roles;

    /**
     * Convert byte[] data (image stored in database) to String Base64 to display in view
     * Because thymeleaf engine doesn't approve byte[] data
     */
    public String convertImageToString() {
        String encode = "";
        try {
            encode = Base64.getMimeEncoder().encodeToString(this.getAvatar());
        } catch (Exception e) {
            e.getStackTrace();
        }
        return encode;
    }
}
