CREATE DATABASE hm_map_db
-- utf8은 글자당 3바이트까지만 저장해서 일부 한자와 이모지가 깨집니다. 일본어를 다루니 4바이트까지 저장하는 utf8mb4를 씁니다. 
  DEFAULT CHARACTER SET utf8mb4 
  COLLATE utf8mb4_0900_ai_ci;

CREATE USER 'hmmap'@'localhost' IDENTIFIED BY '비밀번호';
GRANT ALL PRIVILEGES ON hm_map_db.* TO 'hmmap'@'localhost';

USE hm_map_db;

CREATE TABLE place (
  place_id     INT AUTO_INCREMENT PRIMARY KEY, 
  country_code CHAR(2)      NOT NULL,
  category     VARCHAR(20)  NOT NULL,
  latitude     DECIMAL(9,6) NOT NULL,
  longitude    DECIMAL(9,6) NOT NULL,
  image_file   VARCHAR(100),
  is_active    TINYINT(1)   NOT NULL DEFAULT 1,
  created_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT ck_place_country  CHECK (country_code IN ('KR', 'JP')),
  CONSTRAINT ck_place_category CHECK (category IN ('MUSEUM', 'BATTLEFIELD', 'MEMORIAL', 'SHIP'))
);

CREATE TABLE place_i18n (
  place_id    INT NOT NULL,
  lang        CHAR(2) NOT NULL,
  name        VARCHAR(100) NOT NULL,
  address     VARCHAR(200),
  description TEXT,

  -- 제약
  CONSTRAINT pk_place_i18n      PRIMARY KEY (place_id, lang),
  CONSTRAINT fk_place_i18n_place FOREIGN KEY (place_id) REFERENCES place(place_id) ON DELETE CASCADE,
  CONSTRAINT ck_place_i18n_lang CHECK (lang IN ('ko', 'ja'))
);
