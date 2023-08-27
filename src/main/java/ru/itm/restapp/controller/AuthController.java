package ru.itm.restapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itm.restapp.model.User;
import ru.itm.restapp.service.UserService;

@RestController
@RequestMapping
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        UserDetails foundUser = userService.loadUserByUsername(user.getUsername());
        if (!userService.checkPassword(foundUser, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ошибка авторизации!\nНеверный пароль");
        }
        return ResponseEntity.ok().body(foundUser);
    }
}
