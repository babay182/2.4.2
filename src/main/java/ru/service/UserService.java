package ru.service;

import ru.model.User;

import java.util.List;

public interface UserService {
    User show(int id);
    void save(User user);
    List<User> index();
    void delete(int id);
    void update(int id, User newUser);
}
