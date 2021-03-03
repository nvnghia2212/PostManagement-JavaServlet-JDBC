package com.javajdbc.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javajdbc.constant.SystemConstant;
import com.javajdbc.model.NewModel;
import com.javajdbc.service.ICategoryService;
import com.javajdbc.service.INewService;
import com.javajdbc.utils.GetParamUtils;
import com.javajdbc.utils.MessageUtil;

@WebServlet(urlPatterns = "/admin-new")
public class NewController extends HttpServlet {
	
	@Inject
	private INewService newService;
	
	@Inject
	private ICategoryService categoryService;

	private static final long serialVersionUID = 2231144058574894876L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		request.getParameter("type") == "LIST"
		
		String view = "";
		NewModel newModel = new NewModel();
		newModel = GetParamUtils.toModel(NewModel.class, request);
		
		if(newModel.getType().equals(SystemConstant.LIST)){
			
			newModel.setListModel(newService.findAll());
			view = "/views/admin/new/list.jsp";
			
		}else if(newModel.getType().equals(SystemConstant.EDIT)) {
			if(newModel.getId() != null) {
				newModel = newService.findOne(newModel.getId());
			}
			view = "/views/admin/new/edit.jsp";
			request.setAttribute("categories", categoryService.finAll());
		}
		MessageUtil.showMess(request);
		request.setAttribute(SystemConstant.MODEL, newModel);
		RequestDispatcher requestDisp = request.getRequestDispatcher(view);
		requestDisp.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
