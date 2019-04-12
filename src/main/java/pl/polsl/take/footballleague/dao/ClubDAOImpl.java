package pl.polsl.take.footballleague.dao;

import pl.polsl.take.footballleague.database.Club;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Dependent
public class ClubDAOImpl implements ClubDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Club> getAll() {
        Query query = entityManager.createQuery("SELECT c FROM Club c");
        return query.getResultList();
    }

    @Override
    public Club getById(Long id) {
        return entityManager.find(Club.class,id);
    }

    @Override
    public void add(Club footballer) {
        entityManager.persist(footballer);
    }

    @Override
    public void update(Club footballer) {
        entityManager.merge(footballer);
    }

    @Override
    public void remove(Club footballer) {
        entityManager.remove(footballer);
    }
}
