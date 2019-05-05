package pl.polsl.take.footballleague.dto;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.take.footballleague.database.Footballer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
public class FootballerListDTO {
    private Set<FootballerDTO> footballers;

    static public FootballerListDTO from(Set<Footballer> footballers){
        FootballerListDTO footballerListDTO = new FootballerListDTO();
        footballerListDTO.setFootballers(footballers
                .stream()
                .map(FootballerDTO::from)
                .collect(Collectors.toSet())
        );
        return footballerListDTO;
    }
}
