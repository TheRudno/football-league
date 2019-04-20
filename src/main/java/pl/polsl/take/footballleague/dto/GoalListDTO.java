package pl.polsl.take.footballleague.dto;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.take.footballleague.database.Goal;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class GoalListDTO {

    private List<GoalDTO> goals;

    static public GoalListDTO from(List<Goal> goals){
        GoalListDTO goalListDTO = new GoalListDTO();
        goalListDTO.setGoals(goals
                .stream()
                .map(GoalDTO::from)
                .collect(Collectors.toList())
        );
        return goalListDTO;
    }

}
