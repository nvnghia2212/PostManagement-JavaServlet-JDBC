package com.javajdbc.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javajdbc.model.NewModel;
import com.javajdbc.service.INewService;
import com.javajdbc.utils.HttpUtil;

@WebServlet(urlPatterns = "/api-admin")
public class NewAPI extends HttpServlet {

	@Inject
	private INewService newService;

	private static final long serialVersionUID = -8928180615502284625L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

// Để server nhận biết kiểu tiếng việt từ client gửi về server (request)
		request.setCharacterEncoding("UTF-8");

// Để server trả dữ liệu kiểu json cho client (respon)
		response.setContentType("application/json");

// Gọi hàm convert json thành string và mapping dữ liệu vào model để insert database
		// HttpUtil.of(request.getReader()).toModel(NewModel.class);
		NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		// newService.insert(newModel);
		newModel = newService.insert(newModel);

// Convert model thành json
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), newModel);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		newModel = newService.update(newModel);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(),newModel);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		newService.delete(newModel.getIds());
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), "{ }");
	}
}
