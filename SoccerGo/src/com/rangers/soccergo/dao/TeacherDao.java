package com.rangers.soccergo.dao;

import java.util.List;

import com.rangers.soccergo.model.Teacher;

public interface TeacherDao {
	public List<Teacher> list();
	public void save(Teacher teacher);
}
