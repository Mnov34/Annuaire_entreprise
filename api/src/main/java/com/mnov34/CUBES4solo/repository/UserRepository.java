package com.mnov34.CUBES4solo.repository;

import com.mnov34.CUBES4solo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
