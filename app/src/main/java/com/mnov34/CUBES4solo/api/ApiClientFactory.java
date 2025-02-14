package com.mnov34.CUBES4solo.api;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public class ApiClientFactory {
    private static final String BASE_URL = "http://localhost:8080/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    public static UserApiClient createUserApiClient() {
        return retrofit.create(UserApiClient.class);
    }

    public static EmployeeApiClient createEmployeeApiClient() {
        return retrofit.create(EmployeeApiClient.class);
    }
}
