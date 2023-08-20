package ru.itm.restapp.service;

import ru.itm.restapp.model.Role;
import java.util.List;
import java.util.Set;

public interface RoleService {
   
   Set<Role> findRolesById(List<Long> ids);
}
