package com.entities;

public class Pet {

    private int petId;

    private String petName;

    private String breed;

    private String gender;

    private String coatColor;

    private String age;

    private String location;

    private String species;

    private String size;

    private String quest;

    private String photos;

    private Member member;
    
    public Pet() {
    }

    public Pet(String petName, String breed, String gender, String coatColor, String age, String location, String species, String size, String quest, String photos) {
        this.petName = petName;
        this.breed = breed;
        this.gender = gender;
        this.coatColor = coatColor;
        this.age = age;
        this.location = location;
        this.species = species;
        this.size = size;
        this.quest = quest;
        this.photos = photos;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String catName) {
        this.petName = catName;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCoatColor() {
        return coatColor;
    }

    public void setCoatColor(String coatColor) {
        this.coatColor = coatColor;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
    public String toString() {
        return "Cat{" +
                "petId=" + petId +
                ", petName='" + petName + '\'' +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                ", coatColor='" + coatColor + '\'' +
                ", age=" + age +
                ", location='" + location + '\'' +
                ", species='" + species + '\'' +
                ", size='" + size + '\'' +
                ", quest='" + quest + '\'' +
                ", photos='" + photos + '\'' +
                '}';
    }
}
