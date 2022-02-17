package ru.job4j.accident.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

import java.util.Optional;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    @Override
    @EntityGraph(attributePaths = {"type", "rules"})
    @NonNull
    Iterable<Accident> findAll();

    @Override
    @EntityGraph(attributePaths = {"type", "rules"})
    @NonNull
    Optional<Accident> findById(@NonNull Integer id);
}