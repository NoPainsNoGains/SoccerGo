package com.rangers.soccergo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_DEFAULT)
public class User {
	/*系统默认字段*/
	private String objectId;
	private Date createdAt;
	private Date updatedAt;
    private String username;
    private boolean emailVerified;
    private boolean mobilePhoneVerified;  
    private String mobilePhoneNumber;
    private String email;
    private String authData;
    /*end*/
     
    public String getAuthData() {
		return authData;
	}
	public void setAuthData(String authData) {
		this.authData = authData;
	}
	/*自定义字段*/
    private int prefered_role;  //擅长位置
    private String nickname;    //昵称
    private String points;      //积分
    private int level;        //等级
    //private Host host;
    
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}
	public boolean isMobilePhoneVerified() {
		return mobilePhoneVerified;
	}
	public void setMobilePhoneVerified(boolean mobilePhoneVerified) {
		this.mobilePhoneVerified = mobilePhoneVerified;
	}
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}
	public int getPrefered_role() {
		return prefered_role;
	}
	public void setPrefered_role(int prefered_role) {
		this.prefered_role = prefered_role;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [objectId=" + objectId + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", username=" + username
				+ ", emailVerified=" + emailVerified + ", mobilePhoneVerified="
				+ mobilePhoneVerified + ", mobilePhoneNumber="
				+ mobilePhoneNumber + ", email=" + email + ", authData="
				+ authData + ", prefered_role=" + prefered_role + ", nickname="
				+ nickname + ", points=" + points + ", level=" + level + "]";
	}
	
    
}
