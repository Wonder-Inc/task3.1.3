package ru.itm.restapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.itm.restapp.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    
    void create(User user, List<Long> ids);
    
    void delete(Long id);
    
    void update(User user, List<Long> rolesIds, Long userId);
    
    User showUser(Long id);
    
    List<User> listUsers();
}
