package com.entities;

import java.util.Date;

public class Adoption {
	
	private int adoptionId;
	private Date adoptionDate;
	private String adoptionResult;
	private Date recordDate;
	private Pet pet;
	private Member member;
	
	public Adoption() {
	}

	public Adoption(Date adoptionDate, String adoptionResult, Date recordDate) {
		this.adoptionDate = adoptionDate;
		this.adoptionResult = adoptionResult;
		this.recordDate = recordDate;
	}

	public int getAdoptionId() {
		return adoptionId;
	}

	public void setAdoptionId(int adoptionId) {
		this.adoptionId = adoptionId;
	}

	public Date getAdoptionDate() {
		return adoptionDate;
	}

	public void setAdoptionDate(Date adoptionDate) {
		this.adoptionDate = adoptionDate;
	}

	public String getAdoptionResult() {
		return adoptionResult;
	}

	public void setAdoptionResult(String adoptionResult) {
		this.adoptionResult = adoptionResult;
	}
	
	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "Adoption [adoptionId=" + adoptionId + ", adoptionDate=" + adoptionDate + ", adoptionResult="
				+ adoptionResult + ", recordDate=" + recordDate + ", pet=" + pet + ", member=" + member + "]";
	}
}
