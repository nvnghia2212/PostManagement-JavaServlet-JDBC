package com.javajdbc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javajdbc.constant.SystemConstant;
import com.javajdbc.model.UserModel;
import com.javajdbc.utils.SessionUtil;

public class AuthorizationFilter implements Filter {

	private ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String url = request.getRequestURI();
		
		if(url.startsWith("/Java-JDBC/admin-home")) {
			UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USER");
			if (userModel != null) {
				if (userModel.getRole().getCode().equals(SystemConstant.ADMIN)) {
					filterChain.doFilter(servletRequest, servletResponse);
				}else if (userModel.getRole().getCode().equals(SystemConstant.USER)) {
					response.sendRedirect(request.getContextPath()+"/login?action=login");
				}
			}else {
				response.sendRedirect(request.getContextPath()+"/login?action=login");
			}
		}else {
			response.sendRedirect(request.getContextPath()+"/login?action=login");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
