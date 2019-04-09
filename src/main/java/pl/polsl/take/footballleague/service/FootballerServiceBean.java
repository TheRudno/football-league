package pl.polsl.take.footballleague.service;

import pl.polsl.take.footballleague.dao.FootballerDAO;
import pl.polsl.take.footballleague.database.Footballer;
import pl.polsl.take.footballleague.exceptions.ElementNotFoundException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class FootballerServiceBean {

    @Inject
    FootballerDAO footballerDAO;

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

    public void update(Footballer footballer) throws ElementNotFoundException {
        if(footballerDAO.getById(footballer.getId()) != null){
            footballerDAO.update(footballer);
        }else{
            throw new ElementNotFoundException();
        }

    }

    public void add(Footballer footballer){
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
}
