package pl.polsl.take.footballleague.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.take.footballleague.exceptions.ElementValidationException;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    List<String> errors;

    static public ErrorDTO from(Exception exception){
        return new ErrorDTO(List.of(exception.getMessage()));
    }

    static public ErrorDTO from(ElementValidationException exception){
        return new ErrorDTO(exception.getViolations());
    }
}
