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

import com.entities.Adoption;
import com.entities.Member;
import com.entities.Pet;
import com.managers.AdoptionManager;
import com.managers.MemberManager;
import com.managers.PetManager;
import com.database.JDBCDriver;

public class AdoptionDao {

	private Connection conn;

	public AdoptionDao() {
		JDBCDriver jdbc = new JDBCDriver();
		conn = jdbc.getConn();
	}

	// 新增
	public boolean save(Date adoptionDate, String adoptionResult, Date recordDate, int petId, int userId) {

		boolean isSuccess = false;
		// 轉換成 java.sql.Timestamp 格式
		Timestamp timestamp = new Timestamp(adoptionDate.getTime());

		String query = "INSERT INTO adoption (adoption_date, adoption_result, pet_id, user_id) VALUES (?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setTimestamp(1, timestamp);
			pstm.setString(2, adoptionResult);// 預設null
			pstm.setInt(3, petId);
			pstm.setInt(4, userId);

			isSuccess = pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isSuccess;
	}
	
	public Adoption findById(int adoptionId) {
		Adoption adoption = null;
		String query = "SELECT * FROM adoption WHERE adoption_id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, adoptionId);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("adoption_id");
				Timestamp adoptionDate = rs.getTimestamp("adoption_date");
				String adoptionResult = rs.getString("adoption_result");
				Timestamp recordDate =rs.getTimestamp("record_date");
				int petId = rs.getInt("pet_id");
				int userId = rs.getInt("user_id");

				adoption = AdoptionManager.getInstance().createAdoption(id, adoptionDate, adoptionResult, recordDate, petId,
						userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return adoption;
	}

	// 找到全部預約
	public List<Adoption> findAll() {
		List<Adoption> result = new ArrayList<>();
		Adoption adoption = null;

		try (Statement stmt = conn.createStatement();) {
			String query = "SELECT * FROM adoption";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				int id = rs.getInt("adoption_id");
				Timestamp adoptionDate = rs.getTimestamp("adoption_date");
				String adoptionResult = rs.getString("adoption_result");
				Timestamp recordDate =rs.getTimestamp("record_date");
				int petId = rs.getInt("pet_id");
				int userId = rs.getInt("user_id");

				adoption = AdoptionManager.getInstance().createAdoption(id, adoptionDate, adoptionResult, recordDate, petId,
						userId);

				result.add(adoption);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	// 查找該寵物 全部的預約
	public List<Adoption> findAllByPetId(int petId) {
		List<Adoption> result = new ArrayList<>();
		Adoption adoption = null;

		String query = "SELECT * FROM adoption WHERE pet_id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {

			pstm.setInt(1, petId);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("adoption_id");
				Timestamp adoptionDate = rs.getTimestamp("adoption_date");
				String adoptionResult = rs.getString("adoption_result");
				Timestamp recordDate =rs.getTimestamp("record_date");
				int pet = rs.getInt("pet_id");
				int userId = rs.getInt("user_id");

				adoption = AdoptionManager.getInstance().createAdoption(id, adoptionDate, adoptionResult, recordDate, pet, userId);
				result.add(adoption);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	// 查找會員預約的項目
	public List<Adoption> findByMemberId(int userId) {
		List<Adoption> result = new ArrayList<>();
		Adoption adoption = null;

		String query = "SELECT * FROM adoption WHERE user_id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, userId);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("adoption_id");
				Timestamp adoptionDate = rs.getTimestamp("adoption_date");
				String adoptionResult = rs.getString("adoption_result");
				Timestamp recordDate =rs.getTimestamp("record_date");
				int petId = rs.getInt("pet_id");
				int memberId = rs.getInt("user_id");

				adoption = AdoptionManager.getInstance().createAdoption(id, adoptionDate, adoptionResult, recordDate, petId, memberId);
				
				result.add(adoption);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// 判斷該寵物是否已經被預約
	public Adoption findByMemberPetId(int petId, int userId) {

		Adoption adoption = null;

		String query = "SELECT * FROM adoption WHERE pet_id = ? and user_id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, petId);
			pstm.setInt(2, userId);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("adoption_id");
				Timestamp adoptionDate = rs.getTimestamp("adoption_date");
				String adoptionResult = rs.getString("adoption_result");
				Timestamp recordDate =rs.getTimestamp("record_date");
				int pet = rs.getInt("pet_id");
				int memberId = rs.getInt("user_id");

				adoption = AdoptionManager.getInstance().createAdoption(id, adoptionDate, adoptionResult, recordDate, pet, memberId);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return adoption;
	}
	
	// 查詢adoptionResult
	public String findResultByMemberPetId(int petId, int userId) {
		String result = null;

		String query = "SELECT adoption_result FROM adoption WHERE pet_id = ? and user_id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, petId);
			pstm.setInt(2, userId);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				result = rs.getString("adoption_result");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public String findResultById(int adoptionId) {
		String result = null;

		String query = "SELECT adoption_result FROM adoption WHERE adoption_id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, adoptionId);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				result = rs.getString("adoption_result");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	// 更新預約結果
	public boolean updateAdoption(int adoptionId, String adoptionResult, Date recordDate) {
		boolean isSuccess = false;
		String query = "UPDATE adoption SET adoption_result = ?, record_date = ? WHERE adoption_id = ?";
		
		// 轉換成 java.sql.Timestamp 格式
		Timestamp timestamp = new Timestamp(recordDate.getTime());

		try (PreparedStatement pstm = conn.prepareStatement(query);) {

			pstm.setString(1, adoptionResult);
			pstm.setTimestamp(2, timestamp);
			pstm.setInt(3, adoptionId);

			isSuccess = pstm.executeUpdate() > 0;
			
			return isSuccess;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	// 查找被預約的浪浪
	public Pet getPet(int petId) {
		Pet pet = null;

		String query = "SELECT * FROM pet WHERE pet_id = ?";
		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, petId);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("pet_id");
				String petName = rs.getString("pet_name");
				String breed = rs.getString("breed");
				String gender = rs.getString("gender");
				String coatColor = rs.getString("coat_color");
				String age = rs.getString("age");
				String location = rs.getString("location");
				String species = rs.getString("species");
				String size = rs.getString("size");
				String quest = rs.getString("quest");
				String photo = rs.getString("photos");
				int userId = rs.getInt("user_id");

				pet = PetManager.getInstance().createPet(id, petName, breed, gender, coatColor, age, location, species,
						size, quest, photo, userId);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pet;
	}

	// 查找預約人
	public Member getMember(int userId) {
		Member member = null;

		String query = "SELECT * FROM member WHERE user_id = ?";
		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, userId);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("user_id");
				String userName = rs.getString("username");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				Date birthday = rs.getDate("birthday");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				String address = rs.getString("address");

				member = MemberManager.getInstance().createMember(id, userName, password, name, phone, birthday, email,
						gender, address);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	
	// 取消預約申請
	public boolean delete(int adoptionId) {
		boolean isDelete = false;
		String query = "DELETE FROM adoption WHERE adoption_id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, adoptionId);

			isDelete = pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDelete;
	}

}
