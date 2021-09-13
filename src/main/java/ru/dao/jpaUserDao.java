package ru.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.model.Role;
import ru.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

@Component
public class jpaUserDao implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User show(int id) {
        TypedQuery<User> query = entityManager.createQuery("select user from User user where user.id " +
                "= :id", User.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public Set<User> index() {
        return new HashSet<>(entityManager.createQuery("select user from User user", User.class).getResultList());
    }

    @Override
    public void delete(int id) {
        User p = show(id);
        entityManager.remove(p);
    }

    @Override
    public void update(int id, User newUser) {
        User p = show(id);
        p.setAge(newUser.getAge());
        p.setEmail(newUser.getEmail());
        p.setName(newUser.getName());
        p.setRoles(newUser.getRoles());
    }

    @Override
    public Set<Role> getRoles() {
        Set<Role> list = new HashSet<>(entityManager.createQuery("FROM Role", Role.class).getResultList());
        System.out.println("Role list received from sql: " + list);
        return list;
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getRole(int id) {
        return entityManager.getReference(Role.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByName(String userName) {
        return entityManager.createQuery("select user from User user where user.name = :name", User.class)
                .setParameter("name", userName)
                .getSingleResult();
    }


}
