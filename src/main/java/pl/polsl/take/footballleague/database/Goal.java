package pl.polsl.take.footballleague.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int goalMinute;

    @ManyToOne
    @JoinColumn
    private Match match;

    @ManyToOne()
    @JoinColumn
    private Footballer scorer;

    @Column(nullable = false)
    private Team team;

    private enum Team{
        HOME,AWAY
    }

}
