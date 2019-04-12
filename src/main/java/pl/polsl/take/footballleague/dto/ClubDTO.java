package pl.polsl.take.footballleague.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.take.footballleague.database.Club;

import static pl.polsl.take.footballleague.utils.Utils.consumeIfNonNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClubDTO {
    private Long id;
    private String name;
    private String city;
    private String country;

    public static ClubDTO from(Club club){
        return new ClubDTO(
                club.getId(),
                club.getName(),
                club.getCity(),
                club.getCountry()
        );
    }

    public Club toClub(){
        Club club = new Club();
        club.setId(id);
        club.setCountry(country);
        club.setCity(city);
        club.setName(name);
        return club;
    }

    public Club mergeWith(Club club){
        consumeIfNonNull(club::setName,name);
        consumeIfNonNull(club::setCity,city);
        consumeIfNonNull(club::setCountry,country);
        return club;
    }





}
