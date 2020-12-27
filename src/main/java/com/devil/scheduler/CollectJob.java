package com.devil.scheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.devil.domain.Badge;
import com.devil.domain.BadgeStan;
import com.devil.domain.Collect;
import com.devil.domain.Tag;
import com.devil.domain.User;
import com.devil.service.BadgeService;
import com.devil.service.CollectService;
import com.devil.service.UserService;

// @Component
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

  // 초 분 시 일 월 요일
  // @Scheduled(cron = "* * * * * *")
  public void add() throws Exception {
    List<Badge> badges = badgeService.list((String) null);
    List<User> users = userService.list(null);
    for (User user : users) {
      List<Badge> collectedBadges = badgeService.list(user);
      for (Badge badge : badges) {
        System.out.println("===" + badge.getName() + "뱃지===");
        Tag tag = badge.getTag();
        // 이미 가지고 있는 뱃지라면 건너뛰기
        if (collectedBadges.contains(badge)) {
          break;
        }
        // 가지고 있지 않은 뱃지 기준 충족되는지 알아보기
        List<BadgeStan> standards = badge.getBadgeStans();
        // 전체 기준 갯수
        int totalStandards = standards.size();
        // 충족되는 기준 갯수
        int count = 0;
        for (BadgeStan standard : badge.getBadgeStans()) {
          System.out.println(standard.getEvaluationName());
          System.out.println("필요 count: " + standard.getCount());
          Map<String, Object> params = new HashMap<>();
          params.put("userNo", user.getNo());
          params.put("tagNo", tag.getNo());
          params.put("evaluationNo", standard.getEvaluationNo());
          // 충족된다면 충족되는 기준 갯수를 하나씩 높인다.
          int userCount = collectService.getCount(params);
          System.out.println("실제 count: " + userCount);
          if (userCount >= standard.getCount()) {
            System.out.println("충족함");
            count++;
          }
        }
        // 충족되는 기준 갯수와 뱃지의 전체 기준 갯수가 같으면 뱃지를 수여한다.
        if (totalStandards == count) {
          collectService.add(new Collect().setUser(user).setBadge(badge));
        }
        System.out.println(user.getNickname() + "님은 조건 충족이 되지 않습니다.");
        System.out.println("필요기준 갯수: " + totalStandards);
        System.out.println("충족기준 갯수: " + count);
      }
    }
  }
}

