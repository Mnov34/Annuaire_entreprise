package com.mnov34.CUBES4solo.controller;

import com.mnov34.CUBES4solo.dto.UserDto;
import com.mnov34.CUBES4solo.model.ForgotPasswordRequest;
import com.mnov34.CUBES4solo.model.ResetPasswordRequest;
import com.mnov34.CUBES4solo.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication endpoints for login and logout.
 *
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody UserDto userDto) {
        return authService.login(userDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody UserDto userDto) {
        authService.logout(userDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Commence le processus de récupération de mot de passe")
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        authService.forgotPassword(request.getEmail());
        return ResponseEntity.ok("Si l'adresse email existe, un lien de réinitialisation a été envoyé.");
    }

    @Operation(summary = "Changer le mot de passe en utilisant un token")
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest newPasswordRequest) {
        authService.resetPassword(newPasswordRequest);
        return ResponseEntity.ok("Le mot de passe a été réinitialisé avec succès.");    }
}
