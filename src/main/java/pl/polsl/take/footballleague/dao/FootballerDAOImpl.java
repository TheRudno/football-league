package pl.polsl.take.footballleague.dao;

import pl.polsl.take.footballleague.database.Footballer;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Dependent
public class FootballerDAOImpl implements FootballerDAO{

    @PersistenceContext
    EntityManager manager;

    @Override
    @SuppressWarnings("unchecked")
    public Set<Footballer> getAll() {
        Query query = manager.createQuery("select f from Footballer f");
        return new HashSet(query.getResultList());
    }

    @Override
    public Footballer getById(Long id) {
        return manager.find(Footballer.class,id);
    }

    @Override
    public void add(Footballer footballer) {
        manager.persist(footballer);
    }

    @Override
    public void update(Footballer footballer) {
        manager.merge(footballer);
    }

    @Override
    public void remove(Footballer footballer) {
        manager.remove(footballer);
    }
}
