package com.mnov34.CUBES4solo.api;

import com.mnov34.CUBES4solo.dto.ServiceDto;
import com.mnov34.CUBES4solo.dto.UserDto;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface ServiceApiClient {
    @GET("api/services")
    Call<List<ServiceDto>> getAllServices();
}
