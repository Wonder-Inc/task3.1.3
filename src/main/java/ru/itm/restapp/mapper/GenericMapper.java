package ru.itm.restapp.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
public abstract class GenericMapper<E, D> implements Mapper<E, D> {
    protected final ModelMapper modelMapper;
    private final Class<E> entityClass;
    private final Class<D> dtoClass;
    
    protected GenericMapper(ModelMapper modelMapper, Class<E> entityClass, Class<D> dtoClass) {
        this.modelMapper = modelMapper;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }
    
    @PostConstruct
    protected abstract void setupMapper();
    
    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, entityClass);
    }
    
    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, dtoClass);
    }
    
    @Override
    public List<E> toEntityList(List<D> dtoList) {
        return dtoList.stream().map(this::toEntity).toList();
    }

    @Override
    public List<D> toDtoList(List<E> entityList) {
        return entityList.stream().map(this::toDto).toList();
    }
    
    Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFieldsToEntity(source, destination);
            return context.getDestination();
        };
    }
    
    Converter<E, D> toDtoConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFieldsToDto(source, destination);
            return context.getDestination();
        };
    }
    
    protected abstract void mapSpecificFieldsToEntity(D source, E destination);
    
    protected abstract void mapSpecificFieldsToDto(E source, D destination);
    
    protected abstract Set<Long> getIds(E entity);
}
