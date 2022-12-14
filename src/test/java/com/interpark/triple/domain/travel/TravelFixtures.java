package com.interpark.triple.domain.travel;

import com.interpark.triple.domain.city.dto.CityRegisterRequest;
import com.interpark.triple.domain.travel.dto.TravelCreateRequest;

import static java.time.LocalDateTime.now;

public class TravelFixtures {
  public static final TravelCreateRequest TRAVEL_SUWON_CREATE_REQUEST =
          TravelCreateRequest.builder().userId(1L).cityId(1L).travelStartAt(now().plusDays(1)).travelEndAt(now().plusDays(2)).build();

  public static final CityRegisterRequest TRAVEL_SEOUL_CREATE_REQUEST =
      CityRegisterRequest.builder().userId(1L).cityName("서울").cityIntroContent("간단한 서울 소개").build();
}
