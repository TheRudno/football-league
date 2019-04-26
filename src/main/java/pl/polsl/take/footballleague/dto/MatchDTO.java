package pl.polsl.take.footballleague.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.take.footballleague.database.Goal;
import pl.polsl.take.footballleague.database.Match;
import pl.polsl.take.footballleague.exceptions.NoEnumConstantException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static pl.polsl.take.footballleague.utils.Utils.consumeEnumIfNonNull;
import static pl.polsl.take.footballleague.utils.Utils.consumeIfNonNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {

    private Long id;
    private Long homeSide;
    private Long awaySide;
    private LocalDate matchDate;
    private List<Long> goals;
    private String result;


    public static MatchDTO from(Match match){
        return new MatchDTO(
                match.getId(),
                match.getHomeSide().getId(),
                match.getAwaySide().getId(),
                match.getMatchDate(),
                toGoalListIDS(match.getGoals()),
                match.getResult().name()
        );
    }

    public static List<Long> toGoalListIDS(List<Goal> goals){
        List<Long> goalsIDS = new ArrayList<>();
        for (Goal goal: goals) {
            goalsIDS.add(goal.getId());
        }
        return  goalsIDS;
    }

    public Match toMatch(){
        Match match = new Match();
        match.setResult(Match.Result.valueOf(result));
        match.setMatchDate(matchDate);
        match.setId(id);
        return match;
    }

    public Match mergeWith(Match match) throws NoEnumConstantException {
        consumeIfNonNull(match::setMatchDate, matchDate);
        consumeEnumIfNonNull(match::setResult, Match.Result.valueOf(result));
        return match;
    }

}
