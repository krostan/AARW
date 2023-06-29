package com.database;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptStringUtil {
	// 加密密碼
	public static String encodePassword(String password) {
		// 加鹽預設為10
		String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
		
        return hashpw;
    }
	// 驗證密碼 前者為輸入的密碼 後者為加密後的密碼
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
