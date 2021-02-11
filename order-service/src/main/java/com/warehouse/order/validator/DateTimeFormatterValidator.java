package com.warehouse.order.validator;

import com.warehouse.order.constraint.DateTimeFormatterConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterValidator implements ConstraintValidator<DateTimeFormatterConstraint, String> {
    
    
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime presentTime = LocalDateTime.now();
        try{
            LocalDateTime dateFromString = LocalDateTime.parse(s, formatter);
            LocalDateTime.of(dateFromString.toLocalDate(), dateFromString.toLocalTime());
            System.out.println(dateFromString.toString());
            if(dateFromString.isBefore(presentTime))
                return false;
        } catch (DateTimeException e){
            return false;
        }
        return true;
    }
}
