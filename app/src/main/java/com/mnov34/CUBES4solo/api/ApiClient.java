package com.mnov34.CUBES4solo.api;

import com.mnov34.CUBES4solo.model.Department;
import com.mnov34.CUBES4solo.model.Employee;
import com.mnov34.CUBES4solo.model.Site;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface ApiClient {
    @GET("/api/employees")
    Call<List<Employee>> getEmployees(
            @Query("search") String search,
            @Query("site") String site,
            @Query("service") String department
    );

    @GET("/api/sites")
    Call<List<Site>> getSites();

    @GET("/api/sites/{id}")
    Call<Site> getSiteById(@Path("id") String id);

    @GET("/api/sites/{name}")
    Call<Site> getSiteByName(@Path("name") String name);

    @GET("/api/services")
    Call<List<Department>> getServices();

    @POST("/api/employees")
    Call<Employee> createEmployee(@Body Employee employee);

    @PUT("/api/employees/{id}")
    Call<Employee> updateEmployee(@Path("id") Long id, @Body Employee employee);

    @DELETE("/api/employees/{id}")
    Call<Void> deleteEmployee(@Path("id") Long id);
}
