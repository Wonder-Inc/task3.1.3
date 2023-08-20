package ru.itm.restapp.dao;

import ru.itm.restapp.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    
    Role findRoleByTitle(String title);
    
    Set<Role> findRolesById(List<Long> ids);
}
