package com.javajdbc.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javajdbc.model.NewModel;
import com.javajdbc.model.UserModel;
import com.javajdbc.service.INewService;
import com.javajdbc.service.IUserService;
import com.javajdbc.utils.GetParamUtils;
import com.javajdbc.utils.SessionUtil;

@WebServlet(urlPatterns = "/admin-home")
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 2231144058574894876L;
	
	@Inject
	private IUserService userService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
			RequestDispatcher requestDisp = request.getRequestDispatcher("/views/admin/home.jsp");
			requestDisp.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
