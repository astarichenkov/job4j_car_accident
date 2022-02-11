package ru.job4j.accident.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Accident {
    private int id;
    private String name;
    private String text;
    private String address;
}