CREATE DATABASE  IF NOT EXISTS `pet_directory`;
USE `pet_directory`;

DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `user_id` int NOT NULL,
  `role` enum('系統管理者','管理者','使用者') NOT NULL,
  UNIQUE KEY `permissions_idx_1` (`user_id`),
  CONSTRAINT `permissions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`)
) ENGINE=InnoDB;

INSERT INTO `permissions` VALUES 
(1,'系統管理者');



