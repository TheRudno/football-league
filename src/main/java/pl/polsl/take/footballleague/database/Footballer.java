package pl.polsl.take.footballleague.database;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
public class Footballer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String nationality;

    @ManyToOne
    @JoinColumn
    private Club club;

    @OneToMany(mappedBy = "scorer")
    private List<Goal> goals;

    @Enumerated
    @Column(nullable = false)
    private PlayerPosition position;

    public enum PlayerPosition {
        GOALKEEPER,
        DEFENDER,
        MIDFIELDER,
        STRIKER
    }
}
