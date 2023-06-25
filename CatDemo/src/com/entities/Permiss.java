package com.entities;

public class Permiss {
	private int userId;
	private String role;
	
	public Permiss() {
	}
	
	public Permiss(int userId, String role) {
		super();
		this.userId = userId;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Permiss [userId=" + userId + ", role=" + role + "]";
	}

}
