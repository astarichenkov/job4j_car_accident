package ru.job4j.accident.repository;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.*;

@Repository
@NoArgsConstructor
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private int id;

    public void create(Accident accident) {
        if (accident.getId() != 0) {
            accidents.put(accident.getId(), accident);
        } else {
            accident.setId(id);
            accidents.put(id, accident);
            id++;
        }
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }
}
