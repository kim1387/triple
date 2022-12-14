package com.interpark.triple.domain.city.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@RequiredArgsConstructor
public class CityUpdateRequest {

  @NotNull(message = "수정할 도시 Id는 필수입니다.")
  private final Long cityId;

  @NotBlank(message = "도시이름을 입력해주세요.")
  private final String cityName;

  @NotBlank(message = "도시의 간단한 소개를 입력해주세요.")
  private final String cityIntroContent;
}
