package pl.polsl.take.footballleague.database;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne()
    @JoinColumn
    private Club homeSide;

    @NotNull
    @ManyToOne()
    @JoinColumn
    private Club awaySide;

    @NotNull
    @Column(nullable = false)
    private LocalDate matchDate;

    @OneToMany(mappedBy = "match",fetch = FetchType.EAGER)
    private List<Goal> goals;

    @Column(nullable = false)
    private Result result;

    public enum Result{
        HOMEWIN,AWAYWIN,DRAW
    }

}
