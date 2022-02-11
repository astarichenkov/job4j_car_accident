package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }


    public List<Accident> getSomeAccidents() {
        List<Accident> accidents = accidentMem.findAll();
        Accident accident = Accident.builder()
                .id(1)
                .address("Кузбасская, 1е")
                .name("Превышение скорости")
                .text("КоАП РФ Статья 12.9.2. Превышение установленной скорости движения транспортного средства на величину более 20, но не более 40 километров в час.")
                .build();
        accidents.add(accident);
        accident = Accident.builder()
                .id(2)
                .address("Ларина, 13")
                .name("Превышение скорости")
                .text("КоАП РФ Статья 12.9.3. Превышение установленной скорости движения транспортного средства на величину более 40, но не более 60 километров в час.")
                .build();
        accidents.add(accident);
        return  accidents;
    }
}
