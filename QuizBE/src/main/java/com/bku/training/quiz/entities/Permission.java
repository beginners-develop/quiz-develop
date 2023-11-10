package com.bku.training.quiz.entities;

import com.bku.training.quiz.enumeric.ApiMethod;
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
@Entity
@Table(name = "permission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends BaseEntity {

    private String api;

    @Column(name = "permission_name")
    private String permissionName;

    @Column(name = "permission_code")
    private String permissionCode;

    @Enumerated (EnumType.STRING)
    private ApiMethod method;

    @OneToMany (mappedBy = "permission", cascade = CascadeType.REMOVE)
    private List<RolePermission> rolePermissionList;

}
