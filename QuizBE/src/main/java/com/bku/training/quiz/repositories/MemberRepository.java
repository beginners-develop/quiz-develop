package com.bku.training.quiz.repositories;

import com.bku.training.quiz.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByUsername(String username);

    Optional<Member> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE Member m SET m.failedLogin = :failed WHERE m.username = :username")
    void updateNumberOfFailedLogin(@Param("failed") int failedLogin, @Param("username") String username);

}
