# 😈 Developer Village, DEVIL 👩‍💻
>개발자 커뮤니티 웹 사이트

:pushpin: [발표 PPT](https://docs.google.com/presentation/d/1YRAt4UJY--kdYYx2JAo1ukq3rW8kx6nlbPc2kJ8exfU/edit#slide=id.ga6864e8c28_3_0)

## 바로가기
1. [제작기간 & 참여 인원](https://github.com/procompiler/developer-village/blob/main/README.md#1-%EC%A0%9C%EC%9E%91-%EA%B8%B0%EA%B0%84--%EC%B0%B8%EC%97%AC-%EC%9D%B8%EC%9B%90)
2. [사용 기술](https://github.com/procompiler/developer-village/blob/main/README.md#2-%EC%82%AC%EC%9A%A9-%EA%B8%B0%EC%88%A0)
3. [UI 프로토타입](https://github.com/procompiler/developer-village/blob/main/README.md#3-ui-%ED%94%84%EB%A1%9C%ED%86%A0%ED%83%80%EC%9E%85)
4. [ERD 설계](https://github.com/procompiler/developer-village/blob/main/README.md#4-erd-%EC%84%A4%EA%B3%84)
5. [핵심 기능](https://github.com/procompiler/developer-village/blob/main/README.md#5-%ED%95%B5%EC%8B%AC-%EA%B8%B0%EB%8A%A5)
6. [트러블 슈팅](https://github.com/procompiler/developer-village/blob/main/README.md#6-%ED%8A%B8%EB%9F%AC%EB%B8%94-%EC%8A%88%ED%8C%85)


<br>

## 1. 제작 기간 & 참여 인원
- 2020년 10월 27일 ~ 12월 31일
- 팀 프로젝트: [김하연](https://github.com/hayeon17kim), [유지연](https://github.com/jiyounyou), [박민섭](https://github.com/parkminseob), [이종엽](https://github.com/leejyeop), [최진영](https://github.com/cchoijjinyoung)
- [팀 소개](https://www.notion.so/48d1b112fcd04129ab601c0692ef93cb)

<br>

## 2. 사용 기술
  - Java 11
  - Spring MVC 5.3.2
  - Gradle
  - MyBatis 3.5.6
  - MariaDB 
  - JSP
  - Tiles
  - Ajax
  - JQuery

<br>

## 3. UI 프로토타입
Figma를 사용하여 UI 프로토타입을 만들었습니다. [바로가기](https://www.figma.com/file/Irabu6J2iBDQ4kZk0Ze0UB/Devil-UI-Prototype?node-id=113%3A2)

<br>

## 4. ERD 설계
![mysql-model](https://user-images.githubusercontent.com/50407047/105466251-4cbb4f00-5cd7-11eb-9075-35ad804753f5.png)

<br>

## 5. 핵심 기능

- 게시글 및 댓글 관리
- 유저  및 태그 팔로잉
- 뱃지 시스템: 유저 활동량에 기반한 뱃지 획득
  [상세점보 바로가기](https://hayeon17kim.github.io/posts/devil-badge/)
- 태그 시스템
- 신고차단 기능
- 게시글 북마크
- 사용자 맞춤 개인 피드
- 개인 알림 기능 (댓글, 대댓글, 팔로우, 뱃지획득)
- 게시글 북마크
- 개인 피드 조회
- 개인정보 관리

<br>

## 6. 트러블 슈팅
- [mysql의 datatime 타입과 java.util.Date 클래스](https://hayeon17kim.github.io/posts/devil-03/)
- [태그 언팔로우 시 `/tag/list`로 redirect하는 문제](https://hayeon17kim.github.io/posts/devil-28/)
- [유저 팔로잉 정보 조회 시 UserDao의 책임을 분산](https://hayeon17kim.github.io/posts/devil-29/)
- [게시글 검색 시 태그 정보가 사라지는 문제](https://hayeon17kim.github.io/posts/devil-49/)
- [DispatcherServlet을 admin과 app으로 분리](https://hayeon17kim.github.io/posts/devil-57/)
- [개인 피드 조회 시 게시글 중복 문제](https://hayeon17kim.github.io/posts/devil-58/)
- [DispatcherServlet에 따른 비활성 상태의 데이터 추출 여부 변경](https://hayeon17kim.github.io/posts/devil-61/)
- [알림 기능 구현](https://hayeon17kim.github.io/posts/devil-62/)
- [알림 목록 요청을 비동기 요청으로 전환](https://hayeon17kim.github.io/posts/devil-65/)

<br>

## 7. 회고 / 느낀점
