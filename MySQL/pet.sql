CREATE DATABASE  IF NOT EXISTS `pet_directory`;
USE `pet_directory`;

DROP TABLE IF EXISTS `pet`;

CREATE TABLE `pet` (
  `pet_id` int NOT NULL AUTO_INCREMENT,
  `pet_name` varchar(45) DEFAULT NULL,
  `breed` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `coat_color` varchar(45) DEFAULT NULL,
  `age` varchar(20) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `species` varchar(50)  DEFAULT NULL,
  `size` varchar(50)  DEFAULT NULL,
  `quest` MEDIUMTEXT DEFAULT NULL,
  `photos` varchar(255) DEFAULT NULL,
   `user_id` int NOT NULL,
  PRIMARY KEY (`pet_id`),
  CONSTRAINT `pet_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1;

-- INSERT INTO `pet`(pet_name,breed,gender,coat_color,age,location,species,size,quest,user_id) VALUES  
-- 	("Lili", "Chinchilla", "female", "silver", "1", "Taoyuan", "cat", "medium","",1),
-- 	("Rau", "Munchkin", "female", "yellow", "2", "Taoyuan", "cat", "medium","",2),
--     ("Loce", "British shorthair", "male", "blue", "1", "Taoyuan", "cat", "large","",3),
--     ("Leo", "Opseo", "male", "blue", "2", "Taoyuan", "cat", "","",1),
--     ("Jarry", "Chinchilla", "female", "silver", "1", "Taoyuan", "cat", "medium","",1),
-- 	("Henry", "Munchkin", "female", "yellow", "2", "Taoyuan", "cat", "medium","",2),
--     ("Jojo", "British shorthair", "male", "blue", "1", "Taoyuan", "cat", "large","",3),
--     ("Ann", "Opseo", "male", "blue", "2", "Taoyuan", "cat", "","",1),
--     ("Kevin", "Chinchilla", "female", "silver", "1", "Taoyuan", "cat", "medium","",1),
-- 	("Blue", "Munchkin", "female", "yellow", "2", "Taoyuan", "cat", "medium","",2),
--     ("Krostan", "British shorthair", "male", "blue", "1", "Taoyuan", "cat", "large","",3),
--     ("Mouse", "Opseo", "male", "blue", "2", "Taoyuan", "cat", "","",1),
--     ("Sky", "Chinchilla", "female", "silver", "1", "Taoyuan", "cat", "medium","",1),
-- 	("Niuniu", "Munchkin", "female", "yellow", "2", "Taoyuan", "cat", "medium","",2),
--     ("Leila", "British shorthair", "male", "blue", "1", "Taoyuan", "cat", "large","",3),
--     ("Boob", "Opseo", "male", "blue", "2", "Taoyuan", "cat", "","",1);
