package ru.itm.restapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.itm.restapp.dto.UserDto;

import java.util.List;

public interface UserService extends UserDetailsService {
    
    UserDto create(UserDto user);
    
    void delete(Long id);
    
    UserDto update(UserDto user);
    
    UserDto findById(Long id);
    
    List<UserDto> findAll();
    
    UserDetails loadUserByUsername(String username);
    
    Boolean checkPassword(UserDetails userDetails, String password);
}
