package com.javajdbc.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
	// Hạn chế việc new quá nhiều đối tượng SessionUtil, nên cần kiểm tra để tái sử dụng đối tượng đã được khởi tạo ban đầu
	private static SessionUtil sessionUtil = null;
	
	public static SessionUtil getInstance() {
		if (sessionUtil == null) {
			sessionUtil = new SessionUtil();
		}
		return sessionUtil;
	}
	//-------------------------------------------------------------------
	
	// Session đáp ứng 3 việc: putValue để lưu và duy trì thông tin người dùng, getValue để lấy thông tin ra, removeValue để xóa khi thoát ra khỏi hệ thống
	public void putValue(HttpServletRequest request, String key, Object value) {
		request.getSession().setAttribute(key, value);
	}
	
	public Object getValue(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}
	
	public void removeValue(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}
}
