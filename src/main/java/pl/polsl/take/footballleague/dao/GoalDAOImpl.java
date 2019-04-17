package pl.polsl.take.footballleague.dao;

import pl.polsl.take.footballleague.database.Goal;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Dependent
public class GoalDAOImpl implements GoalDAO {

    @PersistenceContext
    EntityManager manager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Goal> getAll() {
        Query query = manager.createQuery("select g from Goal g");
        return query.getResultList();
    }

    @Override
    public Goal getById(Long id) { return manager.find(Goal.class, id); }

    @Override
    public void add(Goal goal) { manager.persist(goal); }

    @Override
    public void update(Goal goal) { manager.merge(goal); }

    @Override
    public void remove(Goal goal) { manager.remove(goal); }
}
