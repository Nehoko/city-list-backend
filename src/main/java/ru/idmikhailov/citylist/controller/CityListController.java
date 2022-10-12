package ru.idmikhailov.citylist.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.idmikhailov.citylist.dto.CityDto;
import ru.idmikhailov.citylist.service.CityListService;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:80"}, maxAge = 3600)
public class CityListController {

    private final CityListService cityListService;


    @GetMapping("/city_page")
    public Page<CityDto> getCityPage(@RequestParam int page,
                                     @RequestParam int size,
                                     @RequestParam(required = false) String search) {
        return cityListService.getCityPage(page, size, search);
    }

    @PutMapping(value = "/city", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void putCity(@RequestBody CityDto cityDto) {
        cityListService.updateCity(cityDto);
    }

    @GetMapping("/city")
    public CityDto getCity(@RequestParam Long id) {
        return cityListService.getCity(id);
    }
}
