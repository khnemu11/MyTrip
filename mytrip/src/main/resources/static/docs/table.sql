CREATE TABLE `favorite` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `tour_seq` int(11) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=554 DEFAULT CHARSET=utf8;
CREATE TABLE `plan` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `title` varchar(20) NOT NULL,
  `content` varchar(500) NOT NULL,
  `expected_time` varchar(20) DEFAULT NULL,
  `expected_distance` float DEFAULT NULL,
  `taxi_cost` int(11) DEFAULT NULL,
  `fuel_cost` int(11) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `title_UNIQUE` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=394 DEFAULT CHARSET=utf8;
CREATE TABLE `plan_order` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `plan_seq` int(11) NOT NULL,
  `order` int(11) NOT NULL,
  `tour_seq` int(11) NOT NULL,
  `distance` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=474 DEFAULT CHARSET=utf8;
CREATE TABLE `review` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `content` varchar(500) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_date` datetime DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `tour_seq` int(11) DEFAULT NULL,
  `tour_title` varchar(20) DEFAULT NULL,
  `user_id` varchar(20) NOT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=1484 DEFAULT CHARSET=utf8;
CREATE TABLE `review_image` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `review_seq` int(11) NOT NULL,
  `image_name` varchar(50) NOT NULL,
  `image_code` varchar(100) NOT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=344 DEFAULT CHARSET=utf8;
CREATE TABLE `tour` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `type_no` int(11) DEFAULT NULL,
  `type_name` varchar(10) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `address` varchar(50) NOT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  `hits` int(11) DEFAULT '0',
  PRIMARY KEY (`seq`),
  UNIQUE KEY `title_UNIQUE` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=3414 DEFAULT CHARSET=utf8mb4;
CREATE TABLE `user` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `phone_no` varchar(20) DEFAULT NULL,
  `intro` varchar(100) DEFAULT NULL,
  `join_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_date` datetime DEFAULT NULL,
  `withdrawal_date` datetime DEFAULT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8;
