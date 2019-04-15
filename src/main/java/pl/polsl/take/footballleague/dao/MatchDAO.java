package pl.polsl.take.footballleague.dao;

import pl.polsl.take.footballleague.database.Match;

import java.util.List;

public interface MatchDAO {

    List<Match> getAll();
    Match getById(Long id);
    void add(Match match);
    void update(Match match);
    void remove(Match match);
}
