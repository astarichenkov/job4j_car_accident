package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccidentService {
    private final AccidentHibernate accidentHibernate;

    public AccidentService(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    public List<Accident> findAll() {
        return accidentHibernate.findAll();
    }

    public Set<Rule> getRules(String[] ids) {
        Set<Rule> rsl = new HashSet<>();
        if (ids != null) {
            List<String> idList = List.of(ids);
            for (Rule rule : getAllRules()) {
                if (idList.contains(String.valueOf(rule.getId()))) {
                    rsl.add(rule);
                }
            }
        }
        return rsl;
    }

    public List<Rule> getAllRules() {
        return accidentHibernate.getAllRules();
    }

    public List<AccidentType> getAccidentTypes() {
        return accidentHibernate.getAccidentTypes();
    }

    public Accident findById(int id) {
        return accidentHibernate.findById(id);
    }

    public Accident create(Accident accident) {
        return accidentHibernate.create(accident);
    }

}
