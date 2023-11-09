package bku.beginners.core.dto;

import lombok.Data;

@Data
public class UserProfileDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private long creationTime;
    private long lastAccessTime;
}
