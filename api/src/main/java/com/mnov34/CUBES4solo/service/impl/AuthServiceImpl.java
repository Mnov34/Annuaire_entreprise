package com.mnov34.CUBES4solo.service.impl;

import com.mnov34.CUBES4solo.dto.UserDto;
import com.mnov34.CUBES4solo.mapper.EmployeeMapper;
import com.mnov34.CUBES4solo.mapper.UserMapper;
import com.mnov34.CUBES4solo.model.PasswordResetToken;
import com.mnov34.CUBES4solo.model.ResetPasswordRequest;
import com.mnov34.CUBES4solo.model.User;
import com.mnov34.CUBES4solo.repository.PasswordResetTokenRepository;
import com.mnov34.CUBES4solo.repository.UserRepository;
import com.mnov34.CUBES4solo.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final UserMapper userMapper;
    private final EmployeeMapper employeeMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private JavaMailSender mailSender;


    public AuthServiceImpl(UserRepository userRepository,
                           PasswordResetTokenRepository tokenRepository,
                           UserMapper userMapper,
                           EmployeeMapper employeeMapper,
                           BCryptPasswordEncoder passwordEncoder,
                           JavaMailSender mailSender) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.employeeMapper = employeeMapper;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto login(UserDto userDto) {
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());

        if (user.isPresent() && !passwordEncoder.matches(userDto.getPassword(), user.get().getPassword()))
            throw new RuntimeException("Invalid username or password.");

        UserDto dto = new UserDto();
        dto.setId(user.get().getId());
        dto.setEmail(user.get().getEmail());
        dto.setEmployee(employeeMapper.toDto(user.get().getEmployee()));
        dto.setPassword(null);

        return dto;
    }

    @Override
    public void logout(UserDto userDto) {
        SecurityContextHolder.clearContext();
        log.info("L'utilisateur \"{}\" s'est déconnecté avec succès.", userDto.getEmployee().getFirstAndLastName());
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(resetPasswordRequest.getToken());

        if (tokenOpt.isEmpty()) {
            throw new RuntimeException("Token de réinitialisation invalide");
        }

        PasswordResetToken resetToken = tokenOpt.get();
        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Le token de réinitialisation a expiré");
        }

        User user = resetToken.getUser();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(resetPasswordRequest.getNewPassword()));
        userRepository.save(user);

        tokenRepository.delete(resetToken);
    }

    @Override
    public void forgotPassword(String email) {
        createPasswordResetToken(email);
    }

    private PasswordResetToken createPasswordResetToken(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String token = UUID.randomUUID().toString();

            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setToken(token);
            resetToken.setUser(user);
            resetToken.setExpiryDate(LocalDateTime.now().plusHours(24));

            tokenRepository.save(resetToken);

            SimpleMailMessage emailMessage = buildEmail(token, user);
            try {
                mailSender.send(emailMessage);
            } catch (MailException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private static SimpleMailMessage buildEmail(String token, User user) {
        String resetUrl = "http://localhost:8080/reset-password?token=" + token;
        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setTo(user.getEmail());
        emailMessage.setSubject("Réinitialisation de votre mot de passe");
        emailMessage.setText("Pour réinitialiser votre mot de passe, cliquez sur le lien suivant :\n"
                + resetUrl +
                "\nCe lien a une validité de 24h, au delà de cette limite, " +
                "il faudra refaire une demande de réinitialisation de mot de passe");
        return emailMessage;
    }
}
