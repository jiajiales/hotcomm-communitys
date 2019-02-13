package com.hotcomm.framework.comm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetParamNull {

	private static final Map<String, List<Field>> CACHE = new HashMap<String, List<Field>>();

	private static List<Field> getFieldOfClass(Class<?> clazz) {
		List<Field> fields = CACHE.get(clazz.getName());
		if (fields == null) {
			Field[] fieldArr = clazz.getDeclaredFields();
			fields = new ArrayList<Field>(fieldArr.length);
			for (Field each : fieldArr) {
				each.setAccessible(true);
				fields.add(each);
			}
			CACHE.put(clazz.getName(), fields);
		}
		return fields;
	}

	public static <T> T setObjVal(T obj) {
		if (obj == null) {
			return null;
		}
		List<Field> fields = getFieldOfClass(obj.getClass());
		for (int j = 0, size = fields.size(); j < size; j++) {
			try {
				Field each = fields.get(j);//获取属性名称
				if(each.get(obj)=="") {
					each.set(obj, null);
				}
//				System.out.println(each.get(obj));//获取对象的值
//				each.set(obj, "1111");//设置对象的值
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
/*	
	public static <T> T setObj0(T obj) {
		if (obj == null) {
			return null;
		}
		List<Field> fields = getFieldOfClass(obj.getClass());
		for (int j = 0, size = fields.size(); j < size; j++) {
			try {
				Field each = fields.get(j);//获取属性名称
				if(each.get(obj)=="") {
					each.set(obj, null);
				}
//				System.out.println(each.get(obj));//获取对象的值
//				each.set(obj, "1111");//设置对象的值
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return obj;
	}*/
}
