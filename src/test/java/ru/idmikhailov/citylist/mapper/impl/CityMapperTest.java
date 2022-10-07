package ru.idmikhailov.citylist.mapper.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.idmikhailov.citylist.dto.CityDto;
import ru.idmikhailov.citylist.entity.City;
import ru.idmikhailov.citylist.mapper.Mapper;

@SpringBootTest
class CityMapperTest {

    @Autowired
    private Mapper<CityDto, City> cityMapper;

    @Test
    public void toDto() {
        City moscow = City.builder().id(1L).photo("https://example.com").name("Moscow").build();
        CityDto moscowDto = new CityDto(moscow.getId(), moscow.getName(), moscow.getPhoto());

        CityDto cityDto = cityMapper.toDto(moscow);

        Assertions.assertEquals(moscowDto, cityDto);
    }

    @Test
    public void toEntity() {
        City moscow = City.builder().id(1L).photo("https://example.com").name("Moscow").build();
        CityDto moscowDto = new CityDto(moscow.getId(), moscow.getName(), moscow.getPhoto());

        City city = cityMapper.toEntity(moscowDto);

        Assertions.assertEquals(moscow, city);
    }

}