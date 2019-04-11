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
public class Footballer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String surname;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String nationality;

    @ManyToOne
    @JoinColumn
    private Club club;

    @OneToMany(mappedBy = "scorer")
    private List<Goal> goals;

    @Enumerated
    @NotNull
    @Column(nullable = false)
    private PlayerPosition position;

    public enum PlayerPosition {
        GOALKEEPER,
        DEFENDER,
        MIDFIELDER,
        STRIKER
    }
}
