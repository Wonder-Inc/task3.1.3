package ru.itm.restapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itm.restapp.dto.UserDto;
import ru.itm.restapp.mapper.UserMapper;
import ru.itm.restapp.model.User;
import ru.itm.restapp.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    
    @GetMapping("/user")
    public ResponseEntity<UserDto> getUser(Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(userMapper.toDto((User) userService
                .loadUserByUsername(principal.getName())));
    }
}
