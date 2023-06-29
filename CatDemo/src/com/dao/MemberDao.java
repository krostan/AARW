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
import com.database.BCryptStringUtil;
import com.database.JDBCDriver;

public class MemberDao {
	private Connection conn;

	public MemberDao() {
		JDBCDriver jdbc = new JDBCDriver();
		conn = jdbc.getConn();
	}

	// 驗證帳號密碼
	public int authenticate(String username, String password) {
		
		String query = "SELECT user_id FROM member WHERE username = ? and password = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {

			pstm.setString(1, username);
			pstm.setString(2, password);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				return rs.getInt("user_id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	// 新增會員
	public boolean save(String userName, String password, String name, String phone, Date birthday, String email,
			String gender, String address) {
		Member member = null;
		Member memberPhone = null;
		Member memberEmail = null;
		boolean isSuccess = false;
		
		// 轉成 能儲存到資料庫的Date型態
		java.sql.Date sqlBirthday = new java.sql.Date(birthday.getTime());
		
		// 密碼加密
		String hashpw = BCryptStringUtil.encodePassword(password);
		
		String query = "INSERT INTO member (username, password, name, phone, birthday, email, gender, address) VALUES (?,?,?,?,?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {

			pstm.setString(1, userName);
			pstm.setString(2, hashpw);
			pstm.setString(3, name);
			pstm.setString(4, phone);
			pstm.setDate(5, sqlBirthday);
			pstm.setString(6, email);
			pstm.setString(7, gender);
			pstm.setString(8, address);

			// 透過使用者ID 判斷是否已在資料庫中
			member = MemberManager.getInstance().findByName(userName);
			memberPhone = MemberManager.getInstance().findByPhone(phone);
			memberEmail = MemberManager.getInstance().findByEmail(email);

			if (member == null && memberPhone == null && memberEmail == null) {
				isSuccess = pstm.executeUpdate() > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isSuccess;
	}

	// search member by username 用於檢查username是否重複
	public Member findByName(String username) {

		Member member = null;
		String query = "SELECT * FROM member WHERE username = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setString(1, username);

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

	// 驗證電話是否註冊
	public Member findByPhone(String phone) {

		Member member = null;
		String query = "SELECT * FROM member WHERE phone = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setString(1, phone);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {

				int id = rs.getInt("user_id");
				String userName = rs.getString("username");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String getPhone = rs.getString("phone");
				Date birthday = rs.getDate("birthday");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				String address = rs.getString("address");

				member = MemberManager.getInstance().createMember(id, userName, password, name, getPhone, birthday,
						email, gender, address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return member;
	}

	// 驗證email是否註冊
	public Member findByEmail(String email) {

		Member member = null;
		String query = "SELECT * FROM member WHERE email = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setString(1, email);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {

				int id = rs.getInt("user_id");
				String userName = rs.getString("username");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String getPhone = rs.getString("phone");
				Date birthday = rs.getDate("birthday");
				String getEmail = rs.getString("email");
				String gender = rs.getString("gender");
				String address = rs.getString("address");

				member = MemberManager.getInstance().createMember(id, userName, password, name, getPhone, birthday,
						getEmail, gender, address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return member;
	}

	// search member by user_id
	public Member findById(int userId) {

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

	// 找回密碼
	public String findByNameEmail(String username, String email) {

		String query = "SELECT password FROM member WHERE username = ? and email = ?";

		String pwd = "";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setString(1, username);
			pstm.setString(2, email);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {

				pwd = rs.getString("password");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pwd;
	}

	// 更新會員
	public boolean updateMember(int userId, String name, String phone, Date birthday, String email, String gender,
			String address) {

		boolean isSuccess = false;

		java.sql.Date sqlBirthday = new java.sql.Date(birthday.getTime());

		String query = "UPDATE member SET name = ?, phone = ?, birthday = ?, email = ?, gender = ?, address = ? WHERE user_id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {

			pstm.setString(1, name);
			pstm.setString(2, phone);
			pstm.setDate(3, sqlBirthday);
			pstm.setString(4, email);
			pstm.setString(5, gender);
			pstm.setString(6, address);
			pstm.setInt(7, userId);

			isSuccess = pstm.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	// 更改密碼
	public boolean updatePwd(int userId, String oldPwd, String newPwd) {
		boolean isSuccess = false;
		// 密碼加密
		String hashpw = BCryptStringUtil.encodePassword(newPwd);
		
		String query = "UPDATE member SET password = ? WHERE user_id = ? and password = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {

			pstm.setString(1, hashpw);
			pstm.setInt(2, userId);
			pstm.setString(3, oldPwd);

			isSuccess = pstm.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isSuccess;
	}

	// 重設密碼
	public boolean updatePwd(int userId, String newPwd) {
		boolean isSuccess = false;
		// 密碼加密
		String hashpw = BCryptStringUtil.encodePassword(newPwd);

		String query = "UPDATE member SET password = ? WHERE user_id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {

			pstm.setString(1, hashpw);
			pstm.setInt(2, userId);

			isSuccess = pstm.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isSuccess;
	}

	// 搜尋該會員的全部寵物
	public List<Pet> getPetsByMemberId(int userId) {

		List<Pet> result = new ArrayList<>();
		Pet pet = null;
		String query = "SELECT * FROM pet WHERE user_id = ?";
		try (PreparedStatement pstm = conn.prepareStatement(query);) {

			pstm.setInt(1, userId);

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

				pet = PetManager.getInstance().createPet(id, petName, breed, gender, coatColor, age, location, species,
						size, quest, photo, userId);

				result.add(pet);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<Member> findAll() {
		List<Member> result = new ArrayList<>();
		Member member = null;

		String query = "SELECT * FROM member";

		try (Statement stmt = conn.createStatement();) {
			ResultSet rs = stmt.executeQuery(query);
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

				result.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean delete(int userId) {
		boolean isDelete = false;
		String query = "DELETE FROM member WHERE user_id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(query);) {
			pstm.setInt(1, userId);

			isDelete = pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isDelete;
	}

}
