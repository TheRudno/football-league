package pl.polsl.take.footballleague.dao;

import pl.polsl.take.footballleague.database.Goal;

import java.util.List;

public interface GoalDAO {

    List<Goal> getAll();
    Goal getById(Long id);
    void add(Goal goal);
    void update(Goal goal);
    void remove(Goal goal);

}
