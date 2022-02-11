package ru.job4j.accident.repository;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.*;

@Repository
@NoArgsConstructor
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> accidentTypeMap = Map.of(
            1, AccidentType.of(1, "Две машины"),
            2, AccidentType.of(2, "Машина и человек"),
            3, AccidentType.of(3, "Машина и велосипед")
    );
    private int id = 1;

    public void create(Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(accidentTypeMap.get(typeId));
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

    public List<AccidentType> getAccidentTypes() {
        return new ArrayList<>(accidentTypeMap.values());
    }
}
