package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.database.JDBCDriver;
import com.entities.Permiss;
import com.managers.PermissManager;

public class PermissDao {
	private Connection conn;

	public PermissDao() {
		JDBCDriver jdbc = new JDBCDriver();
		conn = jdbc.getConn();
	}

	public boolean save(int userId, String role) {
		boolean isSuccess = false;
		String query = "INSERT INTO permissions (user_id, role) VALUES(?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, userId);
			pstm.setString(2, role);

			isSuccess = pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	public Permiss findByUserId(int userId) {
		Permiss permissions = null;

		String query = "SELECT * FROM permissions WHERE user_id = ?";
		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, userId);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("user_id");
				String role = rs.getString("role");

				permissions = PermissManager.getInstance().createPermissions(id, role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return permissions;
	}

	public List<Permiss> findAll() {
		List<Permiss> result = new ArrayList<>();
		Permiss permissions = null;

		try (Statement stmt = conn.createStatement();) {
			String query = "SELECT * FROM permissions";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				int id = rs.getInt("user_id");
				String role = rs.getString("role");

				permissions = PermissManager.getInstance().createPermissions(id, role);

				result.add(permissions);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean updatePermiss(int userId, String role) {
		boolean isSuccess = false;
		
		String query = "UPDATE permissions SET role = ? WHERE user_id = ?";
		
		try (PreparedStatement pstm = conn.prepareStatement(query);) {

			pstm.setString(1, role);
			pstm.setInt(2, userId);

			isSuccess = pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	public boolean delete(int userId) {
		boolean isDelete = false;
		String query = "DELETE FROM permissions WHERE user_id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, userId);

			isDelete = pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDelete;
	}
}
