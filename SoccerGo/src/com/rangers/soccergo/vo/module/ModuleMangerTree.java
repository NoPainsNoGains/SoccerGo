package com.rangers.soccergo.vo.module;

import java.util.List;

import com.rangers.soccergo.model.Module;
public class ModuleMangerTree {
	private String id;
	private String text; 
	private List<ModuleMangerTree> children;         
	private String fatherId;
	private int state;
	private String remark;
	private String module_code;
	private boolean isCatalogue;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getModule_code() {
		return module_code;
	}
	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}
	public boolean isCatalogue() {
		return isCatalogue;
	}
	public void setCatalogue(boolean isCatalogue) {
		this.isCatalogue = isCatalogue;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<ModuleMangerTree> getChildren() {
		return children;
	}
	public void setChildren(List<ModuleMangerTree> children) {
		this.children = children;
	}
	public String getFatherId() {
		return fatherId;
	}
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
	
