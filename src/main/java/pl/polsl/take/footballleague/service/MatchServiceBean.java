package pl.polsl.take.footballleague.service;

import pl.polsl.take.footballleague.dao.MatchDAO;
import pl.polsl.take.footballleague.database.Match;
import pl.polsl.take.footballleague.exceptions.ElementNotFoundException;
import pl.polsl.take.footballleague.exceptions.ElementValidationException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import java.util.List;

@Stateless
public class MatchServiceBean {

    @Inject
    MatchDAO matchDAO;

    @Inject
    Validator validator;

    public List<Match> getAll(){
        return matchDAO.getAll();
    }

    public Match getById(Long id) throws ElementNotFoundException {
        Match goal = matchDAO.getById(id);
        if(goal != null){
            return goal;
        }else {
            throw new ElementNotFoundException(Match.class);
        }
    }

    public void update(Match match) throws ElementValidationException {
        validate(match);
        matchDAO.update(match);
    }

    public void add(Match match) throws ElementValidationException{
        validate(match);
        matchDAO.add(match);
    }

    private void validate(Match match) throws ElementValidationException {
        var violations = validator.validate(match);
        if(!violations.isEmpty())
            throw new ElementValidationException(violations);
    }

    public void remove(Long id) throws ElementNotFoundException {
        matchDAO.remove(getById(id));
    }
}
