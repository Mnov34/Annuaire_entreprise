package com.mnov34.CUBES4solo.api;

import com.mnov34.CUBES4solo.dto.UserDto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface UserApiClient {
    @GET("api/users")
    Call<List<UserDto>> getUsers();

    @GET("api/users/{id}")
    Call<UserDto> getUser(@Path("id") Long id);

    @GET("/api/employee/search")
    Call<List<UserDto>> searchEmployees(
            @Query("firstName") String firstName,
            @Query("lastName") String lastName,
            @Query("site") String site,
            @Query("service") String service,
            @Query("email") String email
    );

    @POST("api/users")
    Call<UserDto> createUser(@Body UserDto userDto);

    @PUT("api/users/{id}")
    Call<UserDto> updateUser(
            @Path("id") Long id,
            @Body UserDto userDto
    );


    @DELETE("api/users/{id}")
    Call<Void> deleteUser(@Path("id") Long id);
}
