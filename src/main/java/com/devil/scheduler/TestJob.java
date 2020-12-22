package com.devil.scheduler;

import org.springframework.scheduling.annotation.Scheduled;

//@Component
public class TestJob {

  // 초 분 시 일 월 요일
  @Scheduled(cron = "*/10 * * * * *")
  public void job1() {
    // 스프링의 스케쥴러는 매 10초마다 이 메서드를 호출할 것이다.
    System.out.println("=====> TestJob.job1()");
  }
}

