package com.rangers.soccergo.util;

import java.lang.reflect.ParameterizedType;

public class GenericSuperClass {
	public static Class getClass(Class tClass){
		ParameterizedType pt = (ParameterizedType)tClass.getGenericSuperclass();
		Class entity = (Class) pt.getActualTypeArguments()[0];
		return entity;
	}
}
