package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident create(Accident accident) {
        try (Session session = sf.openSession()) {
            session.save(accident);
            return accident;
        }
    }

    public List<Accident> findAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Accident", Accident.class)
                    .list();
        }
    }

    public Accident findById(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Accident a "
                            + "where a.id = :id", Accident.class)
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    public List<AccidentType> getAccidentTypes() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }

    public List<Rule> getAllRules() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule", Rule.class)
                    .list();
        }
    }
}