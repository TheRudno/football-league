package pl.polsl.take.footballleague.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private int goalMinute;

    @NotNull
    @ManyToOne
    @JoinColumn
    private Match match;

    @NotNull
    @ManyToOne
    @JoinColumn
    private Footballer scorer;

    @NotEmpty
    @Column(nullable = false)
    private Team team;

    public enum Team{
        HOME,AWAY
    }

}
