package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entities.Member;
import com.entities.Pet;
import com.managers.MemberManager;
import com.managers.PetManager;
import com.database.JDBCDriver;

public class PetDao {
	private Connection conn;

	public PetDao() {
		JDBCDriver jdbc = new JDBCDriver();
		conn = jdbc.getConn();
	}

	public boolean save(String petName, String breed, String gender, String coatColor, String age, String location,
			String species, String size, String quest, String photo, int userId) {

		boolean isSuccess = false;
		String query = "INSERT INTO pet (pet_name, breed, gender, coat_color, age, location, species, size, quest, photos, user_id) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setString(1, petName);
			pstm.setString(2, breed);
			pstm.setString(3, gender);
			pstm.setString(4, coatColor);
			pstm.setString(5, age);
			pstm.setString(6, location);
			pstm.setString(7, species);
			pstm.setString(8, size);
			pstm.setString(9, quest);
			pstm.setString(10, photo);
			pstm.setInt(11, userId);

			isSuccess = pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	public Pet findById(int petId) {

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
	
	public Pet findByName(String petName) {

		Pet pet = null;
		String query = "SELECT * FROM pet WHERE pet_name = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setString(1, petName);
			
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("pet_id");
				String petName1 = rs.getString("pet_name");
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

				pet = PetManager.getInstance().createPet(id, petName1, breed, gender, coatColor, age, location, species,
						size, quest, photo, userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pet;
	}
	public List<Pet> findAll() {

		List<Pet> result = new ArrayList<>();
		Pet pet = null;

		try (Statement stmt = conn.createStatement();) {
			String query = "SELECT * FROM pet ";
			ResultSet rs = stmt.executeQuery(query);

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

				result.add(pet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean updatePet(int petId, String petName, String breed, String gender, String coatColor, String age,
			String location, String species, String size, String quest, String photo) {

		boolean isSuccess = false;
		String query = "UPDATE pet SET pet_name =?, breed = ?,gender = ?, coat_color =?, age = ?, location = ?, species = ?, size = ?, quest = ?, photos = ? WHERE pet_id = ?";
		
		try (PreparedStatement pstm = conn.prepareStatement(query);) {

			pstm.setString(1, petName);
			pstm.setString(2, breed);
			pstm.setString(3, gender);
			pstm.setString(4, coatColor);
			pstm.setString(5, age);
			pstm.setString(6, location);
			pstm.setString(7, species);
			pstm.setString(8, size);
			pstm.setString(9, quest);
			pstm.setString(10, photo);
			pstm.setInt(11, petId);
			
			isSuccess = pstm.executeUpdate() > 0;
			
			return isSuccess;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

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

	public boolean delete(int petId) {

		boolean isDelete = false;
		String query = "DELETE FROM pet WHERE pet_id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, petId);

			isDelete = pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isDelete;
	}

	public List<Pet> findByKeywordSearch(String keyword) {
		
		List<Pet> result = new ArrayList<>();
		Pet pet = null;
		String query = "call pet_directory.keywordSearch(?)";
		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			
			pstm.setString(1, keyword);
			
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
				int userId =rs.getInt("user_id");

				pet = PetManager.getInstance().createPet(id, petName, breed, gender, coatColor, age, location, species,
						size, quest, photo,userId);
				
				result.add(pet);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
