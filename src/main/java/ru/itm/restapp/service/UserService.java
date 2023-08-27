package ru.itm.restapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.itm.restapp.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    
    void create(User user);
    
    void delete(Long id);
    
    void update(User user, Long userId);
    
    User showUser(Long id);
    
    List<User> listUsers();
    
    Boolean checkPassword(UserDetails userDetails, String password);
}
