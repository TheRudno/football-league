package pl.polsl.take.footballleague.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.take.footballleague.database.Goal;
import pl.polsl.take.footballleague.exceptions.NoEnumConstantException;


import static pl.polsl.take.footballleague.utils.Utils.consumeEnumIfNonNull;
import static pl.polsl.take.footballleague.utils.Utils.consumeIfNonNull;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GoalDTO {

    private Long id;
    private int goalMinute;
    private Long match;
    private Long scorer;
    private String side;

    public static GoalDTO from(Goal goal){
        return new GoalDTO(
                goal.getId(),
                goal.getGoalMinute(),
                goal.getMatch().getId(),
                goal.getScorer().getId(),
                goal.getTeam().name()
        );
    }

    public Goal toGoal(){
        Goal goal = new Goal();
        goal.setTeam(Goal.Team.valueOf(side));
        goal.setGoalMinute(goalMinute);
        return goal;
    }

    public Goal mergeWith(Goal goal) throws NoEnumConstantException{
        consumeIfNonNull(goal::setGoalMinute,goalMinute);
        consumeEnumIfNonNull(goal::setTeam, Goal.Team.valueOf(side));
        return goal;
    }


}
