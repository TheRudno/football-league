package pl.polsl.take.footballleague.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.take.footballleague.database.Footballer;
import pl.polsl.take.footballleague.exceptions.NoEnumConstantException;

import javax.json.bind.annotation.JsonbProperty;
import java.time.LocalDate;
import java.util.Objects;

import static pl.polsl.take.footballleague.utils.Utils.consumeIfNonNull;

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

    public Footballer mergeWith(Footballer footballer) throws NoEnumConstantException {
        consumeIfNonNull(footballer::setName, name);
        consumeIfNonNull(footballer::setSurname, surname);
        consumeIfNonNull(footballer::setDateOfBirth, dateOfBirth);
        consumeIfNonNull(footballer::setNationality, nationality);
        if(Objects.nonNull(position)){
            try{
                footballer.setPosition(Footballer.PlayerPosition.valueOf(position));
            }catch(Exception ignored){
                throw new NoEnumConstantException(position + " is not correct value of position");
            }
        }
        return footballer;
    }

}
