package com.javajdbc.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class GetParamUtils {
	
	@SuppressWarnings("unchecked")
	public static <T> T toModel(Class<T> classModel, HttpServletRequest request){
		T obiect = null;
		try {	
			obiect = classModel.newInstance();
//	Sử dụng BeanUtils Maven tự động get param từ jsp và map vào model	mà không cần convert kiểu của từng param	
			BeanUtils.populate(obiect, request.getParameterMap());
			
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.print(e.getMessage());
		}
		return obiect;
	}
}
