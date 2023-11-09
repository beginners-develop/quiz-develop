package bku.beginners.core.repositories;

import bku.beginners.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUuid(String id);

    Optional<User> findByUsernameAndIsDeletedFalse(String userName);

    Optional<User> findByUsernameOrEmail(String userName, String email);

    Optional<User> findByUsername(String userName);

    Optional<User> findByEmail(String email);
}
