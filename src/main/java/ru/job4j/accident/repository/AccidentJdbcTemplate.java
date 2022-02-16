package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        String insertMessageSql
                = "insert into accident (name, text, address, type_id) values (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(insertMessageSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);

        accident.setId((int) keyHolder.getKeyList().get(0).get("id"));

        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rule (accident_id, rule_id) values (?,?)",
                    accident.getId(),
                    rule.getId());
        }
        return accident;
    }

    public List<Accident> findAll() {
        return jdbc.query("select accident.id as acc_id, accident.name as acc_name, text, address,"
                        + " t.id as t_id, t.name as t_name from accident join type t"
                        + " on accident.type_id = t.id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("acc_id"));
                    accident.setName(rs.getString("acc_name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(AccidentType.of(rs.getInt("t_id"),
                            rs.getString("t_name")));
                    accident.setRules(new HashSet<>(getRules(accident.getId())));
                    return accident;
                });
    }

    public List<Rule> getAllRules() {
        return jdbc.query("select id, name from rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public List<Rule> getRules(int id) {
        return jdbc.query("select r.id, r.name"
                        + " from rule as r join accident_rule ar on r.id = ar.rule_id"
                        + " where ar.accident_id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                },
                id);
    }

    public Accident findById(int id) {
        return jdbc.queryForObject("select accident.id as acc_id, accident.name as acc_name, text, address,"
                        + " t.id as t_id, t.name as t_name from accident join type t"
                        + " on accident.type_id = t.id where accident.id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("acc_id"));
                    accident.setName(rs.getString("acc_name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(AccidentType.of(rs.getInt("t_id"),
                            rs.getString("t_name")));
                    accident.setRules(new HashSet<>(getRules(accident.getId())));
                    return accident;
                },
                id);
    }

    public List<AccidentType> getAccidentTypes() {
        return jdbc.query("select id, name from type",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }
}