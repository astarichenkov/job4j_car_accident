package ru.job4j.accident.repository;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.*;

@Repository
@NoArgsConstructor
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();

    public void add(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }
}
