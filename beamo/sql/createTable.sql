-- beamo.side_munu definition

CREATE TABLE `side_munu` (
                             `seq` bigint(20) NOT NULL AUTO_INCREMENT,
                             `category` varchar(255) DEFAULT NULL,
                             `count` smallint(6) NOT NULL,
                             `img` varchar(300) DEFAULT NULL,
                             `name` varchar(255) DEFAULT NULL,
                             `price` smallint(6) NOT NULL,
                             PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- beamo.users definition

CREATE TABLE `users` (
                         `seq` bigint(20) NOT NULL,
                         `acount_name` varchar(255) DEFAULT NULL,
                         `acount_num` int(11) DEFAULT NULL,
                         `created_dt` datetime(6) DEFAULT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         `point` int(11) NOT NULL,
                         `profile` varchar(300) DEFAULT NULL,
                         `updated_dt` datetime(6) DEFAULT NULL,
                         PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- beamo.munu definition

CREATE TABLE `munu` (
                        `seq` bigint(20) NOT NULL AUTO_INCREMENT,
                        `category` varchar(255) DEFAULT NULL,
                        `count` smallint(6) NOT NULL,
                        `img` varchar(300) DEFAULT NULL,
                        `name` varchar(255) DEFAULT NULL,
                        `price` smallint(6) NOT NULL,
                        `side_menu_seq` bigint(20) DEFAULT NULL,
                        PRIMARY KEY (`seq`),
                        KEY `FK9d90sbtt37ojqq5r1dhdgnp1d` (`side_menu_seq`),
                        CONSTRAINT `FK9d90sbtt37ojqq5r1dhdgnp1d` FOREIGN KEY (`side_menu_seq`) REFERENCES `side_munu` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- beamo.restaurant definition

CREATE TABLE `restaurant` (
                              `seq` bigint(20) NOT NULL AUTO_INCREMENT,
                              `adress` varchar(255) DEFAULT NULL,
                              `category` varchar(255) DEFAULT NULL,
                              `created_dt` datetime(6) DEFAULT NULL,
                              `delivery_price` smallint(6) DEFAULT NULL,
                              `img` varchar(300) DEFAULT NULL,
                              `latitude` tinyblob DEFAULT NULL,
                              `longitude` tinyblob DEFAULT NULL,
                              `max_member` smallint(6) DEFAULT NULL,
                              `name` varchar(255) DEFAULT NULL,
                              `phone` int(11) NOT NULL,
                              `updated_dt` datetime(6) DEFAULT NULL,
                              `menu_seq` bigint(20) DEFAULT NULL,
                              PRIMARY KEY (`seq`),
                              KEY `FKkp2231v3pm55kofolo30tlxb` (`menu_seq`),
                              CONSTRAINT `FKkp2231v3pm55kofolo30tlxb` FOREIGN KEY (`menu_seq`) REFERENCES `munu` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- beamo.chat_info definition

CREATE TABLE `chat_info` (
                             `seq` bigint(20) NOT NULL AUTO_INCREMENT,
                             `adress` varchar(255) DEFAULT NULL,
                             `created_dt` datetime(6) DEFAULT NULL,
                             `latitude` tinyblob DEFAULT NULL,
                             `longitude` tinyblob DEFAULT NULL,
                             `name` varchar(255) DEFAULT NULL,
                             `order_time` datetime(6) DEFAULT NULL,
                             `personnel` smallint(6) NOT NULL,
                             `updated_dt` datetime(6) DEFAULT NULL,
                             `restaurant_seq` bigint(20) DEFAULT NULL,
                             PRIMARY KEY (`seq`),
                             KEY `FK40fj9sk0vr5h5h9v2g005nw75` (`restaurant_seq`),
                             CONSTRAINT `FK40fj9sk0vr5h5h9v2g005nw75` FOREIGN KEY (`restaurant_seq`) REFERENCES `restaurant` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- beamo.chat_room definition

CREATE TABLE `chat_room` (
                             `seq` bigint(20) NOT NULL AUTO_INCREMENT,
                             `created_dt` datetime(6) DEFAULT NULL,
                             `updated_dt` datetime(6) DEFAULT NULL,
                             `user_seq` bigint(20) NOT NULL,
                             `chat_info` bigint(20) DEFAULT NULL,
                             PRIMARY KEY (`seq`,`user_seq`),
                             KEY `FKpo6kos6u6yr4o19jqwwqjhdkm` (`user_seq`),
                             KEY `FKhtgo4pghihohpe06v5b0ybwmh` (`chat_info`),
                             CONSTRAINT `FKhtgo4pghihohpe06v5b0ybwmh` FOREIGN KEY (`chat_info`) REFERENCES `chat_info` (`seq`),
                             CONSTRAINT `FKpo6kos6u6yr4o19jqwwqjhdkm` FOREIGN KEY (`user_seq`) REFERENCES `users` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- beamo.orders definition

CREATE TABLE `orders` (
                          `seq` bigint(20) NOT NULL AUTO_INCREMENT,
                          `created_dt` datetime(6) DEFAULT NULL,
                          `pay_datetime` datetime(6) DEFAULT NULL,
                          `pay_method` varchar(255) DEFAULT NULL,
                          `pay_status` smallint(6) DEFAULT NULL,
                          `pay_type` varchar(255) DEFAULT NULL,
                          `total_amount` int(11) DEFAULT NULL,
                          `updated_dt` datetime(6) DEFAULT NULL,
                          `chat_room_seq` bigint(20) DEFAULT NULL,
                          `user_seq` bigint(20) DEFAULT NULL,
                          `restaurant_seq` bigint(20) DEFAULT NULL,
                          PRIMARY KEY (`seq`),
                          KEY `FKmj1b84d9mfpkoajha3g2hbqy3` (`chat_room_seq`,`user_seq`),
                          KEY `FKn72b1j2lsu697prgbwaunotw5` (`restaurant_seq`),
                          CONSTRAINT `FKmj1b84d9mfpkoajha3g2hbqy3` FOREIGN KEY (`chat_room_seq`, `user_seq`) REFERENCES `chat_room` (`seq`, `user_seq`),
                          CONSTRAINT `FKn72b1j2lsu697prgbwaunotw5` FOREIGN KEY (`restaurant_seq`) REFERENCES `restaurant` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- beamo.owner definition

CREATE TABLE `owner` (
                         `seq` bigint(20) NOT NULL,
                         `acount_name` varchar(255) DEFAULT NULL,
                         `acount_num` int(11) DEFAULT NULL,
                         `created_dt` datetime(6) DEFAULT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         `point` int(11) NOT NULL,
                         `profile` varchar(300) DEFAULT NULL,
                         `updated_dt` datetime(6) DEFAULT NULL,
                         `restaurant_seq` bigint(20) DEFAULT NULL,
                         PRIMARY KEY (`seq`),
                         KEY `FK5j7qt81mqhvgp0gp1usc9it5y` (`restaurant_seq`),
                         CONSTRAINT `FK5j7qt81mqhvgp0gp1usc9it5y` FOREIGN KEY (`restaurant_seq`) REFERENCES `restaurant` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- beamo.review definition

CREATE TABLE `review` (
                          `seq` bigint(20) NOT NULL AUTO_INCREMENT,
                          `content` varchar(255) DEFAULT NULL,
                          `created_dt` datetime(6) DEFAULT NULL,
                          `grade` float NOT NULL,
                          `updated_dt` datetime(6) DEFAULT NULL,
                          `chat_room_seq` bigint(20) DEFAULT NULL,
                          `user_seq` bigint(20) DEFAULT NULL,
                          `restaurant_seq` bigint(20) DEFAULT NULL,
                          PRIMARY KEY (`seq`),
                          KEY `FK9sd4hgvkgtfmte92l69v5dtqv` (`chat_room_seq`,`user_seq`),
                          KEY `FK8knm4uk2jkwh1yn9pdfu5k2tu` (`restaurant_seq`),
                          CONSTRAINT `FK8knm4uk2jkwh1yn9pdfu5k2tu` FOREIGN KEY (`restaurant_seq`) REFERENCES `restaurant` (`seq`),
                          CONSTRAINT `FK9sd4hgvkgtfmte92l69v5dtqv` FOREIGN KEY (`chat_room_seq`, `user_seq`) REFERENCES `chat_room` (`seq`, `user_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


— beamo.basket definition

CREATE TABLE `basket` (
                          `seq` bigint(20) NOT NULL AUTO_INCREMENT,
                          `count` smallint(6) NOT NULL,
                          `total_amount` int(11) NOT NULL,
                          `chat_room_seq` bigint(20) DEFAULT NULL,
                          `user_seq` bigint(20) DEFAULT NULL,
                          `restaurant_seq` bigint(20) DEFAULT NULL,
                          PRIMARY KEY (`seq`),
                          KEY `FK9yg4384dhyan6bfwi3c9xvlyn` (`chat_room_seq`,`user_seq`),
                          KEY `FK1dcbtudt6igihwxqbynhl245o` (`restaurant_seq`),
                          CONSTRAINT `FK1dcbtudt6igihwxqbynhl245o` FOREIGN KEY (`restaurant_seq`) REFERENCES `restaurant` (`seq`),
                          CONSTRAINT `FK9yg4384dhyan6bfwi3c9xvlyn` FOREIGN KEY (`chat_room_seq`, `user_seq`) REFERENCES `chat_room` (`seq`, `user_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;