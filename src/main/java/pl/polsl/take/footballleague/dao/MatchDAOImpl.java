package pl.polsl.take.footballleague.dao;

import pl.polsl.take.footballleague.database.Club;
import pl.polsl.take.footballleague.database.Match;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Dependent
public class MatchDAOImpl implements MatchDAO {

    @PersistenceContext
    EntityManager manager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Match> getAll() {
        Query query = manager.createQuery("select m from Match m");
        return query.getResultList();
    }

    @Override
    public Match getById(Long id) {
        return manager.find(Match.class, id);
    }

    @Override
    public void add(Match match) { manager.persist(match); }

    @Override
    public void update(Match match) { manager.merge(match); }

    @Override
    public void remove(Match match) { manager.remove(match); }
}
