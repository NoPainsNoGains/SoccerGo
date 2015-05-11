package com.rangers.soccergo.vo.module;

import java.util.ArrayList;
import java.util.List;

public class LoginTreePage {
	private String text;
	private List children= new ArrayList();
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List getChildren() {
		return children;
	}
	public void setChildren(List children) {
		this.children = children;
	}
}
