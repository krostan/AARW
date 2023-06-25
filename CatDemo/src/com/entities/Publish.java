package com.entities;

import java.util.Date;

public class Publish {

	private int publishId;

	private Date publishDate;

	private Pet pet;

	private Member member;

	public Publish() {
	}

	public Publish(Date publishDate) {
		this.publishDate = publishDate;
	}

	public int getPublishId() {
		return publishId;
	}

	public void setPublishId(int publishId) {
		this.publishId = publishId;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
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
		return "Publish [publishId=" + publishId + ", publishDate=" + publishDate + ", pet=" + pet + ", member="
				+ member + "]";
	}

}
