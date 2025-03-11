package com.mnov34.CUBES4solo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Maël NOUVEL <br>
 * 11/03/2025
 **/
public class PasswordEncoderTest {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("alice@"));
        System.out.println(new BCryptPasswordEncoder().encode("bob@"));
        System.out.println(new BCryptPasswordEncoder().encode("carol@"));
        System.out.println(new BCryptPasswordEncoder().encode("dave@"));
        System.out.println(new BCryptPasswordEncoder().encode("eve@"));
        System.out.println(new BCryptPasswordEncoder().encode("root"));
    }
}


