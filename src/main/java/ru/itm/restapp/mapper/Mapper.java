package ru.itm.restapp.mapper;

import java.util.List;

public interface Mapper<E, D> {
    
    E toEntity(D dto);
    
    D toDto(E entity);
    
    List<E> toEntityList(List<D> dtoList);
    
    List<D> toDtoList(List<E> entityList);
}
