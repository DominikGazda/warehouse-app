package com.warehouse.client.validator;

import com.warehouse.client.constraint.PhoneNumberFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberFormatter, String> {
    @Override
    public void initialize(PhoneNumberFormatter constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String pattern = "^\\d{3}-\\d{3}-\\d{3}$";
        return Pattern.matches(pattern, s);
    }
}
