package com.rangers.soccergo.vo.system;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rangers.soccergo.model.Role;
@Component("rolePage")
public class RolePage {
	private List<Role> rows;
	private int total;
	
	public List<Role> getRows() {
		return rows;
	}
	public void setRows(List<Role> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
