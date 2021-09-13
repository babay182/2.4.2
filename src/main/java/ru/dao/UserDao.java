package ru.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.model.Role;
import ru.model.User;

import ru.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    User show(int id);
    void save(User user);
    Set<User> index();
    void delete(int id);
    void update(int id, User newUser);
    public Set<Role> getRoles();
    public void addRole(Role role);
    public Role getRole(int id);
    public User getUserByName(String userName);
}
