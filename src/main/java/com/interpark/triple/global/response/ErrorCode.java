package com.interpark.triple.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum Naming Format : {주체}_{이유}
 * message format: 동사 명사형으로 마무리
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
  // Global
  INTERNAL_SERVER_ERROR(500, "G001", "서버 오류"),

  // USER
  USER_NOT_FOUND(400, "U001", "유저를 찾기 실패"),

  // CITY
  CITY_NOT_FOUND(400, "C001", "도시를 찾을 수 없음"),
  ;

  private final int status;
  private final String code;
  private final String message;
}
