package com.warehouse.client.constraint;

import com.warehouse.client.validator.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberFormatter{
    String message() default "Wrong phone number expression (000-000-000)";
    Class<?>[]groups() default {};
    Class<? extends Payload> [] payload() default {};
}
