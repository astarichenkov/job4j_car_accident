package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccidentService {
    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository ruleRepository;

    public AccidentService(AccidentRepository accidentRepository, AccidentTypeRepository accidentTypeRepository, RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    public List<Accident> findAll() {
        List<Accident> res = new ArrayList<>();
        accidentRepository.findAll().forEach(res::add);
        return res;
    }

    public List<Rule> getAllRules() {
        List<Rule> res = new ArrayList<>();
        ruleRepository.findAll().forEach(res::add);
        return res;
    }

    public List<AccidentType> getAccidentTypes() {
        List<AccidentType> res = new ArrayList<>();
        accidentTypeRepository.findAll().forEach(res::add);
        return res;
    }

    public Accident findById(int id) {
        return accidentRepository.findById(id).get();
    }

    public Accident save(Accident accident, String[] ids) {
        if (ids != null) {
            for (String id : ids) {
                Rule rule = new Rule();
                rule.setId(Integer.parseInt(id));
                accident.addRule(rule);
            }
        }
        return accidentRepository.save(accident);
    }

}
