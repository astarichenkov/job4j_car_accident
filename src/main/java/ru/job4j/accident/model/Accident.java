package ru.job4j.accident.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "accident")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String text;
    private String address;

    @ManyToOne
    private AccidentType type;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "accident_rule",
            joinColumns = { @JoinColumn(name = "accident_id") },
            inverseJoinColumns = { @JoinColumn(name = "rule_id") }
    )
    private Set<Rule> rules = new HashSet<>();
}