package com.bku.training.quiz.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "role")
@JsonIgnoreProperties(value = {"members"})
public class Role extends BaseEntity{

    @Column(name = "role_name")
    private String roleName;

    private String description;

    @ManyToMany (mappedBy = "roles")
    private List<Member> members;

    @OneToMany (mappedBy = "role", cascade = CascadeType.REMOVE)
    private List<RolePermission> rolePermissionList;

    public Role (String roleName) {
        this.roleName = roleName;
    }
}
