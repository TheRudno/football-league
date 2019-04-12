package pl.polsl.take.footballleague.database;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn
    private Club homeSide;

    @ManyToOne()
    @JoinColumn
    private Club awaySide;

    @Column(nullable = false)
    private LocalDate matchDate;

    @OneToMany(mappedBy = "match")
    private List<Goal> goals;

    @Column(nullable = false)
    private Result result;

    private enum Result{
        HOMEWIN,AWAYWIN,DRAW
    }

}
