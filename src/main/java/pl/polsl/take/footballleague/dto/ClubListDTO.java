package pl.polsl.take.footballleague.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.take.footballleague.database.Club;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
public class ClubListDTO {
    List<ClubDTO> clubs;

    public static ClubListDTO from(List<Club> clubList){
        return new ClubListDTO(clubList
                .stream()
                .map(ClubDTO::from)
                .collect(Collectors.toList())
        );
    }
}
