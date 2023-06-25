package com.managers;

import java.util.List;

import com.dao.PermissDao;
import com.entities.Permiss;

public class PermissManager {

	private static PermissManager instance = new PermissManager();
	private static PermissDao dao = new PermissDao();

	private PermissManager() {
	}

	public static PermissManager getInstance() {
		return instance;
	}

	public Permiss createPermissions(int userId, String role) {
		Permiss permissions = new Permiss();

		permissions.setUserId(userId);
		permissions.setRole(role);

		return permissions;
	}

	// 新增
	public boolean save(int userId, String role) {
		return dao.save(userId, role);
	}

	public Permiss findByUserId(int userId) {
		return dao.findByUserId(userId);
	}
	
	public List<Permiss> findAll() {
		return dao.findAll();
	}
	
	public boolean updatePermiss(int userId, String role) {
		return dao.updatePermiss(userId, role);
	}
	
	public boolean delete(int userId) {
		return dao.delete(userId);
	}
	

}
