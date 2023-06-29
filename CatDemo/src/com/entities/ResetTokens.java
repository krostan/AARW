package com.entities;

import java.time.LocalDateTime;

public class ResetTokens {
	private int userId;
	private String token;
	private LocalDateTime  expirationDate;
	private String isUsed;
	
	public ResetTokens() {
		
	}
	
	public ResetTokens(int userId, String token, LocalDateTime  expirationDate, String isUsed) {
		super();
		this.userId = userId;
		this.token = token;
		this.expirationDate = expirationDate;
		this.isUsed = isUsed;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime  getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime  expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	@Override
	public String toString() {
		return "ResetTokens [userId=" + userId + ", token=" + token + ", expirationDate=" + expirationDate + ", isUsed="
				+ isUsed + "]";
	}
	
	
	
}
