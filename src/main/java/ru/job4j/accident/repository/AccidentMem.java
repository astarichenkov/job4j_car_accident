package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> accidentTypeMap = Map.of(
            1, AccidentType.of(1, "Две машины"),
            2, AccidentType.of(2, "Машина и человек"),
            3, AccidentType.of(3, "Машина и велосипед")
    );
    private final AtomicInteger id = new AtomicInteger(3);

    public AccidentMem() {
        Accident accident = Accident.builder()
                .id(1)
                .address("Кузбасская, 1е")
                .name("Превышение скорости")
                .type(AccidentType.of(1, "Две машины"))
                .text("КоАП РФ Статья 12.9.2. Превышение установленной скорости движения транспортного средства"
                        + " на величину более 20, но не более 40 километров в час.")
                .build();
        accidents.put(1, accident);
        accident = Accident.builder()
                .id(2)
                .address("Ларина, 13")
                .name("Превышение скорости")
                .type(AccidentType.of(2, "Машина и человек"))
                .text("КоАП РФ Статья 12.9.3. Превышение установленной скорости движения транспортного средства"
                        + " на величину более 40, но не более 60 километров в час.")
                .build();
        accidents.put(2, accident);
    }

    public void create(Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(accidentTypeMap.get(typeId));
        if (accident.getId() != 0) {
            accidents.put(accident.getId(), accident);
        } else {
            accident.setId(id.get());
            accidents.put(id.get(), accident);
            id.incrementAndGet();
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
