package com.javajdbc.controller.user;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javajdbc.model.UserModel;
import com.javajdbc.service.IUserService;
import com.javajdbc.utils.GetParamUtils;
import com.javajdbc.utils.SessionUtil;

@WebServlet(urlPatterns = {"/home","/login","/logout"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 2231144058574894876L;

	@Inject
	private IUserService userService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			RequestDispatcher requestDisp = request.getRequestDispatcher("/views/login.jsp");
			requestDisp.forward(request, response);
		}else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USER");
			response.sendRedirect(request.getContextPath()+"/login");
		}else {
//		RequestDispatcher requestDisp = request.getRequestDispatcher("/views/user/home.jsp");
		RequestDispatcher requestDisp = request.getRequestDispatcher("/views/login.jsp");
		requestDisp.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			UserModel userModel = GetParamUtils.toModel(UserModel.class, request);
			userModel = userService.findByUserNameAndPassword(userModel.getUserName(), userModel.getPassword());
			if (userModel != null) {
//				PutValue vào session
				SessionUtil.getInstance().putValue(request, "USER", userModel);
				if (userModel.getRole().getCode().equals("ADMIN")) {
					response.sendRedirect(request.getContextPath()+"/admin-home");
				}else if(userModel.getRole().getCode().equals("USER")) {
					response.sendRedirect(request.getContextPath()+"/login?action=login");
				}
			}else {
				response.sendRedirect(request.getContextPath()+"/login?action=login");
			}
		}
		
	}

}
