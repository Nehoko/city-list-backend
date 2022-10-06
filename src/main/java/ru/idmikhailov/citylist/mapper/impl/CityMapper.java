package ru.idmikhailov.citylist.mapper.impl;

import org.springframework.stereotype.Component;
import ru.idmikhailov.citylist.dto.CityDto;
import ru.idmikhailov.citylist.entity.City;
import ru.idmikhailov.citylist.mapper.Mapper;

@Component
public class CityMapper implements Mapper<CityDto, City> {
    @Override
    public CityDto toDto(City city) {
        if (city == null) return null;

        return new CityDto(city.getId(), city.getName(), city.getPhoto());
    }

    @Override
    public City toEntity(CityDto cityDto) {
        if (cityDto == null) return null;

        return new City(cityDto.getId(), cityDto.getName(), cityDto.getPhoto());
    }
}
