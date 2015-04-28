package com.rangers.soccergo.common.util;

public class Constant {
	private Constant()
	{

	}
	public static final String[] PREFERED_ROLE_NAME = {"前锋","中场","后卫","守门员","无"};
	/** 
	  * 通过ID返回擅长位置名
	  * @return String 擅长位置名
	 */
	public static String getNameByPreferedId(int preferedId)
	{
		String preferedName = null;
		switch(preferedId){
			case 1:
				preferedName = PREFERED_ROLE_NAME[0];
				break;
			case 2:
				preferedName = PREFERED_ROLE_NAME[1];
				break;
			case 3:
				preferedName = PREFERED_ROLE_NAME[2];
				break;
			case 4:
				preferedName = PREFERED_ROLE_NAME[3];
				break;
			default:
				preferedName = PREFERED_ROLE_NAME[4];
				break;
		}
		return preferedName;
	}
}
