package pl.polsl.take.footballleague.exceptions;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ElementValidationException extends Exception{
    @Getter
    List<String> violations;
    public <T> ElementValidationException(Set<ConstraintViolation<T>> violations){
        this.violations = new LinkedList<>();
        for(ConstraintViolation violation : violations){
            this.violations.add(violation.getPropertyPath() + ": " + violation.getMessage());
        }
    }
}
