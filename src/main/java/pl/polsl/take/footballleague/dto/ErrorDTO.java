package pl.polsl.take.footballleague.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;

@Setter
@Getter
@AllArgsConstructor
public class ErrorDTO {
    String error;

    static public ErrorDTO from(Exception exception){
        return new ErrorDTO(exception.getMessage());
    }
}
