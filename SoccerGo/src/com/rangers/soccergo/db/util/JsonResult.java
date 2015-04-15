package com.rangers.soccergo.db.util;
import java.util.List;


public class JsonResult<T> {
	private List<T> results;
	private String className;
	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "JsonResult [results=" + results + "]";
	}
	
}
