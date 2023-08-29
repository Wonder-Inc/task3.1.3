package ru.itm.restapp.service;

import ru.itm.restapp.dto.RoleDto;

import java.util.List;
import java.util.Set;

public interface RoleService {
    
    List<RoleDto> findAllById(Set<Long> ids);
}
