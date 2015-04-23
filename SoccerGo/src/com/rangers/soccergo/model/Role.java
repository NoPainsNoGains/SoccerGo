package com.rangers.soccergo.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rangers.soccergo.db.util.JsonUtil;
@JsonInclude(Include.NON_NULL)
public class Role {
	private String objectId;
	private static Map<String,Map<String,Boolean>> test = new HashMap<String, Map<String,Boolean>>();
	static {
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		map.put("read", true);
		map.put("write", true);
		test.put("*",map);
	}
	private Date createdAt;
	private Date updatedAt;
	private String name;
	private Map<String, Object> users;
	private Map<String, String> roles;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Map<String, Object> getUsers() {
		return users;
	}
	public void setUsers(Map<String, Object> users) {
		this.users = users;
	}
	public Map<String, String> getRoles() {
		return roles;
	}
	public void setRoles(Map<String, String> roles) {
		this.roles = roles;
	}
	@JsonProperty("ACL")
	public  Map<String, Map<String, Boolean>> getTest() {
		return test;
	}
	public  void setTest(Map<String, Map<String, Boolean>> test) {
		Role.test = test;
	}
	@Override
	public String toString() {
		return "Role [objectId=" + objectId + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", name=" + name + ", users="
				+ users + ", roles=" + roles + "]";
	}
	
}
