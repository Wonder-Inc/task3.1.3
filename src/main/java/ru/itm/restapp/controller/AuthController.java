package ru.itm.restapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itm.restapp.dto.LoginDto;
import ru.itm.restapp.dto.RoleDto;
import ru.itm.restapp.mapper.UserMapper;
import ru.itm.restapp.model.User;
import ru.itm.restapp.service.RoleService;
import ru.itm.restapp.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class AuthController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper userMapper;
    
    public AuthController(UserService userService, RoleService roleService, UserMapper userMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.userMapper = userMapper;
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Map<String, Object> response = new HashMap<>();
        User foundUser = (User) userService.loadUserByUsername(loginDto.getUsername());
        if (!userService.checkPassword(foundUser, loginDto.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ошибка авторизации!\nНеверный пароль");
        }
        response.put("username", userMapper.toDto(foundUser).getUsername());
        response.put("authorities", roleService.findAllById(userMapper.toDto(foundUser).getRolesIds())
                .stream().map(RoleDto::getTitle));
        return ResponseEntity.ok().body(response);
    }
}
