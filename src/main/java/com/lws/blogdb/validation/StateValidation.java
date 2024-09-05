package com.lws.blogdb.validation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.io.Serializable;
public class StateValidation implements ConstraintValidator<State, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        //如果value大于128以上要切换equals方法
        return value == 0 || value == 1 ;
    }
}
