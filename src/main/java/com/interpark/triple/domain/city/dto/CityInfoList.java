package com.interpark.triple.domain.city.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CityInfoList {

  private List<CityInfo> cityInfos = new ArrayList<>();

  public void addAllCityInfo(List<CityInfo> cityInfos){
    this.cityInfos.addAll(cityInfos);
  }
  public void addCityInfo(CityInfo cityInfo){
    this.cityInfos.add(cityInfo);
  }

}
