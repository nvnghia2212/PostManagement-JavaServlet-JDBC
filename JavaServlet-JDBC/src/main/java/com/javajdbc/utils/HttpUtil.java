package com.javajdbc.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	
// Convert Json thành chuỗi string
	private String value;
	
	public HttpUtil(String value) {
		this.value = value;
	}

	public static HttpUtil of(BufferedReader reader) {
		try {
			String line;
			StringBuilder stringBuilder = new StringBuilder();
			while((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			return new HttpUtil(stringBuilder.toString());
		} catch (IOException e) {
			return null;
		}
	}
//-----------------------------------------------------------------------------------	
	
// Mapping dữ liệu stringjson vào model, dùng hàm ObjectMapper().readValue("chuỗi string", "class model") để nó map tự động
	public <T> T toModel(Class<T> tModel){
		try {
			return new ObjectMapper().readValue(value, tModel);
		} catch (Exception e) {
			return null;
		}
	}
//------------------------------------------------------------------------------------
	
//	Convert Json thành chuỗi string
//	public static String of(BufferedReader reader) {
//		try {
//			String line;
//			StringBuilder stringBuilder = new StringBuilder();
//			while((line = reader.readLine()) != null) {
//				stringBuilder.append(line);
//			}
//			return value = stringBuilder.toString();
//		} catch (IOException e) {
//			return null;
//		}
//	}	
}
