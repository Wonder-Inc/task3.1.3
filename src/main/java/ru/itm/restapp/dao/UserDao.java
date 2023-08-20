package ru.itm.restapp.dao;

import ru.itm.restapp.model.User;

import java.util.List;

public interface UserDao {
    
    void create(User user);
    
    void delete(Long id);
    
    void update(User user, Long id);
    
    User showUser(Long id);
    
    User loadUserByUsername(String username);
    
    List<User> listUsers();
}
