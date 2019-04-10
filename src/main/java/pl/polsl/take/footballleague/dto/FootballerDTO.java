package pl.polsl.take.footballleague.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.take.footballleague.database.Club;
import pl.polsl.take.footballleague.database.Footballer;

import javax.inject.Inject;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FootballerDTO{
    private Long id;

    @NotNull
    @Pattern(regexp = "[A-Za-z]{2,}")
    private String name;
    @NotNull
    @Pattern(regexp = "[A-Za-z]{2,}")
    private String surname;
    @JsonbDateFormat("yyyy-MM-dd")
    @NotNull
    @Pattern(regexp = "[A-Za-z]{2,}")
    private LocalDate dateOfBirth;
    @NotNull
    @Pattern(regexp = "[A-Za-z]{2,}")
    private String nationality;
    @NotNull
    @Pattern(regexp = "[A-Za-z]{2,}")
    private Club club;
    @NotNull
    @Pattern(regexp = "[A-Za-z]{2,}")
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
        if(position != null){
            try{
                footballer.setPosition(Footballer.PlayerPosition.valueOf(position));
            }catch(Exception ignored){
            }
        }
        return footballer;
    }

}
