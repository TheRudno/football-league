package pl.polsl.take.footballleague.service;

import pl.polsl.take.footballleague.dao.FootballerDAO;
import pl.polsl.take.footballleague.database.Footballer;
import pl.polsl.take.footballleague.exceptions.ElementNotFoundException;
import pl.polsl.take.footballleague.exceptions.ElementValidationException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import java.util.List;

@Stateless
public class FootballerServiceBean {

    @Inject
    FootballerDAO footballerDAO;

    @Inject
    Validator validator;

    public List<Footballer> getAll(){
        return footballerDAO.getAll();
    }

    public Footballer getById(Long id) throws ElementNotFoundException {
        Footballer footballer = footballerDAO.getById(id);
        if(footballer != null){
            return footballer;
        }else{
            throw new ElementNotFoundException();
        }

    }

    public void update(Footballer footballer) throws ElementValidationException {
        validate(footballer);
        footballerDAO.update(footballer);
    }

    public void add(Footballer footballer) throws ElementValidationException {
        validate(footballer);
        footballerDAO.add(footballer);
    }

    public void remove(Long id) throws ElementNotFoundException {
        Footballer footballer = footballerDAO.getById(id);
        if(footballer != null){
            footballerDAO.remove(footballer);
        }else{
            throw new ElementNotFoundException();
        }
    }

    private void validate(Footballer footballer) throws ElementValidationException {
        var violations = validator.validate(footballer);
        if(!violations.isEmpty()){
            throw new ElementValidationException(violations);
        }
    }
}
