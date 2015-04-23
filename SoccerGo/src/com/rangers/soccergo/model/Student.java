package com.rangers.soccergo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_DEFAULT)
public class Student {
	private String objectId;
	@JsonIgnore
	private Date createdAt;
	@JsonIgnore
	private Date updatedAt;
	private String studentName;
	private String idCard;
	
	public Student() {
		super();
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getIdCard() {
		return idCard;
	}


	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}


	@Override
	public String toString() {
		return "Student [objectId=" + objectId + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", studentName=" + studentName
				+ ", idCard=" + idCard + "]";
	}
	
}
