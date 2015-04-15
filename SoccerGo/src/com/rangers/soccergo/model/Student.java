package com.rangers.soccergo.model;

public class Student {
	private String ObjectId;
	private String createdAt;
	private String updatedAt;
	private String studentName;
	private String IdCard;
	
	public Student() {
		super();
	}
	
	public Student(String studentName, String idCard) {
		super();
		this.studentName = studentName;
		IdCard = idCard;
	}

	public String getObjectId() {
		return ObjectId;
	}
	public void setObjectId(String objectId) {
		ObjectId = objectId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getIdCard() {
		return IdCard;
	}
	public void setIdCard(String idCard) {
		IdCard = idCard;
	}

	@Override
	public String toString() {
		return "Student [ObjectId=" + ObjectId + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", studentName=" + studentName
				+ ", IdCard=" + IdCard + "]";
	}
	
}
