package ru.itm.restapp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itm.restapp.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public void create(User user) {
        entityManager.persist(user);
    }
    
    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
    
    @Override
    public void update(User user, Long id) {
        User oldUser = entityManager.find(User.class, id);
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhone(user.getPhone());
        oldUser.setAuthorities(user.getAuthorities());
        entityManager.merge(oldUser);
    }
    
    @Override
    public User showUser(Long id) {
        return entityManager.find(User.class, id);
    }
    
    @Override
    public User loadUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u join fetch u.authorities where u.username = :username", User.class);
        return query.setParameter("username", username).getSingleResult();
    }
    
    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("from User").getResultList();
    }
}
