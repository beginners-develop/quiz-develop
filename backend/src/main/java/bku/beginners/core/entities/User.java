package bku.beginners.core.entities;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String uuid;

    @Column(name = "username")
    private String username;

    private String code;

    @Column(name = "user_type")
    private String userType;

    private String dob;

    private String email;

    private String password;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
