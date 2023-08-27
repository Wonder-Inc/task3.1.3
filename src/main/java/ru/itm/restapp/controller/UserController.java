package ru.itm.restapp.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itm.restapp.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping
public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/user")
    public UserDetails getUser(Principal principal) {
        return userService.loadUserByUsername(principal.getName());
    }
}
