package com.mnov34.CUBES4solo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Configuration
public class RetrofitConfig {
    private static final String BASE_URL = "http://localhost:8080/";

    @Bean
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

}
