package ru.itm.restapp.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.itm.restapp.dto.UserDto;
import ru.itm.restapp.model.Role;
import ru.itm.restapp.model.User;
import ru.itm.restapp.repository.RoleRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper extends GenericMapper<User, UserDto> {
    private final RoleRepository roleRepository;
    
    protected UserMapper(ModelMapper modelMapper, RoleRepository roleRepository) {
        super(modelMapper, User.class, UserDto.class);
        this.roleRepository = roleRepository;
    }
    
    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(User.class, UserDto.class)
                .addMappings(m -> m.skip(UserDto::setRolesIds)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(UserDto.class, User.class)
                .addMappings(m -> m.skip(User::setAuthorities)).setPostConverter(toEntityConverter());
    }
    
    @Override
    protected void mapSpecificFieldsToEntity(UserDto source, User destination) {
        if (Objects.nonNull(source.getRolesIds())) {
            destination.setAuthorities(new HashSet<>(roleRepository.findAllById(source.getRolesIds())));
        } else {
            destination.setAuthorities(Collections.emptySet());
        }
    }
    
    @Override
    protected void mapSpecificFieldsToDto(User source, UserDto destination) {
        destination.setRolesIds(getIds(source));
    }
    
    @Override
    protected Set<Long> getIds(User entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getAuthorities())) {
            return null;
        } else {
            return entity.getAuthorities().stream()
                    .map(Role::getId)
                    .collect(Collectors.toSet());
        }
    }
}
