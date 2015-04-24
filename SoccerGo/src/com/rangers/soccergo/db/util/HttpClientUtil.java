package com.rangers.soccergo.db.util;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


public class HttpClientUtil {
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);
	private CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
	private static HttpClientUtil instance;
	//单例模式
	public static HttpClientUtil getInstance(){
		if(instance == null){
			return new HttpClientUtil();
		}
		return instance;
	}
	private HttpClientUtil(){
		
	}
	
	public String sendPostRequest(URI uri,String json){
		HttpPost httpPost = new HttpPost(uri);
		//PostMethod postMethod = new UTF8PostMethod(uri.toString());
		
		httpPost.setHeaders(setHead());		      
		try {
			 //将json格式的实体放入post中   编码为utf-8
			 StringEntity s = new StringEntity(json,"utf-8");
			 httpPost.setEntity(s);
			 System.out.println("POST　Entity: " + json);
			 //System.out.println(httpPost.getRequestLine());
			 logger.info(httpPost.getRequestLine().toString());		
			 //执行post请求  
	         HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
	         //响应状态  
	         System.out.println("status:" + httpResponse.getStatusLine());
	         HttpEntity entity = httpResponse.getEntity();	         
	         if(entity != null){
	        	 String rjson = EntityUtils.toString(entity);
	        	 System.out.println("response content:" + rjson); 	        	 
	        	 Map<String,String> res = (Map<String, String>) JsonUtil.getInstance().json2obj(rjson, Map.class);
	        	 //报错
	        	 if(res.containsKey("error")){
	        		 System.err.println(res.get("error"));
	        	 }
	        	 if(res.containsKey("code")){
	        		 return res.get("code");
	        	 }
	         }
	         
		}catch (ClientProtocolException e) {
			logger.debug("postData Exception url: "+uri.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			logger.debug("postData Exception url: "+uri.toString());
			e.printStackTrace();
		}finally{
		  try {     //关闭流并释放资源  
			  if(closeableHttpClient != null)
                   closeableHttpClient.close();  
            } catch (IOException e) { 
                e.printStackTrace();  
            }  
		}
       return "failed";
	}
	
	public String sendGetRequest(URI uri){
		HttpGet httpGet = new HttpGet(uri);
		httpGet.setHeaders(setHead());
		String res = "";
		try {
			//执行get方法
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			//响应状态  
	        System.out.println("status:" + httpResponse.getStatusLine());
	        int code = httpResponse.getStatusLine().getStatusCode();
	        if(code != 200){
	        	System.err.println("***查询操作出错***");
	        }
	        else{	        
	        	HttpEntity entity = httpResponse.getEntity();	        
		        if(entity != null){
		        	//System.out.println("contentEncoding:" + entity.getContentEncoding()); 
		        	res = EntityUtils.toString(entity,"utf-8");	        	
		        }
	        }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			  try {     //关闭流并释放资源  
	                closeableHttpClient.close();  
	            } catch (IOException e) { 
	                e.printStackTrace();  
	            }  
			}
		return res;
		
	}
	public void sendPutRequest(URI uri,String json){
		HttpPut httpPut = new HttpPut(uri);
		httpPut.setHeaders(setHead());
		try {
			//将json放入put中
			StringEntity s = new StringEntity(json,"utf-8");
			httpPut.setEntity(s);
			HttpResponse httpResponse = closeableHttpClient.execute(httpPut);
			System.out.println("PUT Entity : " + json);
			//System.out.println("status:" + httpResponse.getStatusLine());
			int code = httpResponse.getStatusLine().getStatusCode();
		        if(code != 200){
		        	System.err.println("***更新操作出错***");
		        }
		    HttpEntity entity = httpResponse.getEntity();	         
		    if(entity != null){
		        	 String rjson = EntityUtils.toString(entity);
		        	 System.out.println("response content:" + rjson); 
		    }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			  try {     //关闭流并释放资源  
	                closeableHttpClient.close();  
	            } catch (IOException e) { 
	                e.printStackTrace();  
	            }  
			}
	}
	
	public void sendDeleteRequest(URI uri){
		HttpDelete httpDelete = new HttpDelete(uri);
		httpDelete.setHeaders(setHead());
		try {
			HttpResponse httpResponse = closeableHttpClient.execute(httpDelete);
			//响应状态  
	        //System.out.println("status:" + httpResponse.getStatusLine());
	        int code = httpResponse.getStatusLine().getStatusCode();
	        if(code != 200){
	        	System.err.println("***删除操作出错***");
	        }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			  try {     //关闭流并释放资源  
	                closeableHttpClient.close();  
	            } catch (IOException e) { 
	                e.printStackTrace();  
	            }  
			}
		logger.info("删除操作完成");
		System.out.println("删除操作完成");
	}
	
	private static Header[] setHead(){
		Header[] headers = new BasicHeader[3];
	    headers[0] = new BasicHeader("Content-Type","application/json;charset=utf-8");
	    headers[1] = new BasicHeader("X-AVOSCloud-Application-Id","eq50ix2xybjwgxvjmh92uqu089vqcx1zjggqf1w8mb8159ry");
	    headers[2] = new BasicHeader("X-AVOSCloud-Application-Key","gc1k1wlvj37vfc7508otdhnsu0a190tq7hkhdt0ste0che4w");
		return headers;
	}
}
