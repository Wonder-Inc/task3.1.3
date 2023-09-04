package ru.itm.restapp.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itm.restapp.dto.UserDto;
import ru.itm.restapp.mapper.UserMapper;
import ru.itm.restapp.model.User;
import ru.itm.restapp.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Transactional
    @Override
    public UserDto create(UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(userMapper.toEntity(user)));
    }
    
    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    
    @Transactional
    @Override
    public UserDto update(UserDto user) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(user)));
    }
    
    @Override
    public UserDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("Пользователя с таким id не существует!");
        } else {
            return userMapper.toDto(user.get());
        }
    }
    
    @Override
    public List<UserDto> findAll() {
        return userMapper.toDtoList(userRepository.findAll());
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Пользователя с таким именем не существует!");
        } else {
            return user;
        }
    }
    
    @Override
    public Boolean checkPassword(UserDetails userDetails, String password) {
        return passwordEncoder.matches(password, userDetails.getPassword());
    }
}
