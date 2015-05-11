package com.rangers.soccergo.model;

import java.util.Date;

public class RoleAuthority {
	
	/*系统自带字段*/
	private String objectId;
	private Date createdAt;
	private Date updatedAt;
	/*end*/
	
	private String roleId;
	private String moduleSet;
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
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getModuleSet() {
		return moduleSet;
	}
	public void setModuleSet(String moduleSet) {
		this.moduleSet = moduleSet;
	}
	
}
