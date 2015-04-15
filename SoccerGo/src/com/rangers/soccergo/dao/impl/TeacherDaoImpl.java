package com.rangers.soccergo.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.rangers.soccergo.dao.TeacherDao;
import com.rangers.soccergo.db.util.CloudSession;
import com.rangers.soccergo.model.Teacher;
@Component("teacherDaoImpl")
public class TeacherDaoImpl implements TeacherDao{
	private CloudSession session;
	
	public CloudSession getSession() {
		return session;
	}
	@Resource(name="session")
	public void setSession(CloudSession session) {
		this.session = session;
	}

	public List<Teacher> list() {
		List<Teacher> list = (List<Teacher>) session.get(new Teacher());
		return list;
	}

	public void save(Teacher teacher) {
		teacher.setDate(new Date());
		session.save(teacher);
	}
	
}
