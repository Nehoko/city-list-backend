package ru.idmikhailov.citylist.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.idmikhailov.citylist.dto.CityDto;
import ru.idmikhailov.citylist.entity.City;
import ru.idmikhailov.citylist.mapper.Mapper;
import ru.idmikhailov.citylist.repository.CityRepository;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CityListServiceTest {

    @Autowired
    private CityListService cityListService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private Mapper<CityDto, City> cityMapper;

    @Test
    @Transactional
    public void getCityPage() {
        int page = 0;
        int size = 10;
        City city = City.builder().name("Moscow").photo("https://example.com").build();
        city = cityRepository.save(city);
        CityDto cityDto = cityMapper.toDto(city);
        List<CityDto> cityDtoList = List.of(cityDto);

        Assertions.assertEquals(cityListService.getCityPage(page, size, null).getContent(), cityDtoList);

        cityRepository.delete(city);
    }

    @Test
    @Transactional
    public void updateCity() {
        City moscow = City.builder().name("Moscow").photo("https://example.ru").build();

        // save original
        moscow = cityRepository.save(moscow);

        // create DTO for updating
        String kyivName = "Kiyv";
        String photo = "https://example.com.ua";
        CityDto kyiv = new CityDto(moscow.getId(), kyivName, photo);

        // use 'update city' of cityListService for testing
        cityListService.updateCity(kyiv);

        // get updated entity
        Optional<City> updated = cityRepository.findById(moscow.getId());

        // we still have that entity
        Assertions.assertTrue(updated.isPresent());

        City updatedCity = updated.get();

        Assertions.assertEquals(updatedCity.getId(), moscow.getId());
        Assertions.assertEquals(updatedCity.getName(), kyivName);
        Assertions.assertEquals(updatedCity.getPhoto(), photo);

        cityRepository.delete(updatedCity);
    }
}