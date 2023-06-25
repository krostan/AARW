CREATE DATABASE  IF NOT EXISTS `pet_directory`;
USE `pet_directory`;

DROP TABLE IF EXISTS `adoption`;

-- 領養申請表 
CREATE TABLE `adoption` (
  `adoption_id` int NOT NULL AUTO_INCREMENT,
  `adoption_date` datetime DEFAULT NULL,
  `adoption_result` varchar(20) DEFAULT NULL,
  `record_date` datetime DEFAULT NULL,
  `pet_id` int,
  `user_id` int,
  PRIMARY KEY (`adoption_id`),
  UNIQUE KEY `adoption_idx_1` (`user_id`, `pet_id`, `adoption_id`),
  CONSTRAINT `adoption_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`),
  CONSTRAINT `adoption_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 


-- INSERT INTO `adoption` VALUES 
	

