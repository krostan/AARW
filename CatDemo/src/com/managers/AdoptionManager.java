package com.managers;

import java.util.Date;
import java.util.List;

import com.dao.AdoptionDao;
import com.entities.Adoption;
import com.entities.Member;
import com.entities.Pet;

/* 預約控制
 * 會員預約一次只能預約一個 除非預約結果為 false 才可繼續預約
 * 新增 刪除 查詢預約 查詢member的全部預約 查詢被預約pet 查詢預約member 更新(更新結果)
 * */

public class AdoptionManager {
	private static AdoptionManager instance = new AdoptionManager();
	private static AdoptionDao dao = new AdoptionDao();

	private AdoptionManager() {
	}

	public static AdoptionManager getInstance() {
		return instance;
	}

	public Adoption createAdoption(int id, Date adoptionDate, String adoptionResult, Date recordDate, int petId, int userId) {
		Adoption adoption = new Adoption();

		adoption.setAdoptionId(id);
		adoption.setAdoptionDate(adoptionDate);
		adoption.setAdoptionResult(adoptionResult);
		adoption.setRecordDate(recordDate);
		
		Pet pet = PetManager.getInstance().findById(petId);
		adoption.setPet(pet);

		Member member = MemberManager.getInstance().findById(userId);

		adoption.setMember(member);

		return adoption;
	}
	
	// 新增
	public boolean save(Date adoptionDate, String adoptionResult, Date recordDate, int petId, int userId) {
		return dao.save(adoptionDate, adoptionResult, recordDate, petId, userId);
	}

	public Adoption findById(int adoptionId) {
		return dao.findById(adoptionId);
	}
	
	// 找到全部預約
	public List<Adoption> findAll() {
		return dao.findAll();
	}
	
	// 查找該寵物 全部的預約
	public List<Adoption> findAllByPetId(int petId) {
		return dao.findAllByPetId(petId);
	}
	
	// 查找會員預約的項目
	public List<Adoption> findByMemberId(int userId) {
		return dao.findByMemberId(userId);
	}
	
	// 判斷該寵物是否已經被預約
	public Adoption findByMemberPetId(int petId, int userId) {
		return dao.findByMemberPetId(petId, userId);
	}
	
	// 查詢adoptionResult
	public String findResultByMemberPetId(int petId, int userId) {
		return dao.findResultByMemberPetId(petId, userId);
	}
	
	// 查詢adoptionResult
	public String findResultById(int adoptionId) {
		return dao.findResultById(adoptionId);
	}
	
	// 更新預約結果
	public boolean updateAdoption(int adoptionId, String adoptionResult, Date recordDate) {
		return dao.updateAdoption(adoptionId, adoptionResult, recordDate);
	}

	// 查找被預約的浪浪
	public Pet getPet(int petId) {
		return dao.getPet(petId);
	}
	
	// 查找預約人
	public Member getMember(int userId) {
		return dao.getMember(userId);
	}
	
	// 取消預約申請
	public boolean delete(int adoptionId) {
		return dao.delete(adoptionId);
	}
}
