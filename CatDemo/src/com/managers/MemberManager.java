package com.managers;

import java.util.Date;
import java.util.List;

import com.dao.MemberDao;
import com.entities.Member;
import com.entities.Pet;

public class MemberManager {
	private static MemberManager instance = new MemberManager();
	private static MemberDao dao = new MemberDao();

	private MemberManager() {
	}

	public static MemberManager getInstance() {
		return instance;
	}

	public Member createMember(int id, String username, String password, String name, String phone, Date birthday,
			String email, String gender, String address) {
		Member member = new Member();

		member.setUserId(id);
		member.setUsername(username);
		member.setPassword(password);
		member.setName(name);
		member.setPhone(phone);
		member.setBirthday(birthday);
		member.setEmail(email);
		member.setGender(gender);
		member.setAddress(address);

		return member;
	}

	// 驗證帳號密碼
	public int authenticate(String username, String password) {
		return dao.authenticate(username, password);
	}

	// 新增會員
	public boolean save(String userName, String password, String name, String phone, Date birthday, String email,
			String gender, String address) {
		return dao.save(userName, password, name, phone, birthday, email, gender, address);
	}

	// search member by username 用於檢查username是否重複
	public Member findByName(String userName) {
		return dao.findByName(userName);
	}
	
	// 驗證電話是否註冊
	public Member findByPhone(String phone) {
		return dao.findByPhone(phone);
	}
	
	// 驗證email是否註冊
	public Member findByEmail(String email) {
		return dao.findByEmail(email);
	}
	
	// search member by user_id
	public Member findById(int userId) {
		return dao.findById(userId);
	}

	// 找回密碼
	public String findByNameEmail(String username, String email) {
		return dao.findByNameEmail(username, email);
	}

	// 更新會員
	public boolean updateMember(int userId, String name, String phone, Date birthday, String email, String gender,
			String address) {
		return dao.updateMember(userId, name, phone, birthday, email, gender, address);
	}

	// 重設密碼
	public boolean updatePwd(int userId, String oldPwd, String newPwd) {
		return dao.updatePwd(userId, oldPwd, newPwd);
	}

	// 搜尋會員的全部寵物
	public List<Pet> getPetsByMemberId(int userId) {
		return dao.getPetsByMemberId(userId);
	}

	public List<Member> findAll() {
		return dao.findAll();
	}

	public boolean delete(int userId) {
		return dao.delete(userId);
	}
}
