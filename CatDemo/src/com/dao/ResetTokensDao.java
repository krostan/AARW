package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.database.JDBCDriver;
import com.entities.ResetTokens;
import com.managers.ResetTokensManager;

public class ResetTokensDao {
	private Connection conn;

	public ResetTokensDao() {
		JDBCDriver jdbc = new JDBCDriver();
		conn = jdbc.getConn();
	}

	public boolean save(int userId, String token, LocalDateTime  expirationDate) {
		boolean isSuccess = false;
		// TtimeSstamp 轉 LocalDateTime
		Timestamp timestamp = Timestamp.valueOf(expirationDate);
		String query = "INSERT INTO reset_tokens (user_id, token, expiration_date) VALUES(?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, userId);
			pstm.setString(2, token);
			pstm.setTimestamp(3, timestamp);

			isSuccess = pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isSuccess;
	}

	public ResetTokens findByToken(String token) {
		ResetTokens resetTokens = null;

		String query = "SELECT * FROM reset_tokens WHERE token = ?";
		
		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			
			pstm.setString(1, token);
			
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("user_id");
				String token1 = rs.getString("token");
				// LocalDateTime 轉 TtimeSstamp
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				LocalDateTime expirationDate = timestamp.toLocalDateTime();
				
				String isUsed = rs.getString("is_used");
				
				resetTokens = ResetTokensManager.getInstance().createResetTokens(id, token1, expirationDate, isUsed);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resetTokens;
	}

	public boolean updateResetTokens(int userId, String token, String isUsed) {
		boolean isSuccess = false;
		String query = "UPDATE reset_tokens SET is_used = ? WHERE user_id = ? and token = ?";
		
		try (PreparedStatement pstm = conn.prepareStatement(query);) {

			pstm.setString(1, isUsed);
			pstm.setInt(2, userId);
			pstm.setString(3, token);
			
			isSuccess = pstm.executeUpdate() > 0;
			
			return isSuccess;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

}
