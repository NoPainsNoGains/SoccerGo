package com.rangers.soccergo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.rangers.soccergo.dao.TeacherDao;
import com.rangers.soccergo.model.Teacher;
import com.rangers.soccergo.service.TeacherService;
@Component("teacherServiceImpl")
public class TeacherServiceImpl implements TeacherService{
	private TeacherDao teacherDaoImpl;
	
	public TeacherDao getTeacherDaoImpl() {
		return teacherDaoImpl;
	}
	@Resource(name="teacherDaoImpl")
	public void setTeacherDaoImpl(TeacherDao teacherDaoImpl) {
		this.teacherDaoImpl = teacherDaoImpl;
	}

	public List<Teacher> listService() {
		return teacherDaoImpl.list();
	}

	public void saveService(Teacher teacher) {
		teacherDaoImpl.save(teacher);
	}

}
