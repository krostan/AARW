CREATE DATABASE  IF NOT EXISTS `pet_directory`;
USE `pet_directory`;

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

INSERT INTO member (username,password,name,phone,birthday,email,gender,address) VALUES
	('cat','1','Leo','0912333444','1998-01-02','cat1@cat.com','男','貓貓市貓貓區貓貓路11號'),
	('cat2','cat123','Henry','0912555666','1990-02-02','cat1@cat.com','女','貓貓市貓貓區貓貓路12號'),
	('cat3','cat123','Krostan','0912777888','1990-03-03','cat1@cat.com','男','貓貓市貓貓區貓貓路13號'),
	('cat4','cat123','Alvin','0912999000','1990-04-04','cat1@cat.com','女','貓貓市貓貓區貓貓路14號'),
	('cat5','cat123','Jack','0912111222','1990-05-05','cat1@cat.com','女','貓貓市貓貓區貓貓路15號');

