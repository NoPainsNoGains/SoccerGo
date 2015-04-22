package com.rangers.soccergo.db.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {
	private static Map<String,String> map = new HashMap<String, String>();
	static{
		map.put("_User", "User");
		map.put("_Role", "Role");
	}
	//根据类字符串得到类名
	public static String getClassName(String claz){
		String temp = "";
		if(claz.lastIndexOf(".")!=-1){
			temp = claz.substring(claz.lastIndexOf(".")+1,claz.length());
		}
		else if(claz.lastIndexOf(" ")!=-1) {
			temp = claz.substring(claz.lastIndexOf(" ")+1,claz.length());
		}
		else{
			temp = claz;
		}
		return temp;
	}
	//判断一个字符串中一个字符的个数
	public static int countChar(String str,String c){
		int count = 0;
		int index = str.indexOf(c);
		while(index != -1){			
			count ++;
			str = str.substring(index+1,str.length());		
			index = str.indexOf(c);
		}
		return count;
	}
	
	public static String paramsToString(Object[] os){			
		StringBuffer sb = new StringBuffer("[");
		for(int i=0; os!=null && i<os.length;i++){
			Object o = os[i];
			String clzName = getClassName(o.getClass().toString());
			if("Integer".equals(clzName)){
				sb.append(o);
				sb.append(",");
			}else if("Boolean".equals(clzName)){
				sb.append(o);
				sb.append(",");
			}else if("String".equals(clzName)){
				sb.append("\"");
				sb.append(o);
				sb.append("\",");
			}
		}
		if(sb.lastIndexOf(",")!=-1){
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
		sb.append("]");
		return sb.toString();
	}
	public static Class String2clz(String str){
		Class entity = null;
		if(map.get(str)!=null){
			str = map.get(str);
		}
		try {
			entity = Class.forName("com.rangers.soccergo.model."+str);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}
	
}
