package ru.idmikhailov.citylist.service;

import org.springframework.data.domain.Page;
import ru.idmikhailov.citylist.dto.CityDto;

public interface CityListService {

    Page<CityDto> getCityPage(int page, int size, String search);
    void updateCity(CityDto cityDto);

    CityDto getCity(Long id);
}
