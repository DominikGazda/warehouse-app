package com.warehouse.order.constraint;

import com.warehouse.order.validator.DateTimeFormatterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateTimeFormatterValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateTimeFormatterConstraint{
    String message() default "Wrong date expression, or past date provided (YYYY-MM-DD HH:MM)";
    Class<?>[]groups() default {};
    Class<? extends Payload> [] payload() default {};
}
