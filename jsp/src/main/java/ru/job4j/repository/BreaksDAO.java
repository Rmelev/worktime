package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Breaks;
import ru.job4j.models.DayTiming;

import java.util.List;

@Repository
public interface BreaksDAO extends CrudRepository<Breaks, Integer> {
//    Breaks getByRecord(DayTiming record);
    Breaks findById(int id);
    List<Breaks> findAllByRecordId(int id);

}
