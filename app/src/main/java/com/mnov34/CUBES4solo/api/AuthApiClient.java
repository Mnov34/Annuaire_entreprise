package com.mnov34.CUBES4solo.api;

import com.mnov34.CUBES4solo.dto.UserDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface AuthApiClient {
    @POST("api/auth/login")
    Call<UserDto> login(@Body UserDto userDto);

    @POST("api/auth/register")
    Call<UserDto> register(@Body UserDto userDto);
}
