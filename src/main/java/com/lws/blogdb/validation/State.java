package com.lws.blogdb.validation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StateValidation.class})
public @interface State {
    String message() default "state的值只能是0或者1";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
