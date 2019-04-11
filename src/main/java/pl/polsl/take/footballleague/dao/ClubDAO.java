package pl.polsl.take.footballleague.dao;

import pl.polsl.take.footballleague.database.Club;

import java.util.List;

public interface ClubDAO {
    List<Club> getAll();
    Club getById(Long id);
    void add(Club footballer);
    void update(Club footballer);
    void remove(Club footballer);
}
