package com.rangers.soccergo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.model.Teacher;
import com.rangers.soccergo.service.TeacherService;

@Controller   
@RequestMapping("/Teacher") 
public class TeacherController {
	private TeacherService teacherServiceImpl;

	public TeacherService getTeacherServiceImpl() {
		return teacherServiceImpl;
	}
	@Resource(name="teacherServiceImpl")
	public void setTeacherServiceImpl(TeacherService teacherServiceImpl) {
		this.teacherServiceImpl = teacherServiceImpl;
	}
	@RequestMapping("/list.action") 
	public void list(HttpServletRequest request,HttpServletResponse response){
		List<Teacher> list = teacherServiceImpl.listService();
		String jsonStr = JsonUtil.list2json(list);
		PrintWriter out = null;
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/add.action")
	public String add(Teacher teacher,HttpServletRequest request,HttpServletResponse response){
		System.out.println("教师名: "+teacher.getTeacherName()+"    教师Id: "+teacher.getTeacherId());
		teacherServiceImpl.saveService(teacher);
		return "/Teacher/list";
	}
}
