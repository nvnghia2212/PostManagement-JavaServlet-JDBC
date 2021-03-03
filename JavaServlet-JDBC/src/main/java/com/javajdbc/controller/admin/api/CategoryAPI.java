package com.javajdbc.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javajdbc.model.CategoryModel;
import com.javajdbc.service.ICategoryService;
import com.javajdbc.utils.HttpUtil;

@WebServlet(urlPatterns = "/api-admin-category")
public class CategoryAPI extends HttpServlet{

	@Inject
	ICategoryService categoryService;
	
	private static final long serialVersionUID = -4369878557555596102L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		CategoryModel categoryModel = HttpUtil.of(request.getReader()).toModel(CategoryModel.class);
		categoryModel = categoryService.insert(categoryModel);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), categoryModel);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		CategoryModel categoryModel = HttpUtil.of(request.getReader()).toModel(CategoryModel.class);
		categoryModel = categoryService.update(categoryModel);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), categoryModel);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		CategoryModel categoryModel = HttpUtil.of(request.getReader()).toModel(CategoryModel.class);
		categoryService.delete(categoryModel.getIds());
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), "{ }");
	}
}
