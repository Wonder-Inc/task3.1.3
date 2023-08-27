package ru.itm.restapp.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itm.restapp.model.User;
import ru.itm.restapp.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    
    public AdminController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public ResponseEntity<UserDetails> getAdmin(Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.loadUserByUsername(principal.getName()));
    }
    
    @GetMapping("/allUsers")
    public List<User> getUsers() {
        return userService.listUsers();
    }
    
    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid User user) {
        userService.create(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    
    @PutMapping(value = "/{id}/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid User user, @PathVariable("id") Long id) {
        userService.update(user, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.showUser(id);
    }
}
