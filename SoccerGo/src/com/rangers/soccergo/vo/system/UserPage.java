package com.rangers.soccergo.vo.system;

import java.util.List;
import org.springframework.stereotype.Component;

@Component("userPage")
public class UserPage {
	private List<UserVo> rows;
	private int total;
	public List<UserVo> getRows() {
		return rows;
	}
	public void setRows(List<UserVo> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
