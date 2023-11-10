package com.bku.training.quiz.repositories;

import com.bku.training.quiz.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(String name);
}
