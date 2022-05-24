-- uesr
INSERT INTO beamo.users
(seq, acount_name, acount_num, created_dt, name, `point`, profile, updated_dt)
VALUES(1, "국민 은행", 123123,now(), "이름", 100000, "test_url", now());

-- owner
INSERT INTO beamo.owner
(seq, acount_name, acount_num, created_dt, name, `point`, profile, updated_dt)
VALUES(1, "국민 은행", 123123,now(), "이름", 100000, "test_url", now());

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