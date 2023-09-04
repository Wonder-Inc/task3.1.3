package ru.itm.restapp.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.itm.restapp.dto.RoleDto;
import ru.itm.restapp.model.Role;
import ru.itm.restapp.model.User;
import ru.itm.restapp.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper extends GenericMapper<Role, RoleDto> {
    private final UserRepository userRepository;
    
    protected RoleMapper(ModelMapper modelMapper, UserRepository roleRepository) {
        super(modelMapper, Role.class, RoleDto.class);
        this.userRepository = roleRepository;
    }
    
    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Role.class, RoleDto.class)
                .addMappings(m -> m.skip(RoleDto::setUsersIds)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(RoleDto.class, Role.class)
                .addMappings(m -> m.skip(Role::setUsers)).setPostConverter(toEntityConverter());
    }
    
    @Override
    protected void mapSpecificFieldsToEntity(RoleDto source, Role destination) {
        if (Objects.nonNull(source.getUsersIds())) {
            destination.setUsers(new HashSet<>(userRepository.findAllById(source.getUsersIds())));
        } else {
            destination.setUsers(Collections.emptySet());
        }
    }
    
    @Override
    protected void mapSpecificFieldsToDto(Role source, RoleDto destination) {
        destination.setUsersIds(getIds(source));
    }
    
    @Override
    protected Set<Long> getIds(Role entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getUsers())) {
            return null;
        } else {
            return entity.getUsers().stream()
                    .map(User::getId)
                    .collect(Collectors.toSet());
        }
    }
}
