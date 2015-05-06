package com.rangers.soccergo.common.util;


public class SplitUtil {
	/*字符串id转换成 id*/
	public static int[]  idsplit(String ids) {
		String[] StringArray = ids.split(",");
		int[] Intarray = new int[StringArray.length];
		for(int i=0;i<StringArray.length;i++)
			Intarray[i]=Integer.parseInt(StringArray[i]);			
		return  Intarray;
	}
	/*uid rolename 转换成  uid rid */
	public static String[] stringplit(String ids){	
		String[] StringArray = ids.split(",");
		return StringArray;
	}
}
