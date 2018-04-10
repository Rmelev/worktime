package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.DayTiming;
import ru.job4j.models.User;

import java.util.List;

@Repository
public interface DayTimingDAO extends CrudRepository<DayTiming, Integer> {
    DayTiming getByUserAndFordate(User user, int day);
}
