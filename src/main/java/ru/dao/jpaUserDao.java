package ru.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.config.SpringConfig;
import ru.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional
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
    public List<User> index() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
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
    }
}
