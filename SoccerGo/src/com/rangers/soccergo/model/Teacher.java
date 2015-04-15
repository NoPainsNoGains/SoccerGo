package com.rangers.soccergo.model;

import java.util.Date;

public class Teacher {
	private String ObjectId;
	private String createdAt;
	private String updatedAt;
	private String teacherName;
	private String teacherId;
	private Date date;
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
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Teacher(String objectId, String createdAt, String updatedAt,
			String teacherName, String teacherId) {
		super();
		ObjectId = objectId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.teacherName = teacherName;
		this.teacherId = teacherId;
	}
	public Teacher() {
		super();
	}
	
	
}
