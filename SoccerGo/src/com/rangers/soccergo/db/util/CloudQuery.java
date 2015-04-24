package com.rangers.soccergo.db.util;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;

import com.rangers.soccergo.model.Student;


public class CloudQuery {
	private String cql;
	private Object[] params;
	private URIBuilder uriBuilder;
	private String[] cqls;
	public CloudQuery() {
		super();
	}
 
	public CloudQuery(URIBuilder uriBuilder, String cql) {
		super();
		this.cql = cql;
		this.uriBuilder = uriBuilder;
		this.params = new Object[StringUtil.countChar(cql, "?")];
	}
	
	public void setParam(int arg0,Object arg1){
		if(arg0<StringUtil.countChar(cql, "?")){
			params[arg0] = arg1;
		}
		else{
			System.err.println("CQL 语句 变量个数越界");
			throw new ArrayIndexOutOfBoundsException("CQL 语句 变量个数越界");
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> list(){
		try {
			URI uri = uriBuilder.setParameter("cql", cql)
					            .setParameter("pvalues",formatParams(params)).build();
			System.out.println("uri 地址："+uri);
			String res = HttpClientUtil.getInstance().sendGetRequest(uri);
			System.out.println(res);
			if(res == null || res.equals("")||res.equals("{\"results\":[]}")){
				return null;
			}
			else{
				res = res.substring(res.indexOf("["), res.indexOf("]")+1);
				System.out.println(res);
				int index = findBystr(cql);
				Class entity = null;
				if(index != -1){
					if(cqls[index-1].contains("*")){
						entity = StringUtil.String2clz(cqls[index+1]);
					}
					else{
						entity = Map.class;
					}
				}else{
					System.err.println("CQL 语句 语法错误");
					throw new ArrayIndexOutOfBoundsException("CQL 语句 语法错误");
				}
				return JsonUtil.getInstance().json2list(res,entity);				
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private String formatParams(Object[] objects){
		//System.out.println(StringUtil.paramsToString(objects));
		return StringUtil.paramsToString(objects);
	}
	private int findBystr(String cql){
		if(cql == null && cql.equals(""))
			return -1;
		cqls = cql.split(" ");
		for(int i=0;i<cqls.length;i++){
			if(cqls[i].equals("from") || cqls[i].equals("FROM")){
				return i;
			}
		}
		return -1;
	}
	
	public Object exeResult(String key){
		try {
			URI uri = uriBuilder.setParameter("cql", cql)
					            .setParameter("pvalues",formatParams(params)).build();
			System.out.println("uri 地址："+uri);
			String res = HttpClientUtil.getInstance().sendGetRequest(uri);
			System.out.println(res);
			HashMap<String, Object> map = (HashMap<String, Object>) JsonUtil.getInstance().json2obj(res, Map.class);
			if(map.containsKey(key))
			     return map.get(key);
			else{
				return null;
			}
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
