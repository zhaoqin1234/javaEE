package com.hrxt.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


/**
 
 */
public class Util{
	public static Map<String, Object> objectToMap(Object obj){
		Map<String, Object> map = new HashMap<String, Object>();
		if(obj == null){
			return map;
		}
		Class objClass = obj.getClass();
		Field[] fields = objClass.getDeclaredFields();
		try{
			for(Field field : fields){
				field.setAccessible(true);
				map.put(field.getName(), field.get(obj));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}

}
