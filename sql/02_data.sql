use hm_map_db;
DELETE FROM place WHERE place_id > 0;   -- CASCADE로 place_i18n도 같이 삭제됨

-- 1. 전쟁기념관 (한국/서울)
insert into place (place_id, country_code, category, latitude, longitude)
values (1, 'KR', 'MUSEUM', 37.536500, 126.977200);

insert into place_i18n (place_id, lang, name, address, description) values
(1, 'ko', '전쟁기념관', '서울특별시 용산구 이태원로 29', '한국 전쟁사와 유물을 전시하는 박물관'), 
(1, 'ja', '戦争記念館', 'ソウル特別市 龍山区 梨泰院路 29', '韓国の戦争史と軍事遺物を展示する博物館');

-- 2. 세키가하라 기념관 (일본/기후)
insert into place (place_id, country_code, category, latitude, longitude)
values (2, 'JP', 'MUSEUM', 35.364617, 136.464098);

insert into place_i18n (place_id, lang, name, address, description) values
(2, 'ko', '세키가하라 고전장 기념관', '기후현 후와군 세키가하라초 세키가하라 894-55', '세키가하라 전투를 알아볼 수 있는 기념관'), 
(2, 'ja', '岐阜関ケ原古戦場記念館', '岐阜県不破郡関ケ原町大字関ケ原894-55', '岐阜関ケ原戦闘を');

-- 3. 육군박물관 (한국/서울) 좌표없음 보류
/*
insert into place (place_id, country_code, category, latitude, longitude)
values (1, 'KR', 'MUSEUM');

insert into place_i18n (place_id, lang, name, address, description) values
(3, 'ko', '육군사관학교 육군박물관', '서울특별시 노원구 화랑로 574', '한국의 고대부터 근대까지의 군사 유물을 전시하는 박물관'), 
(3, 'ja', '陸軍博物館', 'ソウル特別市 蘆原区 花郎路 574', '韓国の');
*/
