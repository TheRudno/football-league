package pl.polsl.take.footballleague.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.take.footballleague.database.Club;
import pl.polsl.take.footballleague.database.Footballer;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FootballerDTO{
    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String nationality;
    private Club club;
    private String position;

    public static FootballerDTO from(Footballer footballer){
        return new FootballerDTO(footballer.getId(),
                footballer.getName(),
                footballer.getSurname(),
                footballer.getDateOfBirth(),
                footballer.getNationality(),
                footballer.getClub(),
                footballer.getPosition().name());
    }

    public Footballer toFootballer(){
        Footballer footballer=new Footballer();
        footballer.setId(id);
        footballer.setName(name);
        footballer.setSurname(surname);
        footballer.setDateOfBirth(dateOfBirth);
        footballer.setNationality(nationality);
        footballer.setClub(club);
        footballer.setPosition(Footballer.PlayerPosition.valueOf(position));
        return footballer;
    }

}
