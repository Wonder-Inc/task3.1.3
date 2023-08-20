package ru.itm.restapp.service;

import org.springframework.stereotype.Service;
import ru.itm.restapp.dao.RoleDao;
import ru.itm.restapp.model.Role;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;
    
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    
    @Override
    public Set<Role> findRolesById(List<Long> ids) {
        return roleDao.findRolesById(ids);
    }
}
