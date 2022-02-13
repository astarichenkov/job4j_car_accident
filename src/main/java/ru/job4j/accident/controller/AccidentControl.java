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
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AccidentControl {
    private final AccidentJdbcTemplate accidents;
    private final AccidentService accidentService;


    public AccidentControl(AccidentJdbcTemplate accidents, AccidentService accidentService) {
        this.accidents = accidents;
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", getTypes());
        model.addAttribute("rules", getRules());
        return "accident/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", getTypes());
        model.addAttribute("rules", getRules());
        model.addAttribute("accident", accidents.findById(id));
        return "accident/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("ruleIds");
        accident.setRules(accidentService.getRules(ids));
        accidents.create(accident);
        return "redirect:/";
    }

    private List<AccidentType> getTypes() {
        return accidents.getAccidentTypes();
    }

    private List<Rule> getRules() {
        return accidents.getAllRules();
    }
}