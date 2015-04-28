package com.rangers.soccergo.db.util;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;

@Component("session")
public class CloudSession {
	private static URIBuilder uriBuilder = new URIBuilder();
	private static URI uri;
	private static String uriPath = "/1.1/classes/";
	private static Map<String,String> classMap = new Hashtable<String, String>();
	static{
		classMap.put("_User", "users");
		classMap.put("_Role", "roles");
	}
	private <T> void init(T t){
		String path = StringUtil.getClassName(t.getClass().toString());	
		if(classMap.get(path) == null){
			path = uriPath + path;
		}else{
			path = "/1.1/" + classMap.get(path);
		}
		try {
			uri = new URIBuilder()
			.setScheme("https")
			.setHost("leancloud.cn")
			.setPort(443)
			.setPath(path).build();
		} catch (URISyntaxException e) {			
			e.printStackTrace();
		} 	
		System.out.println("uri 地址： "+uri);
	}
	private <T> void init(T t,Serializable id){
		String path = StringUtil.getClassName(t.toString());
		if(classMap.get(path) == null){
			path = uriPath + path + "/" + id;
		}else{
			path = "/1.1/" + classMap.get(path) + "/" + id;
		}
		//path = uriPath + path +"/"+ id;
		try {
			uri = new URIBuilder()
			.setScheme("https")
			.setHost("leancloud.cn")
			.setPort(443)
			.setPath(path).build();
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		} 
		//uri地址
		System.out.println("uri 地址： "+uri);
		
	}
	//保存
	public <T> boolean save(T t){		
		//初始化uri
		init(t);
		//将实体转化为json格式
		String json = JsonUtil.getInstance().obj2json(t);
		//执行post方法
		String res = HttpClientUtil.getInstance().sendPostRequest(uri, json);
		return res.equals("succeed")?true:false;
	}
	//更新
	public <T> void update(T t){
		this.save(t);
	}
	
	/*
	 * 根据id进行更新
	 * 实体类中必须有getObjectId方法
	 * 
	 */
	public <T> boolean update(T t,Serializable id){
		//反射机制 得到ID
		try {
			Method m = t.getClass().getMethod("getObjectId",null);
			//得到id
			String s = (String) m.invoke(t,null);
			init(t.getClass(),s);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json = JsonUtil.getInstance().obj2json(t);
		String res = HttpClientUtil.getInstance().sendPutRequest(uri,json);
		return res.equals("succeed")?true:false;
		
	}
	
	//按照id查询
	@SuppressWarnings("rawtypes")
	public Object get(Class entity,Serializable id){
		init(entity,id);
		String res = HttpClientUtil.getInstance().sendGetRequest(uri);
		System.out.println("GET返回的值： " + res);
		if(res == null || res.equals("")||res.equals("{}")){
			return null;
		}
		else{			
			return JsonUtil.getInstance().json2obj(res, entity);
		}
	}
	//查询整个表  
	//返回 null 表示 表中没有值或失败
	@SuppressWarnings("unchecked")
	public <T> List<T> get(T t){
		init(t);
		String res = HttpClientUtil.getInstance().sendGetRequest(uri);
		//System.out.println(res);		
		if(res == null || res.equals("")||res.equals("{\"results\":[]}")){
			return null;
		}
		else{
			res = res.substring(res.indexOf(":")+1, res.length()-1);
			System.out.println(res);
			return JsonUtil.getInstance().json2list(res,t.getClass());
			
		}	
	}
	//执行cql语句
	public CloudQuery executeQuery(String cql){
		uriBuilder.setScheme("https")
        .setHost("leancloud.cn")
        .setPort(443)
        .setPath("/1.1/cloudQuery");		
		return new CloudQuery(uriBuilder,cql);
	}
	//删除操作
	public <T> void delete(T t){
		//将实体转化为json格式
		String json = JsonUtil.getInstance().obj2json(t);
		//利用json得到id
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String, String>) JsonUtil.getInstance().json2obj(json, Map.class);
		String id = map.get("objectId");
		//初始化uri
		init(t.getClass(),id);
		HttpClientUtil.getInstance().sendDeleteRequest(uri);
	}
}
