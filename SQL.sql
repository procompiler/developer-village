-- 유저
DROP TABLE IF EXISTS user RESTRICT;

-- 게시글
DROP TABLE IF EXISTS article RESTRICT;

-- 댓글
DROP TABLE IF EXISTS comment RESTRICT;

-- 뱃지
DROP TABLE IF EXISTS badge RESTRICT;

-- 뱃지평가기준
DROP TABLE IF EXISTS badge_standard RESTRICT;

-- 태그
DROP TABLE IF EXISTS tag RESTRICT;

-- 게시글-태그
DROP TABLE IF EXISTS article_tag RESTRICT;

-- 팔로우
DROP TABLE IF EXISTS follow RESTRICT;

-- 유저_뱃지
DROP TABLE IF EXISTS user_badge RESTRICT;

-- 지역
DROP TABLE IF EXISTS region RESTRICT;

-- 게시글 첨부 사진
DROP TABLE IF EXISTS article_photo RESTRICT;

-- 신고
DROP TABLE IF EXISTS report RESTRICT;

-- 유저_북마크_게시글
DROP TABLE IF EXISTS user_bookmark_article RESTRICT;

-- 알람
DROP TABLE IF EXISTS notification RESTRICT;

-- 유저_팔로우_태그
DROP TABLE IF EXISTS user_follow_tag RESTRICT;

-- 신고 유형
DROP TABLE IF EXISTS report_type RESTRICT;

-- 뱃지평가
DROP TABLE IF EXISTS badge_evaluation RESTRICT;

-- 카테고리
DROP TABLE IF EXISTS category RESTRICT;

-- 게시글신고
DROP TABLE IF EXISTS report_article RESTRICT;

-- 댓글신고
DROP TABLE IF EXISTS report_comment RESTRICT;

-- 사용자차단
DROP TABLE IF EXISTS block RESTRICT;

-- 유저
CREATE TABLE user (
  uno         INTEGER      NOT NULL COMMENT '사용자번호', -- 사용자번호
  email       VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
  nickname    VARCHAR(50)  NULL     COMMENT '닉네임', -- 닉네임
  name        VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  pwd         VARCHAR(255) NULL     COMMENT '암호', -- 암호
  cdt         DATE         NOT NULL DEFAULT now() COMMENT '가입일', -- 가입일
  rnt_vsdt    DATETIME     NOT NULL COMMENT '최종방문일', -- 최종방문일
  login_type  VARCHAR(50)  NOT NULL COMMENT '로그인유형', -- 로그인유형
  photo       VARCHAR(255) NULL     COMMENT '사진', -- 사진
  hp_url      VARCHAR(255) NULL     COMMENT '개인홈피주소', -- 개인홈피주소
  gh_url      VARCHAR(255) NULL     COMMENT '깃허브주소', -- 깃허브주소
  in_url      VARCHAR(255) NULL     COMMENT '인스타주소', -- 인스타주소
  tw_url      VARCHAR(255) NULL     COMMENT '트위터주소', -- 트위터주소
  blocked     INTEGER      NOT NULL DEFAULT 0 COMMENT '차단여부', -- 차단여부
  tech        MEDIUMTEXT   NULL     COMMENT '기술스택', -- 기술스택
  auth        INTEGER      NOT NULL DEFAULT 0 COMMENT '관리자여부', -- 관리자여부
  notificated INTEGER      NOT NULL DEFAULT 0 COMMENT '알람알림여부' -- 알람알림여부
)
COMMENT '유저';

-- 유저
ALTER TABLE user
  ADD CONSTRAINT PK_user -- 유저 기본키
    PRIMARY KEY (
      uno -- 사용자번호
    );

-- 이메일 유니크
CREATE UNIQUE INDEX UIX_email
  ON user ( -- 유저
    email ASC -- 이메일
  );

-- 닉네임 유니크
CREATE UNIQUE INDEX UIX_nick
  ON user ( -- 유저
    nickname ASC -- 닉네임
  );

ALTER TABLE user
  MODIFY COLUMN uno INTEGER NOT NULL AUTO_INCREMENT COMMENT '사용자번호';

-- 게시글
CREATE TABLE article (
  arno      INTEGER      NOT NULL COMMENT '게시글번호', -- 게시글번호
  writer    INTEGER      NOT NULL COMMENT '작성자', -- 작성자
  cano      INTEGER      NOT NULL COMMENT '카테고리번호', -- 카테고리번호
  rgno      INTEGER      NULL     COMMENT '스터디_지역번호', -- 스터디_지역번호
  title     VARCHAR(255) NOT NULL COMMENT '게시글제목', -- 게시글제목
  cdt       DATETIME     NOT NULL DEFAULT now() COMMENT '등록일자', -- 등록일자
  vw_cnt    INTEGER      NOT NULL DEFAULT 0 COMMENT '조회수', -- 조회수
  udt       DATETIME     NULL     COMMENT '수정일자', -- 수정일자
  ddt       DATETIME     NULL     COMMENT '삭제일자', -- 삭제일자
  status    INTEGER      NOT NULL DEFAULT 0 COMMENT '활성상태', -- 활성상태
  st_status INTEGER      NULL     DEFAULT 0 COMMENT '모집상태', -- 모집상태
  rc_edt    DATE         NULL     COMMENT '모집마감일' -- 모집마감일
)
COMMENT '게시글';

-- 게시글
ALTER TABLE article
  ADD CONSTRAINT PK_article -- 게시글 기본키
    PRIMARY KEY (
      arno -- 게시글번호
    );

ALTER TABLE article
  MODIFY COLUMN arno INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시글번호';

-- 댓글
CREATE TABLE comment (
  cno      INTEGER    NOT NULL COMMENT '댓글번호', -- 댓글번호
  arno     INTEGER    NOT NULL COMMENT '게시글번호', -- 게시글번호
  uno      INTEGER    NOT NULL COMMENT '작성자', -- 작성자
  content  MEDIUMTEXT NOT NULL COMMENT '내용', -- 내용
  cdt      DATETIME   NOT NULL DEFAULT now() COMMENT '등록일자', -- 등록일자
  ord      INTEGER    NULL     COMMENT '댓글순서', -- 댓글순서
  step     INTEGER    NULL     COMMENT '댓글단계', -- 댓글단계
  udt      DATETIME   NULL     COMMENT '수정일자', -- 수정일자
  status   INTEGER    NOT NULL DEFAULT 0 COMMENT '활성상태', -- 활성상태
  ddt      DATETIME   NULL     COMMENT '삭제일자', -- 삭제일자
  selected INTEGER    NOT NULL DEFAULT 0 COMMENT '대표답변여부' -- 대표답변여부
)
COMMENT '댓글';

-- 댓글
ALTER TABLE comment
  ADD CONSTRAINT PK_comment -- 댓글 기본키
    PRIMARY KEY (
      cno -- 댓글번호
    );

ALTER TABLE comment
  MODIFY COLUMN cno INTEGER NOT NULL AUTO_INCREMENT COMMENT '댓글번호';

-- 뱃지
CREATE TABLE badge (
  bno   INTEGER      NOT NULL COMMENT '뱃지번호', -- 뱃지번호
  name  VARCHAR(50)  NOT NULL COMMENT '뱃지이름', -- 뱃지이름
  photo VARCHAR(255) NOT NULL COMMENT '사진', -- 사진
  tno   INTEGER      NULL     COMMENT '태그번호' -- 태그번호
)
COMMENT '뱃지';

-- 뱃지
ALTER TABLE badge
  ADD CONSTRAINT PK_badge -- 뱃지 기본키
    PRIMARY KEY (
      bno -- 뱃지번호
    );

ALTER TABLE badge
  MODIFY COLUMN bno INTEGER NOT NULL AUTO_INCREMENT COMMENT '뱃지번호';

-- 뱃지평가기준
CREATE TABLE badge_standard (
  bsno   INTEGER NOT NULL COMMENT '뱃지평가기준번호', -- 뱃지평가기준번호
  bno    INTEGER NOT NULL COMMENT '뱃지번호', -- 뱃지번호
  bsname INTEGER NOT NULL COMMENT '뱃지평가명번호', -- 뱃지평가명번호
  count  INTEGER NULL     COMMENT '기준횟수' -- 기준횟수
)
COMMENT '뱃지평가기준';

-- 뱃지평가기준
ALTER TABLE badge_standard
  ADD CONSTRAINT PK_badge_standard -- 뱃지평가기준 기본키
    PRIMARY KEY (
      bsno -- 뱃지평가기준번호
    );

ALTER TABLE badge_standard
  MODIFY COLUMN bsno INTEGER NOT NULL AUTO_INCREMENT COMMENT '뱃지평가기준번호';

-- 태그
CREATE TABLE tag (
  tno   INTEGER      NOT NULL COMMENT '태그번호', -- 태그번호
  name  VARCHAR(50)  NOT NULL COMMENT '태그이름', -- 태그이름
  photo VARCHAR(255) NULL     COMMENT '사진', -- 사진
  color VARCHAR(50)  NOT NULL DEFAULT "#6C5DDF" COMMENT '색(hex)' -- 색(hex)
)
COMMENT '태그';

-- 태그
ALTER TABLE tag
  ADD CONSTRAINT PK_tag -- 태그 기본키
    PRIMARY KEY (
      tno -- 태그번호
    );

-- 태그이름 유니크키
CREATE UNIQUE INDEX UIX_tag
  ON tag ( -- 태그
    name ASC -- 태그이름
  );

ALTER TABLE tag
  MODIFY COLUMN tno INTEGER NOT NULL AUTO_INCREMENT COMMENT '태그번호';

-- 게시글-태그
CREATE TABLE article_tag (
  cano INTEGER NOT NULL COMMENT '게시글번호', -- 게시글번호
  tno  INTEGER NOT NULL COMMENT '태그번호' -- 태그번호
)
COMMENT '게시글-태그';

-- 게시글-태그
ALTER TABLE article_tag
  ADD CONSTRAINT PK_article_tag -- 게시글-태그 기본키
    PRIMARY KEY (
      cano, -- 게시글번호
      tno   -- 태그번호
    );

-- 팔로우
CREATE TABLE follow (
  following_no INTEGER  NOT NULL COMMENT '팔로잉번호', -- 팔로잉번호
  follower_no  INTEGER  NOT NULL COMMENT '팔로워번호', -- 팔로워번호
  cdt          DATETIME NOT NULL DEFAULT now() COMMENT '생성일' -- 생성일
)
COMMENT '팔로우';

-- 팔로우
ALTER TABLE follow
  ADD CONSTRAINT PK_follow -- 팔로우 기본키
    PRIMARY KEY (
      following_no, -- 팔로잉번호
      follower_no   -- 팔로워번호
    );

-- 유저_뱃지
CREATE TABLE user_badge (
  uno INTEGER NOT NULL COMMENT '사용자번호', -- 사용자번호
  bno INTEGER NOT NULL COMMENT '뱃지번호', -- 뱃지번호
  adt DATE    NOT NULL DEFAULT now() COMMENT '수여일' -- 수여일
)
COMMENT '유저_뱃지';

-- 유저_뱃지
ALTER TABLE user_badge
  ADD CONSTRAINT PK_user_badge -- 유저_뱃지 기본키
    PRIMARY KEY (
      uno, -- 사용자번호
      bno  -- 뱃지번호
    );

-- 지역
CREATE TABLE region (
  rgno INTEGER     NOT NULL COMMENT '지역번호', -- 지역번호
  name VARCHAR(50) NOT NULL COMMENT '지역명' -- 지역명
)
COMMENT '지역';

-- 지역
ALTER TABLE region
  ADD CONSTRAINT PK_region -- 지역 기본키
    PRIMARY KEY (
      rgno -- 지역번호
    );

-- 지역명 유니크키
CREATE UNIQUE INDEX UIX_region
  ON region ( -- 지역
    name ASC -- 지역명
  );

-- 게시글 첨부 사진
CREATE TABLE article_photo (
  pno  INTEGER      NOT NULL COMMENT '사진번호', -- 사진번호
  arno INTEGER      NOT NULL COMMENT '게시글번호', -- 게시글번호
  adr  VARCHAR(255) NOT NULL COMMENT '사진주소' -- 사진주소
)
COMMENT '게시글 첨부 사진';

-- 게시글 첨부 사진
ALTER TABLE article_photo
  ADD CONSTRAINT PK_article_photo -- 게시글 첨부 사진 기본키
    PRIMARY KEY (
      pno -- 사진번호
    );

ALTER TABLE article_photo
  MODIFY COLUMN pno INTEGER NOT NULL AUTO_INCREMENT COMMENT '사진번호';

-- 신고
CREATE TABLE report (
  rpno   INTEGER  NOT NULL COMMENT '신고번호', -- 신고번호
  rptno  INTEGER  NOT NULL COMMENT '신고유형번호', -- 신고유형번호
  uno    INTEGER  NOT NULL COMMENT '신고자', -- 신고자
  cdt    DATETIME NOT NULL DEFAULT now() COMMENT '신고일', -- 신고일
  status INTEGER  NULL     DEFAULT 0 COMMENT '처리상태', -- 처리상태
  pdt    DATETIME NULL     COMMENT '처리일' -- 처리일
)
COMMENT '신고';

-- 신고
ALTER TABLE report
  ADD CONSTRAINT PK_report -- 신고 기본키
    PRIMARY KEY (
      rpno -- 신고번호
    );

ALTER TABLE report
  MODIFY COLUMN rpno INTEGER NOT NULL AUTO_INCREMENT COMMENT '신고번호';

-- 유저_북마크_게시글
CREATE TABLE user_bookmark_article (
  uno  INTEGER  NOT NULL COMMENT '사용자번호', -- 사용자번호
  arno INTEGER  NOT NULL COMMENT '게시글번호', -- 게시글번호
  cdt  DATETIME NOT NULL DEFAULT now() COMMENT '북마크일시' -- 북마크일시
)
COMMENT '유저_북마크_게시글';

-- 유저_북마크_게시글
ALTER TABLE user_bookmark_article
  ADD CONSTRAINT PK_user_bookmark_article -- 유저_북마크_게시글 기본키
    PRIMARY KEY (
      uno,  -- 사용자번호
      arno  -- 게시글번호
    );

-- 알람
CREATE TABLE notification (
  nno     INTEGER      NOT NULL COMMENT '알람번호', -- 알람번호
  uno     INTEGER      NOT NULL COMMENT '사용자번호', -- 사용자번호
  cdt     DATETIME     NOT NULL DEFAULT now() COMMENT '알람생성일', -- 알람생성일
  content MEDIUMTEXT   NULL     COMMENT '알람내용', -- 알람내용
  type    VARCHAR(50)  NOT NULL COMMENT '알람타입', -- 알람타입
  url     VARCHAR(255) NULL     COMMENT ' URL' --  URL
)
COMMENT '알람';

-- 알람
ALTER TABLE notification
  ADD CONSTRAINT PK_notification -- 알람 기본키
    PRIMARY KEY (
      nno -- 알람번호
    );

ALTER TABLE notification
  MODIFY COLUMN nno INTEGER NOT NULL AUTO_INCREMENT COMMENT '알람번호';

-- 유저_팔로우_태그
CREATE TABLE user_follow_tag (
  uno INTEGER NOT NULL COMMENT '사용자번호', -- 사용자번호
  tno INTEGER NOT NULL COMMENT '태그번호', -- 태그번호
  cdt DATE    NOT NULL DEFAULT now() COMMENT '팔로우일시' -- 팔로우일시
)
COMMENT '유저_팔로우_태그';

-- 유저_팔로우_태그
ALTER TABLE user_follow_tag
  ADD CONSTRAINT PK_user_follow_tag -- 유저_팔로우_태그 기본키
    PRIMARY KEY (
      uno, -- 사용자번호
      tno  -- 태그번호
    );

-- 신고 유형
CREATE TABLE report_type (
  rptno INTEGER     NOT NULL COMMENT '신고유형번호', -- 신고유형번호
  name  VARCHAR(50) NOT NULL COMMENT '신고유형명' -- 신고유형명
)
COMMENT '신고 유형';

-- 신고 유형
ALTER TABLE report_type
  ADD CONSTRAINT PK_report_type -- 신고 유형 기본키
    PRIMARY KEY (
      rptno -- 신고유형번호
    );

-- 신고 유형명 유니크키
CREATE UNIQUE INDEX UIX_report_type
  ON report_type ( -- 신고 유형
    name ASC -- 신고유형명
  );

-- 뱃지평가
CREATE TABLE badge_evaluation (
  beno   INTEGER     NOT NULL COMMENT '뱃지평가명번호', -- 뱃지평가명번호
  bename VARCHAR(50) NOT NULL COMMENT '평가명' -- 평가명
)
COMMENT '뱃지평가';

-- 뱃지평가
ALTER TABLE badge_evaluation
  ADD CONSTRAINT PK_badge_evaluation -- 뱃지평가 기본키
    PRIMARY KEY (
      beno -- 뱃지평가명번호
    );

ALTER TABLE badge_evaluation
  MODIFY COLUMN beno INTEGER NOT NULL AUTO_INCREMENT COMMENT '뱃지평가명번호';

-- 카테고리
CREATE TABLE category (
  cano INTEGER     NOT NULL COMMENT '카테고리번호', -- 카테고리번호
  name VARCHAR(50) NOT NULL COMMENT '카테고리명' -- 카테고리명
)
COMMENT '카테고리';

-- 카테고리
ALTER TABLE category
  ADD CONSTRAINT PK_category -- 카테고리 기본키
    PRIMARY KEY (
      cano -- 카테고리번호
    );

-- 분류명 유니크키
CREATE UNIQUE INDEX UIX_category
  ON category ( -- 카테고리
    name ASC -- 카테고리명
  );

-- 게시글신고
CREATE TABLE report_article (
  rpno INTEGER NOT NULL COMMENT '신고번호', -- 신고번호
  arno INTEGER NOT NULL COMMENT '게시글번호' -- 게시글번호
)
COMMENT '게시글신고';

-- 게시글신고
ALTER TABLE report_article
  ADD CONSTRAINT PK_report_article -- 게시글신고 기본키
    PRIMARY KEY (
      rpno -- 신고번호
    );

-- 댓글신고
CREATE TABLE report_comment (
  rpno INTEGER NOT NULL COMMENT '신고번호', -- 신고번호
  cno  INTEGER NOT NULL COMMENT '댓글번호' -- 댓글번호
)
COMMENT '댓글신고';

-- 댓글신고
ALTER TABLE report_comment
  ADD CONSTRAINT PK_report_comment -- 댓글신고 기본키
    PRIMARY KEY (
      rpno -- 신고번호
    );

-- 사용자차단
CREATE TABLE block (
  blno        INTEGER    NOT NULL COMMENT '사용자차단번호', -- 사용자차단번호
  uno         INTEGER    NOT NULL COMMENT '사용자번호', -- 사용자번호
  cdt         DATE       NOT NULL DEFAULT now() COMMENT '차단일자', -- 차단일자
  dates       INTEGER    NOT NULL COMMENT '차단일수', -- 차단일수
  bl_reason   MEDIUMTEXT NOT NULL COMMENT '차단사유', -- 차단사유
  unbl_reason MEDIUMTEXT NULL     COMMENT '해제사유' -- 해제사유
)
COMMENT '사용자차단';

-- 사용자차단
ALTER TABLE block
  ADD CONSTRAINT PK_block -- 사용자차단 기본키
    PRIMARY KEY (
      blno -- 사용자차단번호
    );

ALTER TABLE block
  MODIFY COLUMN blno INTEGER NOT NULL AUTO_INCREMENT COMMENT '사용자차단번호';

-- 게시글
ALTER TABLE article
  ADD CONSTRAINT FK_user_TO_article -- 유저 -> 게시글
    FOREIGN KEY (
      writer -- 작성자
    )
    REFERENCES user ( -- 유저
      uno -- 사용자번호
    );

-- 게시글
ALTER TABLE article
  ADD CONSTRAINT FK_region_TO_article -- 지역 -> 게시글
    FOREIGN KEY (
      rgno -- 스터디_지역번호
    )
    REFERENCES region ( -- 지역
      rgno -- 지역번호
    );

-- 게시글
ALTER TABLE article
  ADD CONSTRAINT FK_category_TO_article -- 카테고리 -> 게시글
    FOREIGN KEY (
      cano -- 카테고리번호
    )
    REFERENCES category ( -- 카테고리
      cano -- 카테고리번호
    );

-- 댓글
ALTER TABLE comment
  ADD CONSTRAINT FK_user_TO_comment -- 유저 -> 댓글
    FOREIGN KEY (
      uno -- 작성자
    )
    REFERENCES user ( -- 유저
      uno -- 사용자번호
    );

-- 댓글
ALTER TABLE comment
  ADD CONSTRAINT FK_article_TO_comment -- 게시글 -> 댓글
    FOREIGN KEY (
      arno -- 게시글번호
    )
    REFERENCES article ( -- 게시글
      arno -- 게시글번호
    );

-- 뱃지
ALTER TABLE badge
  ADD CONSTRAINT FK_tag_TO_badge -- 태그 -> 뱃지
    FOREIGN KEY (
      tno -- 태그번호
    )
    REFERENCES tag ( -- 태그
      tno -- 태그번호
    );

-- 뱃지평가기준
ALTER TABLE badge_standard
  ADD CONSTRAINT FK_badge_TO_badge_standard -- 뱃지 -> 뱃지평가기준
    FOREIGN KEY (
      bno -- 뱃지번호
    )
    REFERENCES badge ( -- 뱃지
      bno -- 뱃지번호
    );

-- 뱃지평가기준
ALTER TABLE badge_standard
  ADD CONSTRAINT FK_badge_evaluation_TO_badge_standard -- 뱃지평가 -> 뱃지평가기준
    FOREIGN KEY (
      bsname -- 뱃지평가명번호
    )
    REFERENCES badge_evaluation ( -- 뱃지평가
      beno -- 뱃지평가명번호
    );

-- 게시글-태그
ALTER TABLE article_tag
  ADD CONSTRAINT FK_article_TO_article_tag -- 게시글 -> 게시글-태그
    FOREIGN KEY (
      cano -- 게시글번호
    )
    REFERENCES article ( -- 게시글
      arno -- 게시글번호
    );

-- 게시글-태그
ALTER TABLE article_tag
  ADD CONSTRAINT FK_tag_TO_article_tag -- 태그 -> 게시글-태그
    FOREIGN KEY (
      tno -- 태그번호
    )
    REFERENCES tag ( -- 태그
      tno -- 태그번호
    );

-- 팔로우
ALTER TABLE follow
  ADD CONSTRAINT FK_user_TO_follow -- 유저 -> 팔로우2
    FOREIGN KEY (
      follower_no -- 팔로워번호
    )
    REFERENCES user ( -- 유저
      uno -- 사용자번호
    );

-- 팔로우
ALTER TABLE follow
  ADD CONSTRAINT FK_user_TO_follow2 -- 유저 -> 팔로우
    FOREIGN KEY (
      following_no -- 팔로잉번호
    )
    REFERENCES user ( -- 유저
      uno -- 사용자번호
    );

-- 유저_뱃지
ALTER TABLE user_badge
  ADD CONSTRAINT FK_user_TO_user_badge -- 유저 -> 유저_뱃지
    FOREIGN KEY (
      uno -- 사용자번호
    )
    REFERENCES user ( -- 유저
      uno -- 사용자번호
    );

-- 유저_뱃지
ALTER TABLE user_badge
  ADD CONSTRAINT FK_badge_TO_user_badge -- 뱃지 -> 유저_뱃지
    FOREIGN KEY (
      bno -- 뱃지번호
    )
    REFERENCES badge ( -- 뱃지
      bno -- 뱃지번호
    );

-- 게시글 첨부 사진
ALTER TABLE article_photo
  ADD CONSTRAINT FK_article_TO_article_photo -- 게시글 -> 게시글 첨부 사진
    FOREIGN KEY (
      arno -- 게시글번호
    )
    REFERENCES article ( -- 게시글
      arno -- 게시글번호
    );

-- 신고
ALTER TABLE report
  ADD CONSTRAINT FK_report_type_TO_report -- 신고 유형 -> 신고
    FOREIGN KEY (
      rptno -- 신고유형번호
    )
    REFERENCES report_type ( -- 신고 유형
      rptno -- 신고유형번호
    );

-- 신고
ALTER TABLE report
  ADD CONSTRAINT FK_user_TO_report -- 유저 -> 신고
    FOREIGN KEY (
      uno -- 신고자
    )
    REFERENCES user ( -- 유저
      uno -- 사용자번호
    );

-- 유저_북마크_게시글
ALTER TABLE user_bookmark_article
  ADD CONSTRAINT FK_user_TO_user_bookmark_article -- 유저 -> 유저_북마크_게시글
    FOREIGN KEY (
      uno -- 사용자번호
    )
    REFERENCES user ( -- 유저
      uno -- 사용자번호
    );

-- 유저_북마크_게시글
ALTER TABLE user_bookmark_article
  ADD CONSTRAINT FK_article_TO_user_bookmark_article -- 게시글 -> 유저_북마크_게시글
    FOREIGN KEY (
      arno -- 게시글번호
    )
    REFERENCES article ( -- 게시글
      arno -- 게시글번호
    );

-- 알람
ALTER TABLE notification
  ADD CONSTRAINT FK_user_TO_notification -- 유저 -> 알람
    FOREIGN KEY (
      uno -- 사용자번호
    )
    REFERENCES user ( -- 유저
      uno -- 사용자번호
    );

-- 유저_팔로우_태그
ALTER TABLE user_follow_tag
  ADD CONSTRAINT FK_user_TO_user_follow_tag -- 유저 -> 유저_팔로우_태그
    FOREIGN KEY (
      uno -- 사용자번호
    )
    REFERENCES user ( -- 유저
      uno -- 사용자번호
    );

-- 유저_팔로우_태그
ALTER TABLE user_follow_tag
  ADD CONSTRAINT FK_tag_TO_user_follow_tag -- 태그 -> 유저_팔로우_태그
    FOREIGN KEY (
      tno -- 태그번호
    )
    REFERENCES tag ( -- 태그
      tno -- 태그번호
    );

-- 게시글신고
ALTER TABLE report_article
  ADD CONSTRAINT FK_article_TO_report_article -- 게시글 -> 게시글신고
    FOREIGN KEY (
      arno -- 게시글번호
    )
    REFERENCES article ( -- 게시글
      arno -- 게시글번호
    );

-- 게시글신고
ALTER TABLE report_article
  ADD CONSTRAINT FK_report_TO_report_article -- 신고 -> 게시글신고
    FOREIGN KEY (
      rpno -- 신고번호
    )
    REFERENCES report ( -- 신고
      rpno -- 신고번호
    );

-- 댓글신고
ALTER TABLE report_comment
  ADD CONSTRAINT FK_comment_TO_report_comment -- 댓글 -> 댓글신고
    FOREIGN KEY (
      cno -- 댓글번호
    )
    REFERENCES comment ( -- 댓글
      cno -- 댓글번호
    );

-- 댓글신고
ALTER TABLE report_comment
  ADD CONSTRAINT FK_report_TO_report_comment -- 신고 -> 댓글신고
    FOREIGN KEY (
      rpno -- 신고번호
    )
    REFERENCES report ( -- 신고
      rpno -- 신고번호
    );

-- 사용자차단
ALTER TABLE block
  ADD CONSTRAINT FK_user_TO_block -- 유저 -> 사용자차단
    FOREIGN KEY (
      uno -- 사용자번호
    )
    REFERENCES user ( -- 유저
      uno -- 사용자번호
    );