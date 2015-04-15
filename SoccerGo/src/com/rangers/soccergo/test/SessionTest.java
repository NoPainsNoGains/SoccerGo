package com.rangers.soccergo.test;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.rangers.soccergo.db.util.CloudQuery;
import com.rangers.soccergo.db.util.CloudSession;
import com.rangers.soccergo.db.util.StringUtil;
import com.rangers.soccergo.model.Student;
import com.rangers.soccergo.model.Teacher;


public class SessionTest {
	@Test
	public void testSave(){
		CloudSession session = new CloudSession();
		/*User u = new User("1005","王五",new Date());	;
		Student s = new Student("小明","20141001");
		session.save(u);
		session.save(s);
		session.save(s);
	    */
	}
	@Test
	public void testGetById(){
		CloudSession session = new CloudSession();
		Student ss = new Student("小明","20141001");
		session.save(ss);
		Student s = (Student)session.get(Student.class, "552b6947e4b01e374f9db67c");
		System.out.println(s);
		if(s == null){
			System.out.println("qwer");
		}
		/*String json="{}";
		User u = (User)JsonUtil.getInstance().json2obj(json, User.class);
		System.out.println(u);*/
	}
	@Test
	public void testGet(){
		CloudSession session = new CloudSession();
		List<Student> list = (List<Student>) session.get(new Student());
		//List<Object> list = (List<Object>)session.get(new Object());
		System.out.println(list == null);
		//System.out.println(list.size());
		for(int i=0;list!=null && i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	@Test
	public void testQuery(){
		CloudSession session = new CloudSession();
		String cql = "select * from Student";
		CloudQuery query = session.executeQuery(cql);
	    //System.out.println(StringUtil.countChar(cql, "e"));
		/*query.setParam(0, 1);
		query.setParam(1, "a");
		query.setParam(2,true);*/
		//query.setParam(0, "552bb4fee4b01e374fa11143");
		List<Student> list = query.list();
		for(int i=0;list!=null&&i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	@Test
	public void testStringUtil(){
		String s = "你好";
		Object[] os = {"你好",10,true};
		//StringUtil.paramsToString(os);
		System.out.println(StringUtil.paramsToString(os));
	}
	@Test
	public void testDel(){
		CloudSession session = new CloudSession();
		//Student s = new Student("张三", "123456");
		//session.save(s);
		Student s1 = (Student) session.get(Student.class,"552bb4abe4b02ec896de6ff5");
		Student s2 = new Student();
		s2.setObjectId("552bb4abe4b02ec896de6f");
		System.out.println(s1);
		session.delete(s2);
	}
	@Test
	public void save(){
		CloudSession session = new CloudSession();
		Teacher teacher = new Teacher(null,null,null,"阿斯顿","201321060312");
		teacher.setDate(new Date());
		session.save(teacher);
	}
	@Test
	public void getTeacherList(){
		CloudSession session = new CloudSession();
		List<Teacher> list = (List<Teacher>) session.get(new Teacher());
		//List<Object> list = (List<Object>)session.get(new Object());
	//	System.out.println(list == null);
		//System.out.println(list.size());
		for(int i=0;list!=null && i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
}

