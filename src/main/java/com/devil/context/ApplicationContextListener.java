package com.devil.context;

import java.util.Map;

public interface ApplicationContextListener {
  // 발행자(애플리케이션)가 애플리케이션 시작을 알리기 위해 호출하는 메서드
  // - 발행자와 옵저버 간에 데이터를 공유하기 위해 맵 파라미터 추가
  // - 호출자가 옵저버의 실행 결과를 받을 수 있도록 파라미터로 맵 객체를 전달할 것이다.
  // - 리턴 값으로 결과를 전달하지 않고 파라미터로 넘어온 저장소에 보관하는 방법을 사용한다.
  // - 왜 이런 방식을 사용하는가?
  // - 파라미터 방식은 메서드에게 작업에 필요한 정보를 전달할 수 있기 때문이다.
  // - Map을 사용하여 파라미터로 전달하는 방식은 데이터의 In/Out이 가능하다.
  void contextInitialized(Map<String,Object> context);

  // 발행자(애플리케이션)가 애플리케이션 종료를 알리기 위해 호출하는 메서드
  void contextDestroyed(Map<String,Object> context);
}