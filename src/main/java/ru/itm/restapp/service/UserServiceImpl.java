package ru.itm.restapp.service;

import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itm.restapp.dao.UserDao;
import ru.itm.restapp.dao.RoleDao;
import ru.itm.restapp.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final BCryptPasswordEncoder passwordEncoder;
    
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Transactional
    @Override
    public void create(User user, List<Long> ids) {
        user.setAuthorities(roleDao.findRolesById(ids));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.create(user);
    }
    
    @Transactional
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
    
    @Transactional
    @Override
    public void update(User user, List<Long> rolesIds, Long userId) {
        user.setAuthorities(roleDao.findRolesById(rolesIds));
        userDao.update(user, userId);
    }
    
    @Override
    public User showUser(Long id) {
        return userDao.showUser(id);
    }
    
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        return userDao.loadUserByUsername(username);
    }
}
