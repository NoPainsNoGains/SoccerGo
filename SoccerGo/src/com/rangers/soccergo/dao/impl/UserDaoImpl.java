package com.rangers.soccergo.dao.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;
import com.rangers.soccergo.dao.UserDao;
import com.rangers.soccergo.db.util.HttpClientUtil;
import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.model.User;

@Component("userDaoImpl")
public class UserDaoImpl extends CommonDaoImpl<User> implements UserDao {
	//用户登录
	public User login(String userName, String password) {
		URI uri = null;
		try {
			uri = new URIBuilder()
			.setScheme("https")
			.setHost("leancloud.cn")
			.setPort(443)
			.setPath("/1.1/login").setParameter("username", userName).setParameter("password", password).build();
		} catch (URISyntaxException e) {			
			e.printStackTrace();
		} 	
		//System.out.println("uri 地址： "+uri);
		String res = HttpClientUtil.getInstance().sendGetRequest(uri);
		System.out.println("GET返回的值： " + res);
		if(res==null || res.equals("")){
			return null;
		}
		return (User) JsonUtil.getInstance().json2obj(res, User.class);
	}

}
