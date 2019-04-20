package pl.polsl.take.footballleague.dto;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.take.footballleague.database.Match;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class MatchListDTO {

    private List<MatchDTO> matches;

    static public MatchListDTO from(List<Match> matches){
        MatchListDTO matchListDTO = new MatchListDTO();
        matchListDTO.setMatches(matches
                .stream()
                .map(MatchDTO::from)
                .collect(Collectors.toList())
        );
        return matchListDTO;
    }

}
