package com.hrxt.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 利用jackson 将javabean对象转换为  json对象方法  
 * @author ZhaoQin
 *
 */
public class JSONutils {
	
	public static String Object2String(Object obj) {
		ObjectMapper mapper = new ObjectMapper();

        String json = "";
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

}
