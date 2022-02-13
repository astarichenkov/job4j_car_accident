package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccidentService {
    private final AccidentJdbcTemplate jdbcTemplate;

    public AccidentService(AccidentJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Accident> findAll() {
        return jdbcTemplate.findAll();
    }

    public Set<Rule> getRules(String[] ids) {
        Set<Rule> rsl = new HashSet<>();
        if (ids != null) {
            List<String> idList = List.of(ids);
            for (Rule rule : jdbcTemplate.getAllRules()) {
                if (idList.contains(String.valueOf(rule.getId()))) {
                    rsl.add(rule);
                }
            }
        }
        return rsl;
    }
}
