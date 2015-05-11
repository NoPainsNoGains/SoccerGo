package com.rangers.soccergo.vo.module;

import java.util.List;
import net.sf.json.JSONObject;

public class ShowModuleTree {
	private String id;
	private String text;
	private String state = "open";
	private List children;
	private JSONObject attributes;                   //有个key为url
	
	public ShowModuleTree() {
	}
	public ShowModuleTree(String id, String text, String state, List children,
			JSONObject attributes) {
		this.id = id;
		this.text = text;
		this.state = state;
		this.children = children;
		this.attributes = attributes;
	}
	public ShowModuleTree(String id, String text, List children,JSONObject attributes) {
		this.id = id;
		this.text = text;
		this.children = children;
		this.attributes = attributes;
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
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public List getChildren() {
		return children;
	}
	
	public void setChildren(List children) {
		this.children = children;
	}
	
	public JSONObject getAttributes() {
		return attributes;
	}
	
	public void setAttributes(JSONObject attributes) {
		this.attributes = attributes;
	}
}
