package pl.polsl.take.footballleague.dao;

import pl.polsl.take.footballleague.database.Footballer;

import java.util.List;

public interface FootballerDAO {
    List<Footballer> getAll();
    Footballer getById(Long id);
    void add(Footballer footballer);
    void update(Footballer footballer);
    void remove(Footballer footballer);
}
