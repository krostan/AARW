package com.managers;

import java.time.LocalDateTime;

import com.dao.ResetTokensDao;
import com.entities.ResetTokens;

public class ResetTokensManager {
	private static ResetTokensManager instance = new ResetTokensManager();
	private static ResetTokensDao dao = new ResetTokensDao();

	private ResetTokensManager() {
	}

	public static ResetTokensManager getInstance() {
		return instance;
	}

	public ResetTokens createResetTokens(int userId, String token, LocalDateTime  expirationDate, String isUsed) {
		ResetTokens resetTokens = new ResetTokens();

		resetTokens.setUserId(userId);
		resetTokens.setToken(token);
		resetTokens.setExpirationDate(expirationDate);
		resetTokens.setIsUsed(isUsed);

		return resetTokens;
	}

	// 新增
	public boolean save(int userId, String token, LocalDateTime  expirationDate) {
		return dao.save(userId, token, expirationDate);
	}

	// 透過token 找到 tokens表的 id
	public ResetTokens findByToken(String token) {
		return dao.findByToken(token);
	}

	// 如果使用過token 則更新 
	public boolean updateResetTokens(int userId, String token, String isUsed) {
		return dao.updateResetTokens(userId, token, isUsed);
	}
}
