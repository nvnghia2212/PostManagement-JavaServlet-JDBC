package com.javajdbc.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javajdbc.model.CommentModel;
import com.javajdbc.service.ICommentService;
import com.javajdbc.utils.HttpUtil;

@WebServlet(urlPatterns = "/api-admin-comment")
public class CommentAPI extends HttpServlet {
	
	@Inject
	ICommentService commentService;
	
	private static final long serialVersionUID = 7762556264898081085L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		CommentModel commentModel = HttpUtil.of(request.getReader()).toModel(CommentModel.class);
		commentModel = commentService.insert(commentModel);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), commentModel);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		CommentModel commentModel = HttpUtil.of(request.getReader()).toModel(CommentModel.class);
		commentModel = commentService.update(commentModel);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), commentModel);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		CommentModel commentModel = HttpUtil.of(request.getReader()).toModel(CommentModel.class);
		commentService.delete(commentModel.getIds());
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), "{ }");
	}
}
