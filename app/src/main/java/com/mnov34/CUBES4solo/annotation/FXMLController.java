package com.mnov34.CUBES4solo.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Documented
public @interface FXMLController {
}
