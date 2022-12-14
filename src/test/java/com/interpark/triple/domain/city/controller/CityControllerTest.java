package com.interpark.triple.domain.city.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.interpark.triple.domain.city.controller.document.CityRestDocument;
import com.interpark.triple.domain.city.dto.CityInfo;
import com.interpark.triple.domain.city.dto.CityInfoList;
import com.interpark.triple.domain.city.dto.CityRegisterRequest;
import com.interpark.triple.domain.city.dto.CityUpdateRequest;
import com.interpark.triple.domain.city.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(controllers = CityController.class)
class CityControllerTest {

  @MockBean private CityService cityService;

  private ObjectMapper objectMapper;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp(
      WebApplicationContext webApplicationContext,
      RestDocumentationContextProvider restDocumentationContextProvider) {
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    mockMvc =
        MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
            .apply(documentationConfiguration(restDocumentationContextProvider))
            .build();
  }

  @Test
  @DisplayName("city ?????? api")
  void registerCity() throws Exception {
    // given
    CityInfo cityInfo =
        CityInfo.builder()
            .name("??????")
            .introContent("????????? ?????? ??????")
            .createdAt(now())
            .updatedAt(now())
            .build();
    CityRegisterRequest cityRegisterRequest =
        CityRegisterRequest.builder()
            .userId(1L)
            .cityName("??????")
            .cityIntroContent("????????? ?????? ??????")
            .build();
    // when
    when(cityService.registerCity(any())).thenReturn(cityInfo);

    // then
    mockMvc
        .perform(
            RestDocumentationRequestBuilders.post("/api/v1/city")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cityRegisterRequest)))
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(CityRestDocument.getCreateCityInfoDocument());
  }

  @Test
  @DisplayName("city ?????? api")
  void deleteCity() throws Exception {
    // given
    CityInfo cityInfo =
        CityInfo.builder()
            .name("??????")
            .introContent("????????? ?????? ??????")
            .createdAt(now())
            .updatedAt(now())
            .build();
    // when
    doNothing().when(cityService).deleteCity(any());

    // then
    mockMvc
        .perform(RestDocumentationRequestBuilders.delete("/api/v1/city/{id}", 1L))
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(CityRestDocument.getDeleteCityDocument());
  }

  @Test
  @DisplayName("city ?????? api")
  void readOneCity() throws Exception {
    // given
    CityInfo cityInfo =
        CityInfo.builder()
            .name("??????")
            .introContent("????????? ?????? ??????")
            .createdAt(now())
            .updatedAt(now())
            .build();
    // when
    when(cityService.findCityInfoById(any())).thenReturn(cityInfo);

    // then
    mockMvc
        .perform(
            RestDocumentationRequestBuilders.get("/api/v1/city/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(CityRestDocument.getOneCityInfoByIdDocument());
  }

  @Test
  @DisplayName("????????? ??? ?????? api")
  void readCityByUser() throws Exception {
    // given
    List<CityInfo> expectedCityInfoList = createCityInfos();
    // when
    when(cityService.findCityInfoByUserIdWithConditions(any()))
        .thenReturn(new CityInfoList(expectedCityInfoList));

    // then
    mockMvc
        .perform(
            RestDocumentationRequestBuilders.get("/api/v1/city/users/{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(CityRestDocument.getCityInfoListByUserIdDocument());
  }

  @Test
  @DisplayName("city ?????? api")
  void updateCityByUser() throws Exception {
    // given
    CityInfo cityInfo =
        CityInfo.builder()
            .name("??????")
            .introContent("????????? ?????? ??????")
            .createdAt(now())
            .updatedAt(now())
            .build();
    CityUpdateRequest cityUpdateRequest =
        CityUpdateRequest.builder().cityId(1L).cityName("??????").cityIntroContent("????????? ?????? ??????").build();
    // when
    when(cityService.updateCityInfo(any())).thenReturn(cityInfo);

    // then
    mockMvc
        .perform(
            RestDocumentationRequestBuilders.put("/api/v1/city")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cityUpdateRequest)))
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(CityRestDocument.getUpdateCityInfoByIdDocument());
  }

  private static List<CityInfo> createCityInfos() {
    List<String> cityList = List.of("??????", "??????", "??????", "??????", "??????", "??????", "??????", "??????", "??????", "??????");
    List<CityInfo> newCityList = new ArrayList<CityInfo>(10);
    for (String city : cityList) {
      newCityList.add(new CityInfo(city, "????????? " + city, now(), now()));
    }
    return newCityList;
  }
}
