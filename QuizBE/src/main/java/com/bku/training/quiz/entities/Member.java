package com.bku.training.quiz.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Entity
@Table (name = "member")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity{

    private String username;

    private String password;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    private String email;

    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Lob
    private byte[] avatar;

    @Column(name = "is_activated")
    private boolean isActivated;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "number_failed_login")
    private int failedLogin;

    @Column(name = "lock_time")
    private Date lockTime;

    @ManyToMany
    private List<Role> roles;

    @OneToMany (mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Quiz> quizzes;

    @OneToMany (mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Score> scores;

    @Override
    public String toString() {
        return "Member{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", isActivated=" + isActivated +
                ", accountNonLocked=" + accountNonLocked +
                ", failedLogin=" + failedLogin +
                ", lockTime=" + lockTime +
                '}';
    }
}
