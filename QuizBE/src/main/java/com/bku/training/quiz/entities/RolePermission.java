package com.bku.training.quiz.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Entity
@Table (name = "role_permission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission extends BaseEntity{

    @ManyToOne
    private Role role;

    @ManyToOne
    private Permission permission;

}
