package pl.polsl.take.footballleague.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.take.footballleague.database.Club;
import pl.polsl.take.footballleague.database.Goal;
import pl.polsl.take.footballleague.database.Match;
import pl.polsl.take.footballleague.exceptions.NoEnumConstantException;

import java.time.LocalDate;
import java.util.List;

import static pl.polsl.take.footballleague.utils.Utils.consumeEnumIfNonNull;
import static pl.polsl.take.footballleague.utils.Utils.consumeIfNonNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {

    private Long id;
    private Club homeSide;
    private Club awaySide;
    private LocalDate matchDate;
    private List<Goal> goals;
    private String result;


    public static MatchDTO from(Match match){
        return new MatchDTO(
                match.getId(),
                match.getHomeSide(),
                match.getAwaySide(),
                match.getMatchDate(),
                match.getGoals(),
                match.getResult().name()
        );
    }

    public Match toMatch(){
        Match match = new Match();
        match.setResult(Match.Result.valueOf(result));
        match.setAwaySide(awaySide);
        match.setHomeSide(homeSide);
        match.setMatchDate(matchDate);
        match.setId(id);
        match.setGoals(goals);
        return match;
    }

    public Match mergeWith(Match match) throws NoEnumConstantException {
        consumeIfNonNull(match::setHomeSide, homeSide);
        consumeIfNonNull(match::setAwaySide, awaySide);
        consumeIfNonNull(match::setGoals, goals);
        consumeIfNonNull(match::setMatchDate, matchDate);
        consumeEnumIfNonNull(match::setResult, Match.Result.valueOf(result));
        return match;
    }

}
