package com.rangers.soccergo.model;

import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_DEFAULT)
public class User {
	/*系统默认字段*/
	private String objectId;	
	private Date createdAt;	
	private Date updatedAt;
    private String username;
    private String password;
    private boolean emailVerified;
    private boolean mobilePhoneVerified;  
    private String mobilePhoneNumber;
    private String email;
    @JsonIgnore
    private String authData;
    private HashMap<String,Object> avatar;
    /*end*/
   
	/*自定义字段*/
    /* role 格式： 
     * "role": {
	    "__type": "Pointer",
	    "className": "_Role",
	    "objectId": "5539fe29e4b069c225add71f"
	}
	执行role.get("objectId")得到role的Id然后根据Id查询Role表
	*/
    private HashMap<String,Object> role;
 
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
    public String getAuthData() {
		return authData;
	}
	public void setAuthData(String authData) {
		this.authData = authData;
	}
	
	public HashMap<String, Object> getAvatar() {
		return avatar;
	}
	public void setAvatar(HashMap<String, Object> avatar) {
		this.avatar = avatar;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public HashMap<String, Object> getRole() {
		return role;
	}
	public void setRole(HashMap<String, Object> role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [objectId=" + objectId + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", username=" + username
				+ ", password=" + password + ", emailVerified=" + emailVerified
				+ ", mobilePhoneVerified=" + mobilePhoneVerified
				+ ", mobilePhoneNumber=" + mobilePhoneNumber + ", email="
				+ email + ", authData=" + authData + ", avatar=" + avatar
				+ ", role=" + role + ", prefered_role=" + prefered_role
				+ ", nickname=" + nickname + ", points=" + points + ", level="
				+ level + "]";
	}
	
	
    
}
