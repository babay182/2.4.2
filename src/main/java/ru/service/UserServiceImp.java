package ru.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.dao.UserDao;
import ru.model.Role;
import ru.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User show(int id) {
        return userDao.show(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public Set<User> index() {
        return userDao.index();
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public void update(int id, User newUser) {
        userDao.update(id, newUser);
    }

    @Override
    public Set<Role> getRoles() {
        return userDao.getRoles();
    }

    @Override
    public void addRole(Role role) {
        userDao.addRole(role);
    }

    @Override
    public Role getRole(int id) {
        return userDao.getRole(id);
    }

    @Override
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

//    public Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
//        return roles.stream().map(e->new SimpleGrantedAuthority(e.getName())).collect(Collectors.toList());
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = userDao.getUserByName(s);
//        if(user == null){
//            throw new UsernameNotFoundException(String.format("User %s not found", s));
//        }
//        return new org.springframework.security.core.userdetails.User(user.getName(),
//                user.getPassword(), getAuthorities(user.getRoles()));
//    }
}
