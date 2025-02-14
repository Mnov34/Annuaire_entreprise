package com.mnov34.CUBES4solo.repository;

import com.mnov34.CUBES4solo.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
}
