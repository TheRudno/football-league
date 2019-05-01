package pl.polsl.take.footballleague.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.take.footballleague.database.Footballer;
import pl.polsl.take.footballleague.exceptions.ConversionException;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import java.time.LocalDate;

import static pl.polsl.take.footballleague.utils.Utils.consumeIfNonNull;
import static pl.polsl.take.footballleague.utils.Utils.convertAndConsumeIfNonNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FootballerDTO{

    @JsonbProperty
    private Long id;

    @JsonbProperty
    private String name;

    @JsonbProperty
    private String surname;

    @JsonbProperty
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @JsonbProperty
    private String nationality;

    @JsonbProperty(nillable = true)
    private Long clubId;

    @JsonbProperty
    private String position;

    public static FootballerDTO from(Footballer footballer){
        return new FootballerDTO(footballer.getId(),
                footballer.getName(),
                footballer.getSurname(),
                footballer.getDateOfBirth(),
                footballer.getNationality(),
                (footballer.getClub()!=null)?footballer.getClub().getId():null,
                footballer.getPosition().name());
    }

    public Footballer toFootballer(){
        Footballer footballer=new Footballer();
        footballer.setId(id);
        footballer.setName(name);
        footballer.setSurname(surname);
        footballer.setDateOfBirth(dateOfBirth);
        footballer.setNationality(nationality);
        if(position != null){
            try{
                footballer.setPosition(Footballer.PlayerPosition.valueOf(position));
            }catch(Exception ignored){
            }
        }
        return footballer;
    }

    public Footballer mergeWith(Footballer footballer) throws ConversionException {
        consumeIfNonNull(footballer::setName, name);
        consumeIfNonNull(footballer::setSurname, surname);
        consumeIfNonNull(footballer::setDateOfBirth, dateOfBirth);
        consumeIfNonNull(footballer::setNationality, nationality);
        convertAndConsumeIfNonNull(footballer::setPosition, Footballer.PlayerPosition::valueOf,position);
        return footballer;
    }

}
