package com.interpark.triple.domain.travel.controller;

import com.interpark.triple.domain.travel.dto.TravelCreateRequest;
import com.interpark.triple.domain.travel.dto.TravelInfo;
import com.interpark.triple.domain.travel.service.TravelService;
import com.interpark.triple.global.response.ResultCode;
import com.interpark.triple.global.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/travel")
@RestController
@RequiredArgsConstructor
public class TravelController {

  private final TravelService travelService;

  @PostMapping
  public ResponseEntity<ResultResponse> createTravel(
      TravelCreateRequest request) {
    TravelInfo travelInfo =
        travelService.createTravel(request);

    return ResponseEntity.ok(
        ResultResponse.of(ResultCode.CREATE_TRAVEL_SUCCESS, travelInfo));
  }


}