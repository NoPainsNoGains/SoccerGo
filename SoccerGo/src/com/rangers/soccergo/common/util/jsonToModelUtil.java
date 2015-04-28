package com.rangers.soccergo.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.vo.system.RoleVo;

public class jsonToModelUtil {
	
	public static String dateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateString=sdf.format(date);
		return dateString;
	}
	
}
