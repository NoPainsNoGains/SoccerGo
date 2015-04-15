package com.rangers.soccergo.service;

import java.util.List;

import com.rangers.soccergo.model.Teacher;

public interface TeacherService {
	public void saveService(Teacher teacher);
	public List<Teacher> listService();
}
