package ru.idmikhailov.citylist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.idmikhailov.citylist.dto.CityDto;
import ru.idmikhailov.citylist.service.CityListService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CityListController.class)
class CityListControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CityListService cityListService;

    @Test
    void getCityPage_thenReturn200() throws Exception {
        int page = 0;
        int size = 10;
        CityDto moscow = new CityDto(1L, "Moscow", "http://example.com");
        List<CityDto> cityDtoList = List.of(moscow);
        Page<CityDto> pages = new PageImpl<>(cityDtoList, PageRequest.of(page, size), cityDtoList.size());

        given(this.cityListService.getCityPage(page, size, null)).willReturn(pages);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/city_page?page=0&size=10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(moscow.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value(moscow.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].photo").value(moscow.getPhoto()));
    }

    @Test
    void putCity_thenReturn200() throws Exception {
        CityDto moscow = new CityDto(1L, "Moscow", "http://example.com");

        mockMvc.perform(MockMvcRequestBuilders
                .put("/city")
                .content(asJsonString(moscow))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}