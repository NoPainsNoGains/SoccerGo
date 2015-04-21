package com.rangers.soccergo.test;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rangers.soccergo.db.util.CloudQuery;
import com.rangers.soccergo.db.util.CloudSession;
import com.rangers.soccergo.db.util.HttpClientUtil;
import com.rangers.soccergo.db.util.JsonResult;
import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.db.util.StringUtil;
import com.rangers.soccergo.model.Student;
import com.rangers.soccergo.model.Teacher;
import com.rangers.soccergo.model.User;


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
		
		System.out.println(list.size());
		for(int i=0;list!=null && i<list.size();i++){
			//Student ss = (Student)list.get(i);
			System.out.println(list.get(i));
			System.out.println(list.get(i).getIdCard());
			//System.out.println(ss.getIdCard());
			
		}
	}
	@Test
	public void testQuery() throws ClassNotFoundException{
		CloudSession session = new CloudSession();
		String cql = "select objectId from Student";
		CloudQuery query = session.executeQuery(cql);
	    //System.out.println(StringUtil.countChar(cql, "e"));
		/*query.setParam(0, 1);
		query.setParam(1, "a");
		query.setParam(2,true);*/
		//query.setParam(0, "552bb4fee4b01e374fa11143");
		//System.out.println(entity);
		List<Object> list = query.list();
		for(int i=0;list!=null&&i<list.size();i++){
			//System.out.println(list.get(i)[0]);
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
	@Test
	public void testRole(){
		CloudSession session = new CloudSession();
		List<User> list = (List<User> )session.get(new User());
		//System.out.println(list.get(0).getClass());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testJson() throws Exception{
        URI uri = new URIBuilder()
        .setScheme("https")
        .setHost("leancloud.cn")
        .setPort(443)
        .setPath("/1.1/classes/Student").build();
		String json = HttpClientUtil.getInstance().sendGetRequest(uri);
		System.out.println(json);		System.out.println(json.indexOf(":"));
        System.out.println(json.substring(10, json.length()));
        json = json.substring(11, json.length()-1);
        System.out.println(json);
		//List<Student> results = (List<Student>) JsonUtil.getInstance().json2obj(json,List.class);
		//List<Student> list = (List<Student>)JsonUtil.getInstance().jsonlist2obj(json);
		ObjectMapper mapper = new ObjectMapper();  
		//JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Student.class);  
		//如果是Map类型  mapper.getTypeFactory().constructParametricType(HashMap.class,String.class, Bean.class);  
		//List<Student> list =  (List<Student>)mapper.readValue(json, javaType); 
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<Student> list = mapper.readValue(json, new TypeReference<List<Student>>() {});
		System.out.println(list.get(0).getClass());
		System.out.println(list.get(0).getIdCard());
		
	}
	@Test
	public void testutil() throws Exception{
		URI uri = new URIBuilder()
        .setScheme("https")
        .setHost("leancloud.cn")
        .setPort(443)
        .setPath("/1.1/classes/Student").build();
		String json = HttpClientUtil.getInstance().sendGetRequest(uri);
        json = json.substring(11, json.length()-1);
        System.out.println(json);
        @SuppressWarnings("unchecked")
		List<Student> list = JsonUtil.getInstance().json2list(json,Student.class);
        System.out.println(list.get(0));
        
	}
}

