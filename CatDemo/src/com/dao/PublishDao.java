package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entities.Publish;
import com.managers.PublishManager;
import com.database.JDBCDriver;

public class PublishDao {

	private Connection conn;

	public PublishDao() {
		JDBCDriver jdbc = new JDBCDriver();
		conn = jdbc.getConn();
	}

	// 新增
	public boolean save(Date publishDate, int petId, int userId) {
		boolean isSuccess = false;
		// 轉換成時間戳
		// 轉換成 java.sql.Timestamp 格式
		Timestamp timestamp = new Timestamp(publishDate.getTime());
		String query = "INSERT INTO publish (publish_date, pet_id, user_id) VALUES (?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setTimestamp(1, timestamp);
			pstm.setInt(2, petId);
			pstm.setInt(3, userId);

			isSuccess = pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isSuccess;
	}
	
	// 透過寵物ID 找到刊登
	public Publish findByPetId(int petId) {
		Publish publish = null;
		String query = "SELECT * FROM publish WHERE pet_id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, petId);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("publish_id");
				Timestamp publishDate = rs.getTimestamp("publish_date");
				int pet = rs.getInt("pet_id");
				int userId = rs.getInt("user_id");

				publish = PublishManager.getInstance().createPublish(id, publishDate, pet, userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return publish;
	}

	public Publish findById(int publishId) {
		Publish publish = null;
		String query = "SELECT * FROM publish WHERE publish_id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, publishId);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("publish_id");
				Timestamp publishDate = rs.getTimestamp("publish_date");
				int petId = rs.getInt("pet_id");
				int userId = rs.getInt("user_id");

				publish = PublishManager.getInstance().createPublish(id, publishDate, petId, userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return publish;
	}
	
	// 透過使用者ID 找到屬於他的刊登
	public List<Publish> findAllOfMember(int userId){
		List<Publish> result = new ArrayList<>();
		Publish publish = null;

		String query = "SELECT * FROM publish WHERE user_id = ?";
		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, userId);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("publish_id");
				Timestamp publishDate = rs.getTimestamp("publish_date");
				int petId = rs.getInt("pet_id");
				int memberId = rs.getInt("user_id");

				publish = PublishManager.getInstance().createPublish(id, publishDate, petId, memberId);

				result.add(publish);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	// 找到全部刊登
	public List<Publish> findAll() {
		List<Publish> result = new ArrayList<>();
		Publish publish = null;

		try (Statement stmt = conn.createStatement();) {
			String query = "SELECT * FROM publish ";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				int id = rs.getInt("publish_id");
				Timestamp publishDate = rs.getTimestamp("publish_date");
				int petId = rs.getInt("pet_id");
				int userId = rs.getInt("user_id");

				publish = PublishManager.getInstance().createPublish(id, publishDate, petId, userId);

				result.add(publish);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	// 依照欄位值 和asc 或 desc 做排序後取出全部刊登
	public List<Publish> findSortAll(String description, String sortData) {
		List<Publish> result = new ArrayList<>();
		Publish publish = null;
		
		String query = "SELECT * FROM publish ORDER BY " + description +" "+ sortData + ", publish_id " + sortData;
		
		if(description.equals("")||sortData.equals("")) {
			query = "SELECT * FROM publish ORDER BY RAND()";
		}

		try (Statement stmt = conn.createStatement();) {
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				int id = rs.getInt("publish_id");
				Timestamp publishDate = rs.getTimestamp("publish_date");
				int petId = rs.getInt("pet_id");
				int userId = rs.getInt("user_id");

				publish = PublishManager.getInstance().createPublish(id, publishDate, petId, userId);

				result.add(publish);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	// 找到所屬petid
	public int getPetId(int publishId) {
		int petId = 0;

		String query = "select pet_id from publish where publish_id = ?";
		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, publishId);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				petId = rs.getInt("pet_id");
			}
			System.out.println(petId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return petId;
	}

	// 找到所屬的會員編號
	public int getMemberId(int publishId) {

		int userId = 0;

		String query = "select user_id from publish where publish_id = ?";
		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, publishId);
			ResultSet rs = pstm.executeQuery();
			
			if (rs.next()) {
				userId = rs.getInt("user_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}

	// 刪除刊登
	public boolean delete(int publishId) {

		boolean isDelete = false;
		String query = "DELETE FROM publish WHERE publish_id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, publishId);

			isDelete = pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDelete;
	}
}
