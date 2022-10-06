package ru.idmikhailov.citylist.mapper;

import java.util.List;

/**
 * Interface for mapping dto to entity and vice versa
 * TODO: replace with mapstruct for further releases
 * @param <DTO> dto
 * @param <ENTITY> entity
 */
public interface Mapper<DTO, ENTITY> {

    DTO toDto(ENTITY entity);
    ENTITY toEntity(DTO dto);

    default List<ENTITY> toEntityList(List<DTO> dtoList) {
        return dtoList.stream().map(this::toEntity).toList();
    }
    default List<DTO> toDtoList(List<ENTITY> entityList) {
        return entityList.stream().map(this::toDto).toList();
    }
}
