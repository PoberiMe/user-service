package me.poberi.userservice.repository;

import jakarta.validation.constraints.NotBlank;
import me.poberi.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    User findUserByUsername(String username);

    boolean existsByUsername(@NotBlank String username);

    boolean existsByEmail(@NotBlank String email);
}
