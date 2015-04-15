package com.rangers.soccergo.db.util;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;


public class CloudQuery {
	private String cql;
	private Object[] params;
	private URIBuilder uriBuilder;
	
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
				JsonResult<T> jr = (JsonResult<T>) JsonUtil.getInstance().json2obj(res, JsonResult.class);		
				return jr.getResults();
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private String formatParams(Object[] objects){
		System.out.println(StringUtil.paramsToString(objects));
		return StringUtil.paramsToString(objects);
	}
}
