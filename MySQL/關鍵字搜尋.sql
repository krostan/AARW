# 如果有存在 keywordSearch 則刪除
DROP PROCEDURE IF EXISTS keywordSearch;

DELIMITER $$
CREATE PROCEDURE keywordSearch(IN keyword VARCHAR(100))
BEGIN
	# 設定變數 初始值為關鍵字的長度
	DECLARE kwLength INT DEFAULT length(keyword);
	# 查詢資料表裡面的 是否有全文檢索
	SELECT count(*) into @fulltext_index 
	FROM information_schema.STATISTICS
	WHERE table_schema = 'pet_directory' AND table_name = 'pet' AND index_name = 'fulltext_keywordSearch_idx';
	
    # 先刪除原本的全文檢索
	IF @fulltext_index > 0 THEN
		ALTER TABLE pet DROP INDEX fulltext_keywordSearch_idx;
	END IF;
	
    # 新增全文檢索
	ALTER TABLE pet ADD FULLTEXT INDEX fulltext_keywordSearch_idx (pet_name, breed, gender, coat_color, age, location, species, size, quest);
	
    SELECT *
	FROM pet
	WHERE
		(
			CASE
				# 一個中文是3個字元
				# 當3個中文字或以上
				WHEN kwLength >= 9 THEN
					MATCH(pet_name, breed, gender, coat_color, age, location, species, size, quest) AGAINST (keyword IN BOOLEAN MODE)
				# 當只有兩個中文
				WHEN kwLength >=3 THEN
					pet_name LIKE CONCAT('%', keyword, '%')
					OR (pet_name NOT LIKE CONCAT('%', keyword, '%') AND breed LIKE CONCAT('%', keyword, '%'))
					OR (pet_name NOT LIKE CONCAT('%', keyword, '%') AND breed NOT LIKE CONCAT('%', keyword, '%') AND gender LIKE CONCAT('%', keyword, '%'))
					OR (pet_name NOT LIKE CONCAT('%', keyword, '%') AND breed NOT LIKE CONCAT('%', keyword, '%') AND gender NOT LIKE CONCAT('%', keyword, '%') AND coat_color LIKE CONCAT('%', keyword, '%'))
					OR (pet_name NOT LIKE CONCAT('%', keyword, '%') AND breed NOT LIKE CONCAT('%', keyword, '%') AND gender NOT LIKE CONCAT('%', keyword, '%') AND coat_color NOT LIKE CONCAT('%', keyword, '%') AND location LIKE CONCAT('%', keyword, '%'))
					OR (pet_name NOT LIKE CONCAT('%', keyword, '%') AND breed NOT LIKE CONCAT('%', keyword, '%') AND gender NOT LIKE CONCAT('%', keyword, '%') AND coat_color NOT LIKE CONCAT('%', keyword, '%') AND location NOT LIKE CONCAT('%', keyword, '%') AND species LIKE CONCAT('%', keyword, '%'))
					OR (pet_name NOT LIKE CONCAT('%', keyword, '%') AND breed NOT LIKE CONCAT('%', keyword, '%') AND gender NOT LIKE CONCAT('%', keyword, '%') AND coat_color NOT LIKE CONCAT('%', keyword, '%') AND location NOT LIKE CONCAT('%', keyword, '%') AND species NOT LIKE CONCAT('%', keyword, '%') AND size LIKE CONCAT('%', keyword, '%'))
					OR (pet_name NOT LIKE CONCAT('%', keyword, '%') AND breed NOT LIKE CONCAT('%', keyword, '%') AND gender NOT LIKE CONCAT('%', keyword, '%') AND coat_color NOT LIKE CONCAT('%', keyword, '%') AND location NOT LIKE CONCAT('%', keyword, '%') AND species NOT LIKE CONCAT('%', keyword, '%') AND size NOT LIKE CONCAT('%', keyword, '%') AND quest LIKE CONCAT('%', keyword, '%'))
				# 當只有一個數字(1個字元)或2個數字(2個字元)
				WHEN kwLength >= 1 THEN
					age LIKE CONCAT('%', keyword, '%')
			END 
		);
    
END$$
DELIMITER ;


-- 以下是測試語法
-- select Length('10');
-- select Length('母');
-- call pet_directory.keywordSearch('四');

-- call pet_directory.keywordSearch('柏曼貓');
-- select * from pet where age LIKE '2';
-- select * from pet where pet_name NOT LIKE CONCAT('%', '金吉', '%') AND breed LIKE CONCAT('%', '金吉', '%');

-- SELECT * FROM pet WHERE MATCH(pet_name, breed, gender, coat_color, age, location, species, size, quest) AGAINST('金吉拉');