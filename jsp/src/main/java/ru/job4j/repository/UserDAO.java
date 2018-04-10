package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.User;

@Repository
public interface UserDAO extends CrudRepository<User, Integer> {
    User getByLogin(String login);
}
