USE hm_map_db;

CREATE TABLE member (
  member_id  INT AUTO_INCREMENT PRIMARY KEY,
  login_id   VARCHAR(20)  NOT NULL,
  password   VARCHAR(64)  NOT NULL,                       -- SHA-256 해시는 항상 64글자
  nickname   VARCHAR(20)  NOT NULL,
  lang       CHAR(2)      NOT NULL DEFAULT 'ko',          -- 선호 언어
  role       VARCHAR(10)  NOT NULL DEFAULT 'USER',        -- 권한
  created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT uq_member_login_id UNIQUE (login_id),        -- 아이디 중복 금지
  CONSTRAINT ck_member_lang CHECK (lang IN ('ko', 'ja')),
  CONSTRAINT ck_member_role CHECK (role IN ('USER', 'ADMIN'))
);