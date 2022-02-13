package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public List<Accident> findAll() {
        return accidentMem.findAll();
    }

    public Set<Rule> getRules(String[] ids) {
        List<String> idList = List.of(ids);
        Set<Rule> rsl = new HashSet<>();
        for (Rule rule : accidentMem.getAllRules()) {
            if (idList.contains(String.valueOf(rule.getId()))) {
                rsl.add(rule);
            }
        }
        return rsl;
    }
}
