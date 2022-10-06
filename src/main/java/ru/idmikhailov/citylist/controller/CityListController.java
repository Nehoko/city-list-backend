package ru.idmikhailov.citylist.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.idmikhailov.citylist.dto.CityDto;
import ru.idmikhailov.citylist.service.CityListService;

@RestController
@AllArgsConstructor
public class CityListController {

    private final CityListService cityListService;


    @GetMapping("/city_page")
    public Page<CityDto> getCityPage(@RequestParam int page,
                                     @RequestParam int size,
                                     @RequestParam(required = false) String search) {
        return cityListService.getCityPage(page, size, search);
    }

    @PutMapping("/city")
    public void putCity(@RequestBody CityDto cityDto) {
        cityListService.updateCity(cityDto);
    }

}
