-- 유저
DROP TABLE IF EXISTS `user` RESTRICT;

-- 게시글
DROP TABLE IF EXISTS `article` RESTRICT;

-- 댓글
DROP TABLE IF EXISTS `comment` RESTRICT;

-- 뱃지
DROP TABLE IF EXISTS `badge` RESTRICT;

-- 뱃지평가기준
DROP TABLE IF EXISTS `bdg_stan` RESTRICT;

-- 태그
DROP TABLE IF EXISTS `tag` RESTRICT;

-- 게시글_태그
DROP TABLE IF EXISTS `arc_tag` RESTRICT;

-- 팔로우
DROP TABLE IF EXISTS `follow` RESTRICT;

-- 유저_뱃지
DROP TABLE IF EXISTS `usr_bdg` RESTRICT;

-- 지역
DROP TABLE IF EXISTS `region` RESTRICT;

-- 게시글 첨부 사진
DROP TABLE IF EXISTS `arc_pht` RESTRICT;

-- 신고
DROP TABLE IF EXISTS `report` RESTRICT;

-- 유저_북마크_게시글
DROP TABLE IF EXISTS `usr_bmk_arc` RESTRICT;

-- 알람
DROP TABLE IF EXISTS `noti` RESTRICT;

-- 유저_팔로우_태그
DROP TABLE IF EXISTS `usr_tag` RESTRICT;

-- 신고 유형
DROP TABLE IF EXISTS `repo_type` RESTRICT;

-- 뱃지평가
DROP TABLE IF EXISTS `bdg_eva` RESTRICT;

-- 카테고리
DROP TABLE IF EXISTS `category` RESTRICT;

-- 게시글신고
DROP TABLE IF EXISTS `repo_arc` RESTRICT;

-- 댓글신고
DROP TABLE IF EXISTS `repo_comt` RESTRICT;

-- 사용자차단
DROP TABLE IF EXISTS `block` RESTRICT;

-- 지역_시군구
DROP TABLE IF EXISTS `relo` RESTRICT;

-- 유저
CREATE TABLE `user` (
  `uno`      INTEGER      NOT NULL, -- 사용자번호
  `email`    VARCHAR(40)  NOT NULL, -- 이메일
  `nick`     VARCHAR(50)  NULL,     -- 닉네임
  `name`     VARCHAR(50)  NOT NULL, -- 이름
  `pwd`      VARCHAR(255) NULL,     -- 암호
  `cdt`      DATE         NOT NULL DEFAULT now(), -- 가입일
  `rnt_vsdt` DATETIME     NULL,     -- 최종방문일
  `log_type` VARCHAR(50)  NOT NULL, -- 로그인유형
  `photo`    VARCHAR(255) NULL,     -- 사진
  `hp_url`   VARCHAR(255) NULL,     -- 개인홈피주소
  `gh_url`   VARCHAR(255) NULL,     -- 깃허브주소
  `in_url`   VARCHAR(255) NULL,     -- 인스타주소
  `tw_url`   VARCHAR(255) NULL,     -- 트위터주소
  `blocked`  INTEGER      NOT NULL DEFAULT 0, -- 차단여부
  `tech`     MEDIUMTEXT   NULL,     -- 기술스택
  `auth`     INTEGER      NOT NULL DEFAULT 0, -- 관리자여부
  `noti`     INTEGER      NOT NULL DEFAULT 0 -- 알람알림여부
);

-- 유저
ALTER TABLE `user`
  ADD CONSTRAINT `PK_user` -- 유저 기본키
    PRIMARY KEY (
      `uno` -- 사용자번호
    );

-- 이메일 유니크
CREATE UNIQUE INDEX `UIX_email`
  ON `user` ( -- 유저
    `email` ASC -- 이메일
  );

-- 닉네임 유니크
CREATE UNIQUE INDEX `UIX_nick`
  ON `user` ( -- 유저
    `nick` ASC -- 닉네임
  );

ALTER TABLE `user`
  MODIFY COLUMN `uno` INTEGER NOT NULL AUTO_INCREMENT;

-- 게시글
CREATE TABLE `article` (
  `arno`      INTEGER      NOT NULL, -- 게시글번호
  `writer`    INTEGER      NOT NULL, -- 작성자
  `cano`      INTEGER      NOT NULL, -- 카테고리번호
  `title`     VARCHAR(255) NOT NULL, -- 게시글제목
  `content`   MEDIUMTEXT   NULL,     -- 내용
  `cdt`       DATETIME     NOT NULL DEFAULT now(), -- 등록일자
  `vw_cnt`    INTEGER      NOT NULL DEFAULT 0, -- 조회수
  `udt`       DATETIME     NULL,     -- 수정일자
  `ddt`       DATETIME     NULL,     -- 삭제일자
  `status`    INTEGER      NOT NULL DEFAULT 0, -- 활성상태
  `st_relo`   INTEGER      NULL,     -- 시군구번호
  `st_status` INTEGER      NULL     DEFAULT 0, -- 모집상태
  `rc_edt`    DATE         NULL      -- 모집마감일
);

-- 게시글
ALTER TABLE `article`
  ADD CONSTRAINT `PK_article` -- 게시글 기본키
    PRIMARY KEY (
      `arno` -- 게시글번호
    );

ALTER TABLE `article`
  MODIFY COLUMN `arno` INTEGER NOT NULL AUTO_INCREMENT;

-- 댓글
CREATE TABLE `comment` (
  `cno`      INTEGER    NOT NULL, -- 댓글번호
  `arno`     INTEGER    NOT NULL, -- 게시글번호
  `uno`      INTEGER    NOT NULL, -- 작성자
  `content`  MEDIUMTEXT NOT NULL, -- 내용
  `cdt`      DATETIME   NOT NULL DEFAULT now(), -- 등록일자
  `ord`      INTEGER    NULL,     -- 댓글순서
  `step`     INTEGER    NULL,     -- 댓글단계
  `udt`      DATETIME   NULL,     -- 수정일자
  `status`   INTEGER    NOT NULL DEFAULT 0, -- 활성상태
  `ddt`      DATETIME   NULL,     -- 삭제일자
  `selected` INTEGER    NOT NULL DEFAULT 0 -- 대표답변여부
);

-- 댓글
ALTER TABLE `comment`
  ADD CONSTRAINT `PK_comment` -- 댓글 기본키
    PRIMARY KEY (
      `cno` -- 댓글번호
    );

ALTER TABLE `comment`
  MODIFY COLUMN `cno` INTEGER NOT NULL AUTO_INCREMENT;

-- 뱃지
CREATE TABLE `badge` (
  `bno`   INTEGER      NOT NULL, -- 뱃지번호
  `name`  VARCHAR(50)  NOT NULL, -- 뱃지이름
  `photo` VARCHAR(255) NOT NULL, -- 사진
  `tno`   INTEGER      NULL      -- 태그번호
);

-- 뱃지
ALTER TABLE `badge`
  ADD CONSTRAINT `PK_badge` -- 뱃지 기본키
    PRIMARY KEY (
      `bno` -- 뱃지번호
    );

ALTER TABLE `badge`
  MODIFY COLUMN `bno` INTEGER NOT NULL AUTO_INCREMENT;

-- 뱃지평가기준
CREATE TABLE `bdg_stan` (
  `bsno`   INTEGER NOT NULL, -- 뱃지평가기준번호
  `bno`    INTEGER NOT NULL, -- 뱃지번호
  `bsname` INTEGER NOT NULL, -- 뱃지평가명번호
  `count`  INTEGER NULL      -- 기준횟수
);

-- 뱃지평가기준
ALTER TABLE `bdg_stan`
  ADD CONSTRAINT `PK_bdg_stan` -- 뱃지평가기준 기본키
    PRIMARY KEY (
      `bsno` -- 뱃지평가기준번호
    );

ALTER TABLE `bdg_stan`
  MODIFY COLUMN `bsno` INTEGER NOT NULL AUTO_INCREMENT;

-- 태그
CREATE TABLE `tag` (
  `tno`   INTEGER      NOT NULL, -- 태그번호
  `name`  VARCHAR(50)  NOT NULL, -- 태그이름
  `photo` VARCHAR(255) NULL,     -- 사진
  `color` VARCHAR(50)  NOT NULL DEFAULT "#6C5DDF" -- 색(hex)
);

-- 태그
ALTER TABLE `tag`
  ADD CONSTRAINT `PK_tag` -- 태그 기본키
    PRIMARY KEY (
      `tno` -- 태그번호
    );

-- 태그이름 유니크키
CREATE UNIQUE INDEX `UIX_tag`
  ON `tag` ( -- 태그
    `name` ASC -- 태그이름
  );

ALTER TABLE `tag`
  MODIFY COLUMN `tno` INTEGER NOT NULL AUTO_INCREMENT;

-- 게시글_태그
CREATE TABLE `arc_tag` (
  `cano` INTEGER NOT NULL, -- 게시글번호
  `tno`  INTEGER NOT NULL  -- 태그번호
);

-- 게시글_태그
ALTER TABLE `arc_tag`
  ADD CONSTRAINT `PK_arc_tag` -- 게시글_태그 기본키
    PRIMARY KEY (
      `cano`, -- 게시글번호
      `tno`   -- 태그번호
    );

-- 팔로우
CREATE TABLE `follow` (
  `fwing_no` INTEGER  NOT NULL, -- 팔로잉번호
  `fwer_no`  INTEGER  NOT NULL, -- 팔로워번호
  `cdt`      DATETIME NOT NULL DEFAULT now() -- 생성일
);

-- 팔로우
ALTER TABLE `follow`
  ADD CONSTRAINT `PK_follow` -- 팔로우 기본키
    PRIMARY KEY (
      `fwing_no`, -- 팔로잉번호
      `fwer_no`   -- 팔로워번호
    );

-- 유저_뱃지
CREATE TABLE `usr_bdg` (
  `uno` INTEGER NOT NULL, -- 사용자번호
  `bno` INTEGER NOT NULL, -- 뱃지번호
  `adt` DATE    NOT NULL DEFAULT now() -- 수여일
);

-- 유저_뱃지
ALTER TABLE `usr_bdg`
  ADD CONSTRAINT `PK_usr_bdg` -- 유저_뱃지 기본키
    PRIMARY KEY (
      `uno`, -- 사용자번호
      `bno`  -- 뱃지번호
    );

-- 지역
CREATE TABLE `region` (
  `rgno` INTEGER     NOT NULL, -- 지역번호
  `name` VARCHAR(50) NOT NULL  -- 지역명
);

-- 지역
ALTER TABLE `region`
  ADD CONSTRAINT `PK_region` -- 지역 기본키
    PRIMARY KEY (
      `rgno` -- 지역번호
    );

-- 지역명 유니크키
CREATE UNIQUE INDEX `UIX_region`
  ON `region` ( -- 지역
    `name` ASC -- 지역명
  );

-- 게시글 첨부 사진
CREATE TABLE `arc_pht` (
  `pno`  INTEGER      NOT NULL, -- 사진번호
  `arno` INTEGER      NOT NULL, -- 게시글번호
  `adr`  VARCHAR(255) NOT NULL  -- 사진주소
);

-- 게시글 첨부 사진
ALTER TABLE `arc_pht`
  ADD CONSTRAINT `PK_arc_pht` -- 게시글 첨부 사진 기본키
    PRIMARY KEY (
      `pno` -- 사진번호
    );

ALTER TABLE `arc_pht`
  MODIFY COLUMN `pno` INTEGER NOT NULL AUTO_INCREMENT;

-- 신고
CREATE TABLE `report` (
  `rpno`   INTEGER  NOT NULL, -- 신고번호
  `rptno`  INTEGER  NOT NULL, -- 신고유형번호
  `uno`    INTEGER  NOT NULL, -- 신고자
  `cdt`    DATETIME NOT NULL DEFAULT now(), -- 신고일
  `status` INTEGER  NULL     DEFAULT 0, -- 처리상태
  `pdt`    DATETIME NULL      -- 처리일
);

-- 신고
ALTER TABLE `report`
  ADD CONSTRAINT `PK_report` -- 신고 기본키
    PRIMARY KEY (
      `rpno` -- 신고번호
    );

ALTER TABLE `report`
  MODIFY COLUMN `rpno` INTEGER NOT NULL AUTO_INCREMENT;

-- 유저_북마크_게시글
CREATE TABLE `usr_bmk_arc` (
  `uno`  INTEGER  NOT NULL, -- 사용자번호
  `arno` INTEGER  NOT NULL, -- 게시글번호
  `cdt`  DATETIME NOT NULL DEFAULT now() -- 북마크일시
);

-- 유저_북마크_게시글
ALTER TABLE `usr_bmk_arc`
  ADD CONSTRAINT `PK_usr_bmk_arc` -- 유저_북마크_게시글 기본키
    PRIMARY KEY (
      `uno`,  -- 사용자번호
      `arno`  -- 게시글번호
    );

-- 알람
CREATE TABLE `noti` (
  `nno`     INTEGER      NOT NULL, -- 알람번호
  `uno`     INTEGER      NOT NULL, -- 사용자번호
  `cdt`     DATETIME     NOT NULL DEFAULT now(), -- 알람생성일
  `content` MEDIUMTEXT   NULL,     -- 알람내용
  `type`    VARCHAR(50)  NOT NULL, -- 알람타입
  `url`     VARCHAR(255) NULL      --  URL
);

-- 알람
ALTER TABLE `noti`
  ADD CONSTRAINT `PK_noti` -- 알람 기본키
    PRIMARY KEY (
      `nno` -- 알람번호
    );

ALTER TABLE `noti`
  MODIFY COLUMN `nno` INTEGER NOT NULL AUTO_INCREMENT;

-- 유저_팔로우_태그
CREATE TABLE `usr_tag` (
  `uno` INTEGER NOT NULL, -- 사용자번호
  `tno` INTEGER NOT NULL, -- 태그번호
  `cdt` DATE    NOT NULL DEFAULT now() -- 팔로우일시
);

-- 유저_팔로우_태그
ALTER TABLE `usr_tag`
  ADD CONSTRAINT `PK_usr_tag` -- 유저_팔로우_태그 기본키
    PRIMARY KEY (
      `uno`, -- 사용자번호
      `tno`  -- 태그번호
    );

-- 신고 유형
CREATE TABLE `repo_type` (
  `rptno` INTEGER     NOT NULL, -- 신고유형번호
  `name`  VARCHAR(50) NOT NULL  -- 신고유형명
);

-- 신고 유형
ALTER TABLE `repo_type`
  ADD CONSTRAINT `PK_repo_type` -- 신고 유형 기본키
    PRIMARY KEY (
      `rptno` -- 신고유형번호
    );

-- 신고 유형 유니크 인덱스
CREATE UNIQUE INDEX `UIX_repo_type`
  ON `repo_type` ( -- 신고 유형
    `name` ASC -- 신고유형명
  );

-- 뱃지평가
CREATE TABLE `bdg_eva` (
  `beno`   INTEGER     NOT NULL, -- 뱃지평가명번호
  `bename` VARCHAR(50) NOT NULL  -- 평가명
);

-- 뱃지평가
ALTER TABLE `bdg_eva`
  ADD CONSTRAINT `PK_bdg_eva` -- 뱃지평가 기본키
    PRIMARY KEY (
      `beno` -- 뱃지평가명번호
    );

ALTER TABLE `bdg_eva`
  MODIFY COLUMN `beno` INTEGER NOT NULL AUTO_INCREMENT;

-- 카테고리
CREATE TABLE `category` (
  `cano` INTEGER     NOT NULL, -- 카테고리번호
  `name` VARCHAR(50) NOT NULL  -- 카테고리명
);

-- 카테고리
ALTER TABLE `category`
  ADD CONSTRAINT `PK_category` -- 카테고리 기본키
    PRIMARY KEY (
      `cano` -- 카테고리번호
    );

-- 분류명 유니크키
CREATE UNIQUE INDEX `UIX_category`
  ON `category` ( -- 카테고리
    `name` ASC -- 카테고리명
  );

-- 게시글신고
CREATE TABLE `repo_arc` (
  `rpno` INTEGER NOT NULL, -- 신고번호
  `arno` INTEGER NOT NULL  -- 게시글번호
);

-- 게시글신고
ALTER TABLE `repo_arc`
  ADD CONSTRAINT `PK_repo_arc` -- 게시글신고 기본키
    PRIMARY KEY (
      `rpno` -- 신고번호
    );

-- 댓글신고
CREATE TABLE `repo_comt` (
  `rpno` INTEGER NOT NULL, -- 신고번호
  `cno`  INTEGER NOT NULL  -- 댓글번호
);

-- 댓글신고
ALTER TABLE `repo_comt`
  ADD CONSTRAINT `PK_repo_comt` -- 댓글신고 기본키
    PRIMARY KEY (
      `rpno` -- 신고번호
    );

-- 사용자차단
CREATE TABLE `block` (
  `blno`     INTEGER    NOT NULL, -- 사용자차단번호
  `uno`      INTEGER    NOT NULL, -- 사용자번호
  `cdt`      DATE       NOT NULL DEFAULT now(), -- 차단일자
  `dates`    INTEGER    NOT NULL, -- 차단일수
  `bl_rsn`   MEDIUMTEXT NOT NULL, -- 차단사유
  `unbl_rsn` MEDIUMTEXT NULL      -- 해제사유
);

-- 사용자차단
ALTER TABLE `block`
  ADD CONSTRAINT `PK_block` -- 사용자차단 기본키
    PRIMARY KEY (
      `blno` -- 사용자차단번호
    );

ALTER TABLE `block`
  MODIFY COLUMN `blno` INTEGER NOT NULL AUTO_INCREMENT;

-- 지역_시군구
CREATE TABLE `relo` (
  `relo` INTEGER     NOT NULL, -- 시군구번호
  `rgno` INTEGER     NOT NULL, -- 지역번호
  `name` VARCHAR(50) NOT NULL  -- 시군구이름
);

-- 지역_시군구
ALTER TABLE `relo`
  ADD CONSTRAINT `PK_relo` -- 지역_시군구 기본키
    PRIMARY KEY (
      `relo` -- 시군구번호
    );

-- 지역_시군구 유니크 인덱스
CREATE UNIQUE INDEX `UIX_relo`
  ON `relo` ( -- 지역_시군구
    `name` ASC -- 시군구이름
  );

-- 게시글
ALTER TABLE `article`
  ADD CONSTRAINT `FK_user_TO_article` -- 유저 -> 게시글
    FOREIGN KEY (
      `writer` -- 작성자
    )
    REFERENCES `user` ( -- 유저
      `uno` -- 사용자번호
    );

-- 게시글
ALTER TABLE `article`
  ADD CONSTRAINT `FK_category_TO_article` -- 카테고리 -> 게시글
    FOREIGN KEY (
      `cano` -- 카테고리번호
    )
    REFERENCES `category` ( -- 카테고리
      `cano` -- 카테고리번호
    );

-- 게시글
ALTER TABLE `article`
  ADD CONSTRAINT `FK_relo_TO_article` -- 지역_시군구 -> 게시글
    FOREIGN KEY (
      `st_relo` -- 시군구번호
    )
    REFERENCES `relo` ( -- 지역_시군구
      `relo` -- 시군구번호
    );

-- 댓글
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_user_TO_comment` -- 유저 -> 댓글
    FOREIGN KEY (
      `uno` -- 작성자
    )
    REFERENCES `user` ( -- 유저
      `uno` -- 사용자번호
    );

-- 댓글
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_article_TO_comment` -- 게시글 -> 댓글
    FOREIGN KEY (
      `arno` -- 게시글번호
    )
    REFERENCES `article` ( -- 게시글
      `arno` -- 게시글번호
    );

-- 뱃지
ALTER TABLE `badge`
  ADD CONSTRAINT `FK_tag_TO_badge` -- 태그 -> 뱃지
    FOREIGN KEY (
      `tno` -- 태그번호
    )
    REFERENCES `tag` ( -- 태그
      `tno` -- 태그번호
    );

-- 뱃지평가기준
ALTER TABLE `bdg_stan`
  ADD CONSTRAINT `FK_badge_TO_bdg_stan` -- 뱃지 -> 뱃지평가기준
    FOREIGN KEY (
      `bno` -- 뱃지번호
    )
    REFERENCES `badge` ( -- 뱃지
      `bno` -- 뱃지번호
    );

-- 뱃지평가기준
ALTER TABLE `bdg_stan`
  ADD CONSTRAINT `FK_bdg_eva_TO_bdg_stan` -- 뱃지평가 -> 뱃지평가기준
    FOREIGN KEY (
      `bsname` -- 뱃지평가명번호
    )
    REFERENCES `bdg_eva` ( -- 뱃지평가
      `beno` -- 뱃지평가명번호
    );

-- 게시글_태그
ALTER TABLE `arc_tag`
  ADD CONSTRAINT `FK_article_TO_arc_tag` -- 게시글 -> 게시글_태그
    FOREIGN KEY (
      `cano` -- 게시글번호
    )
    REFERENCES `article` ( -- 게시글
      `arno` -- 게시글번호
    );

-- 게시글_태그
ALTER TABLE `arc_tag`
  ADD CONSTRAINT `FK_tag_TO_arc_tag` -- 태그 -> 게시글_태그
    FOREIGN KEY (
      `tno` -- 태그번호
    )
    REFERENCES `tag` ( -- 태그
      `tno` -- 태그번호
    );

-- 팔로우
ALTER TABLE `follow`
  ADD CONSTRAINT `FK_user_TO_follow` -- 유저 -> 팔로우2
    FOREIGN KEY (
      `fwer_no` -- 팔로워번호
    )
    REFERENCES `user` ( -- 유저
      `uno` -- 사용자번호
    );

-- 팔로우
ALTER TABLE `follow`
  ADD CONSTRAINT `FK_user_TO_follow2` -- 유저 -> 팔로우
    FOREIGN KEY (
      `fwing_no` -- 팔로잉번호
    )
    REFERENCES `user` ( -- 유저
      `uno` -- 사용자번호
    );

-- 유저_뱃지
ALTER TABLE `usr_bdg`
  ADD CONSTRAINT `FK_user_TO_usr_bdg` -- 유저 -> 유저_뱃지
    FOREIGN KEY (
      `uno` -- 사용자번호
    )
    REFERENCES `user` ( -- 유저
      `uno` -- 사용자번호
    );

-- 유저_뱃지
ALTER TABLE `usr_bdg`
  ADD CONSTRAINT `FK_badge_TO_usr_bdg` -- 뱃지 -> 유저_뱃지
    FOREIGN KEY (
      `bno` -- 뱃지번호
    )
    REFERENCES `badge` ( -- 뱃지
      `bno` -- 뱃지번호
    );

-- 게시글 첨부 사진
ALTER TABLE `arc_pht`
  ADD CONSTRAINT `FK_article_TO_arc_pht` -- 게시글 -> 게시글 첨부 사진
    FOREIGN KEY (
      `arno` -- 게시글번호
    )
    REFERENCES `article` ( -- 게시글
      `arno` -- 게시글번호
    );

-- 신고
ALTER TABLE `report`
  ADD CONSTRAINT `FK_repo_type_TO_report` -- 신고 유형 -> 신고
    FOREIGN KEY (
      `rptno` -- 신고유형번호
    )
    REFERENCES `repo_type` ( -- 신고 유형
      `rptno` -- 신고유형번호
    );

-- 신고
ALTER TABLE `report`
  ADD CONSTRAINT `FK_user_TO_report` -- 유저 -> 신고
    FOREIGN KEY (
      `uno` -- 신고자
    )
    REFERENCES `user` ( -- 유저
      `uno` -- 사용자번호
    );

-- 유저_북마크_게시글
ALTER TABLE `usr_bmk_arc`
  ADD CONSTRAINT `FK_user_TO_usr_bmk_arc` -- 유저 -> 유저_북마크_게시글
    FOREIGN KEY (
      `uno` -- 사용자번호
    )
    REFERENCES `user` ( -- 유저
      `uno` -- 사용자번호
    );

-- 유저_북마크_게시글
ALTER TABLE `usr_bmk_arc`
  ADD CONSTRAINT `FK_article_TO_usr_bmk_arc` -- 게시글 -> 유저_북마크_게시글
    FOREIGN KEY (
      `arno` -- 게시글번호
    )
    REFERENCES `article` ( -- 게시글
      `arno` -- 게시글번호
    );

-- 알람
ALTER TABLE `noti`
  ADD CONSTRAINT `FK_user_TO_noti` -- 유저 -> 알람
    FOREIGN KEY (
      `uno` -- 사용자번호
    )
    REFERENCES `user` ( -- 유저
      `uno` -- 사용자번호
    );

-- 유저_팔로우_태그
ALTER TABLE `usr_tag`
  ADD CONSTRAINT `FK_user_TO_usr_tag` -- 유저 -> 유저_팔로우_태그
    FOREIGN KEY (
      `uno` -- 사용자번호
    )
    REFERENCES `user` ( -- 유저
      `uno` -- 사용자번호
    );

-- 유저_팔로우_태그
ALTER TABLE `usr_tag`
  ADD CONSTRAINT `FK_tag_TO_usr_tag` -- 태그 -> 유저_팔로우_태그
    FOREIGN KEY (
      `tno` -- 태그번호
    )
    REFERENCES `tag` ( -- 태그
      `tno` -- 태그번호
    );

-- 게시글신고
ALTER TABLE `repo_arc`
  ADD CONSTRAINT `FK_article_TO_repo_arc` -- 게시글 -> 게시글신고
    FOREIGN KEY (
      `arno` -- 게시글번호
    )
    REFERENCES `article` ( -- 게시글
      `arno` -- 게시글번호
    );

-- 게시글신고
ALTER TABLE `repo_arc`
  ADD CONSTRAINT `FK_report_TO_repo_arc` -- 신고 -> 게시글신고
    FOREIGN KEY (
      `rpno` -- 신고번호
    )
    REFERENCES `report` ( -- 신고
      `rpno` -- 신고번호
    );

-- 댓글신고
ALTER TABLE `repo_comt`
  ADD CONSTRAINT `FK_comment_TO_repo_comt` -- 댓글 -> 댓글신고
    FOREIGN KEY (
      `cno` -- 댓글번호
    )
    REFERENCES `comment` ( -- 댓글
      `cno` -- 댓글번호
    );

-- 댓글신고
ALTER TABLE `repo_comt`
  ADD CONSTRAINT `FK_report_TO_repo_comt` -- 신고 -> 댓글신고
    FOREIGN KEY (
      `rpno` -- 신고번호
    )
    REFERENCES `report` ( -- 신고
      `rpno` -- 신고번호
    );

-- 사용자차단
ALTER TABLE `block`
  ADD CONSTRAINT `FK_user_TO_block` -- 유저 -> 사용자차단
    FOREIGN KEY (
      `uno` -- 사용자번호
    )
    REFERENCES `user` ( -- 유저
      `uno` -- 사용자번호
    );

-- 지역_시군구
ALTER TABLE `relo`
  ADD CONSTRAINT `FK_region_TO_relo` -- 지역 -> 지역_시군구
    FOREIGN KEY (
      `rgno` -- 지역번호
    )
    REFERENCES `region` ( -- 지역
      `rgno` -- 지역번호
    );