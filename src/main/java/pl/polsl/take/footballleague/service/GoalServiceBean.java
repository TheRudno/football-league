package pl.polsl.take.footballleague.service;

import pl.polsl.take.footballleague.dao.GoalDAO;
import pl.polsl.take.footballleague.database.Goal;
import pl.polsl.take.footballleague.exceptions.ElementNotFoundException;
import pl.polsl.take.footballleague.exceptions.ElementValidationException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import java.util.List;

@Stateless
public class GoalServiceBean {

    @Inject
    GoalDAO goalDAO;

    @Inject
    Validator validator;

    public List<Goal> getAll(){
        return goalDAO.getAll();
    }

    public Goal getById(Long id) throws ElementNotFoundException {
        Goal goal = goalDAO.getById(id);
        if(goal != null){
            return goal;
        }else {
            throw new ElementNotFoundException(Goal.class);
        }
    }

    public void update(Goal goal) throws ElementValidationException{
        validate(goal);
        goalDAO.update(goal);
    }

    public void add(Goal goal) throws ElementValidationException{
        validate(goal);
        goalDAO.add(goal);
    }

    private void validate(Goal goal) throws ElementValidationException {
        var violations = validator.validate(goal);
        if(!violations.isEmpty())
            throw new ElementValidationException(violations);
    }

    public void remove(Long id) throws ElementNotFoundException{
        goalDAO.remove(getById(id));
    }


}
