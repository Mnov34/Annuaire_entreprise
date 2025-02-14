package com.mnov34.CUBES4solo.service;

import com.mnov34.CUBES4solo.dto.UserDto;
import com.mnov34.CUBES4solo.model.ResetPasswordRequest;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface AuthService {
    UserDto login(UserDto userDto);

    void logout(UserDto userDto);

    void forgotPassword(String email);

    void resetPassword(ResetPasswordRequest newPasswordRequest);
}
