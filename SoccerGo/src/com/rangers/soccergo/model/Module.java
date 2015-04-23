package com.rangers.soccergo.model;

import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_DEFAULT)
public class Module {
	private String objectId;
	@JsonIgnore
	private Date createdAt;
	@JsonIgnore
	private Date updatedAt;
	private String module_name;
	private int status;
	private HashMap<String,Object> father_id;
	private String remark;
	private String page;
	private String module_code;
	private boolean isCatalogue;
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
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getModule_code() {
		return module_code;
	}
	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}
	public boolean isIsCatalogue() {
		return isCatalogue;
	}
	public void setCatalogue(boolean isCatalogue) {
		this.isCatalogue = isCatalogue;
	}
	
	public HashMap<String, Object> getFather_id() {
		return father_id;
	}
	public void setFather_id(HashMap<String, Object> father_id) {
		this.father_id = father_id;
	}
	@Override
	public String toString() {
		return "Module [objectId=" + objectId + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", module_name=" + module_name
				+ ", status=" + status + ", father_id=" + father_id
				+ ", remark=" + remark + ", page=" + page + ", module_code="
				+ module_code + ", isCatalogue=" + isCatalogue + "]";
	}
	
	
}
