package com.managers;

import java.util.Date;
import java.util.List;

import com.dao.PublishDao;
import com.entities.Member;
import com.entities.Pet;
import com.entities.Publish;

/* 刊登控制
 * 新增 刪除 查詢刊登 查詢全部刊登 查詢所屬pet 查詢所屬member
 * */

public class PublishManager {
	private static PublishManager instance = new PublishManager();
	private static PublishDao dao = new PublishDao();

	private PublishManager() {
	}

	public static PublishManager getInstance() {
		return instance;
	}

	public Publish createPublish(int id, Date publishDate, int petId, int userId) {
		Publish publish = new Publish();

		publish.setPublishId(id);
		publish.setPublishDate(publishDate);

		Pet pet = PetManager.getInstance().findById(petId);
		publish.setPet(pet);

		Member member = MemberManager.getInstance().findById(userId);

		publish.setMember(member);

		return publish;
	}

	// 新增
	public boolean save(Date publishDate, int petId, int userId) {
		return dao.save(publishDate, petId, userId);
	}
	
	// 透過寵物ID 找到刊登
	public Publish findByPetId(int petId) {
		return dao.findByPetId(petId);
	}

	public Publish findById(int publishId) {
		return dao.findById(publishId);
	}

	// 透過使用者ID 找到屬於他的刊登
	public List<Publish> findAllOfMember(int userId){
		return dao.findAllOfMember(userId);
	}
	
	// 找到全部刊登
	public List<Publish> findAll() {
		return dao.findAll();
	}
	
	// 依照欄位值 和asc 或 desc 做排序後取出全部刊登
	public List<Publish> findSortAll(String description, String sortData) {
		return dao.findSortAll(description, sortData);
	}

	// 找到所屬pet
	public int getPetId(int publishId) {
		return dao.getPetId(publishId);
	}

	// 找到所屬的會員
	public int getMemberId(int publishId) {
		return dao.getMemberId(publishId);
	}

	// 刪除刊登
	public boolean delete(int publishId) {
		return dao.delete(publishId);
	}
}
