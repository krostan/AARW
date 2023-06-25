package com.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Member {

	private int userId;

	private String username;

	private String password;

	private String name;
	
	private String phone;
	
	private Date birthday;
	
	private String email;
	
	private String gender;
	
	private String address;
	
	private List<Pet> pets;

	public Member() {
	}

	public Member(String username, String password, String name, String gender, String phone, String address,
			String email, Date birthday) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.birthday = birthday;
		pets = new ArrayList<Pet>();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
	
	public void addPet(Pet pet) {
		pets.add(pet);
		pet.setMember(this);
	}
	
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", gender=" + gender + ", phone=" + phone + ", address=" + address + ", email=" + email
				+ ", birthday=" + birthday + "]";
	}

}
