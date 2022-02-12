package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AccidentControl {
    private final AccidentMem accidents;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", getTypes());
        model.addAttribute("rules", getRules().values());
        return "accident/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", getTypes());
        model.addAttribute("rules", getRules().values());
        model.addAttribute("accident", accidents.findById(id));
        return "accident/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("ruleIds");
        Map<Integer, Rule> ruleMap = getRules();
        Set<Rule> accidentRules = new HashSet<>();
        if (ids != null) {
            for (String id : ids) {
                Rule rule = ruleMap.get(Integer.parseInt(id));
                accidentRules.add(rule);
            }
        }
        accident.setRules(accidentRules);
        accidents.create(accident);
        return "redirect:/";
    }

    private List<AccidentType> getTypes() {
        return accidents.getAccidentTypes();
    }

    private Map<Integer, Rule> getRules() {
        return accidents.getRules();
    }
}