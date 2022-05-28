-- uesr
INSERT INTO beamo.users
(seq, acount_name, acount_num, created_dt, name, `point`, profile, updated_dt)
VALUES(1, "국민 은행", 123123,now(), "장미란", 100000, "test_url", now());
-- uesr
INSERT INTO beamo.users
(seq, acount_name, acount_num, created_dt, name, `point`, profile, updated_dt)
VALUES(2, "국민 은행", 123123,now(), "박지성", 100000, "test_url", now());
-- uesr
INSERT INTO beamo.users
(seq, acount_name, acount_num, created_dt, name, `point`, profile, updated_dt)
VALUES(3, "국민 은행", 123123,now(), "김연아", 100000, "test_url", now());
-- uesr
INSERT INTO beamo.users
(seq, acount_name, acount_num, created_dt, name, `point`, profile, updated_dt)
VALUES(4, "국민 은행", 123123,now(), "해커스", 100000, "test_url", now());

-- owner
INSERT INTO beamo.owner
(seq, acount_name, acount_num, created_dt, name, `point`, profile, updated_dt)
VALUES(1, "국민 은행", 123123,now(), "사장님", 100000, "test_url", now());

-- restaurant
INSERT INTO beamo.restaurant
(adress, category, created_dt, delivery_price, img, latitude, longitude, max_member, name, phone, updated_dt, owner_seq)
VALUES("무안군", "치킨",now(), 4000, "url", NULL, NULL, 3, "치킨집", 0, now(), 1);

-- munu
INSERT INTO beamo.munu
(category, count, img, name, price, restaurant_seq)
VALUES("메인 메뉴", 1, "url", "best chiken", 15000, 1);

INSERT INTO beamo.munu
(category, count, img, name, price, restaurant_seq)
VALUES("메인 메뉴", 1, "url", "두 chiken", 17000, 1);

INSERT INTO beamo.munu
(category, count, img, name, price, restaurant_seq)
VALUES("메인 메뉴", 1, "url", "셋 chiken", 19000, 1);

-- side menu
INSERT INTO beamo.side_munu
(category, count, name, price, menu_seq)
VALUES("사이드", 0, "치즈볼 5개", 5000, 2);
INSERT INTO beamo.side_munu
(category, count, name, price, menu_seq)
VALUES("사이드", 0, "치즈볼 10개", 8000, 3);
INSERT INTO beamo.side_munu
(category, count, name, price, menu_seq)
VALUES("음료", 0, "사이다", 2000, 1);
INSERT INTO beamo.side_munu
(category, count, name, price, menu_seq)
VALUES("음료", 0, "콜라", 2000, 1);
INSERT INTO beamo.side_munu
(category, count, name, price, menu_seq)
VALUES("음료", 0, "환타", 2000, 1);