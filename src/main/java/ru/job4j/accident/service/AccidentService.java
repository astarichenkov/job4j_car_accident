package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentHibernate accidentHibernate;

    public AccidentService(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    public List<Accident> findAll() {
        return accidentHibernate.findAll();
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

    public Accident save(Accident accident, String[] ids) {
        return accidentHibernate.save(accident, ids);
    }

}
