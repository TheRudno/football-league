package pl.polsl.take.footballleague.service;

import pl.polsl.take.footballleague.dao.ClubDAO;
import pl.polsl.take.footballleague.database.Club;
import pl.polsl.take.footballleague.exceptions.ElementNotFoundException;
import pl.polsl.take.footballleague.exceptions.ElementValidationException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;

@Stateless
public class ClubServiceBean {

    @Inject
    ClubDAO clubDAO;

    @Inject
    Validator validator;


    public List<Club> getAll(){
        return clubDAO.getAll();
    }

    public Club getById(Long id) throws ElementNotFoundException {
        Club club = clubDAO.getById(id);
        if(Objects.nonNull(club)){
            return club;
        }else{
            throw new ElementNotFoundException(Club.class);
        }

    }

    public void update(Club club) throws ElementValidationException {
        validate(club);
        clubDAO.update(club);
    }

    public void add(Club club) throws ElementValidationException {
        validate(club);
        clubDAO.add(club);
    }

    public void remove(Long id) throws ElementNotFoundException {
        Club footballer = clubDAO.getById(id);
        if(footballer != null){
            clubDAO.remove(footballer);
        }else{
            throw new ElementNotFoundException(Club.class);
        }
    }

    private void validate(Club club) throws ElementValidationException {
        var violations = validator.validate(club);
        if(!violations.isEmpty()){
            throw new ElementValidationException(violations);
        }
    }




}
