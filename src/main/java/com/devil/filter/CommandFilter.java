package com.devil.filter;

import java.util.Map;
import com.devil.handler.Request;

public interface CommandFilter {
  // 기존 구현체에 영향을 주지 않고 새 규칙을 추가하는 방법
  // => default 메서드로 선언하라!
  default void init(Map<String,Object> context) throws Exception {
    // 메서드의 몸체가 있어 구현은 된 상태지만,
    // 실행할 코드는 없다.
    // 호출되는 즉시 바로 리턴될 것이다.
  }

  void doFilter(Request request, FilterChain next) throws Exception;

  default void destroy() {
    // 메서드의 몸체가 있어 구현은 된 상태지만,
    // 실행할 코드는 없다.
    // 호출되는 즉시 바로 리턴될 것이다.
  }
}
