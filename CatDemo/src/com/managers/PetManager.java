package com.managers;

import java.util.List;

import com.dao.PetDao;
import com.entities.Member;
import com.entities.Pet;

public class PetManager {
	private static PetManager instance = new PetManager();
	private static PetDao dao = new PetDao();

	private PetManager() {
	}

	public static PetManager getInstance() {
		return instance;
	}

	public Pet createPet(int id, String petName, String breed, String gender, String coatColor, String age,
			String location, String species, String size, String quest, String photo,int userId) {
		Pet pet = new Pet();

		pet.setPetId(id);
		pet.setPetName(petName);
		pet.setBreed(breed);
		pet.setGender(gender);
		pet.setCoatColor(coatColor);
		pet.setAge(age);
		pet.setLocation(location);
		pet.setSpecies(species);
		pet.setSize(size);
		pet.setQuest(quest);
		pet.setPhotos(photo);
		
		Member member = MemberManager.getInstance().findById(userId);
		
		pet.setMember(member);

		return pet;
	}

	public boolean save(String petName, String breed, String gender, String coatColor, String age,
			String location, String species, String size, String quest, String photo,int userId) {
		return dao.save(petName, breed, gender, coatColor, age, location, species, size, quest, photo,userId);
	}

	public Pet findById(int petId) {
		return dao.findById(petId);
	}
	
	public Pet findByName(String petName) {
		return dao.findByName(petName);
	}
	
	public List<Pet> findAll(){
		return dao.findAll();
	}

	public boolean updatePet(int petId, String petName, String breed, String gender, String coatColor, String age,
			String location, String species, String size, String quest, String photo) {
		return dao.updatePet(petId, petName, breed, gender, coatColor, age, location, species, size, quest, photo);
	}

	public Member getMember(int userId) {
		return dao.getMember(userId);
	}

	public boolean delete(int petId) {
		return dao.delete(petId);
	}
	
	// 關鍵字搜尋
	public List<Pet> findByKeywordSearch(String keyword) {
		return dao.findByKeywordSearch(keyword);
	}
}
