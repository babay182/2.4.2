package ru.service;

import ru.model.Role;
import ru.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User show(int id);
    void save(User user);
    Set<User> index();
    void delete(int id);
    void update(int id, User newUser);
    Set<Role> getRoles();
    void addRole(Role role);
    Role getRole(int id);
    public User getUserByName(String userName);
}
