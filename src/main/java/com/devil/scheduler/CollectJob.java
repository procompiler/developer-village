package com.devil.scheduler;

import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.devil.domain.Badge;
import com.devil.domain.BadgeStandard;
import com.devil.domain.User;
import com.devil.service.BadgeService;
import com.devil.service.CollectService;
import com.devil.service.UserService;

@Component
public class CollectJob {
  CollectService collectService;
  UserService userService;
  BadgeService badgeService;

  public CollectJob(CollectService collectService, UserService userService,
      BadgeService badgeService) {
    this.collectService = collectService;
    this.userService = userService;
    this.badgeService = badgeService;
  }
  
  // 유저: 가입일수 / 팔로워수 / 태그리스트 - 게시글수 / 
  // 
  

  // 초 분 시 일 월 요일
  @Scheduled(cron = "* * * * * *")
  public void add() throws Exception {
    /*
    List<Badge> badges = badgeService.list((String) null);
    for (Badge badge : badges) {
      for (BadgeStandard standards : badge.getStandards()) {
        if (standards.getEvaluationName().equals("가입일수")) {
          
        }
      }
    }

    System.out.println("=====> TestJob.job1()");
    */
  }

}

