package ru.itm.restapp.service;

import org.springframework.stereotype.Service;
import ru.itm.restapp.dto.RoleDto;
import ru.itm.restapp.mapper.RoleMapper;
import ru.itm.restapp.repository.RoleRepository;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }
    
    public List<RoleDto> findAllById(Set<Long> ids) {
        return roleMapper.toDtoList(roleRepository.findAllById(ids));
    }
}
