CREATE DATABASE  IF NOT EXISTS `pet_directory`;
USE `pet_directory`;

DROP TABLE IF EXISTS `publish`;

-- 刊登浪浪表 
CREATE TABLE `publish` (
  `publish_id` int NOT NULL AUTO_INCREMENT,
  `publish_date` datetime DEFAULT NULL,
  `pet_id` int ,
  `user_id` int ,
  PRIMARY KEY (`publish_id`),
  UNIQUE KEY `publish_idx_1` (`user_id`,`pet_id`),
  CONSTRAINT `publish_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`),
  CONSTRAINT `publish_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;


-- INSERT INTO `publish` VALUES 
	

