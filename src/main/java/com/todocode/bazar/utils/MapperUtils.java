package com.todocode.bazar.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MapperUtils {
    private final ObjectMapper mapper;

    public <D, T> T mapDtoToEntity(D dto, Class<T> entityClass) {
        return mapper.convertValue(dto, entityClass);
    }

    public <T, D> D mapEntityToDto(T entity, Class<D> dtoClass) {
        return mapper.convertValue(entity, dtoClass);
    }

    public <T, D> List<D> mapEntityListToDtoList(List<T> entityList, Class<D> dtoClass) {
        return entityList.stream()
                .map(entity -> mapper.convertValue(entity, dtoClass))
                .collect(Collectors.toList());
    }

    public <D, T> List<T> mapDtoListToEntityList(List<D> dtoList, Class<T> entityClass) {
        return dtoList.stream()
                .map(dto -> mapper.convertValue(dto, entityClass))
                .collect(Collectors.toList());
    }
}
