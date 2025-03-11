package com.mnov34.CUBES4solo.api;

import lombok.Setter;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author Maël NOUVEL <br>
 * 11/03/2025
 **/
public class AuthInterceptor implements Interceptor {

    @Setter
    private static String authHeader;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        if (authHeader != null) {
            Request request = original.newBuilder()
                    .header("Authorization", authHeader)
                    .build();
            return chain.proceed(request);
        }
        return chain.proceed(original);
    }
}
