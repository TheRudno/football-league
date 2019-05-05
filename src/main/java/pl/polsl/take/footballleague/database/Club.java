package pl.polsl.take.footballleague.database;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.ext.ParamConverter;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotEmpty
    @Column(nullable = false)
    private String city;

    @NotEmpty
    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "club", fetch=FetchType.EAGER)
    private Set<Footballer> squad;

}
