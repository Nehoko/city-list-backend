package ru.idmikhailov.citylist.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.idmikhailov.citylist.dto.CityDto;
import ru.idmikhailov.citylist.entity.City;
import ru.idmikhailov.citylist.mapper.Mapper;
import ru.idmikhailov.citylist.repository.CityRepository;
import ru.idmikhailov.citylist.service.CityListService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CityListServiceImpl implements CityListService {


    private final CityRepository cityRepository;
    private final Mapper<CityDto, City> cityMapper;

    @Transactional(readOnly = true)
    public Page<CityDto> getCityPage(int page, int size, String search) {

        Page<City> cityPage;
        if (search == null || search.isEmpty() || search.isBlank()) {
            cityPage = cityRepository.findAll(PageRequest.of(page, size));
            return cityPage.map(cityMapper::toDto);
        }

        cityPage = cityRepository.findAllByNameIsLike(search, PageRequest.of(page, size));
        return cityPage.map(cityMapper::toDto);

    }

    @Transactional
    public void updateCity(CityDto cityDto) {
        if (cityDto == null) return;

        City city = cityRepository.getReferenceById(cityDto.getId());
        city.setName(cityDto.getName());
        city.setPhoto(cityDto.getPhoto());

        cityRepository.save(city);

    }

    @Transactional(readOnly = true)
    public CityDto getCity(Long id) {
        Optional<City> cityOptional = cityRepository.findById(id);
        if (cityOptional.isEmpty()) return null;
        return cityMapper.toDto(cityOptional.get());
    }
}
