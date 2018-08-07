package com.bdi.erp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdi.erp.service.UserService;
import com.bdi.erp.service.impl.UserServiceImpl;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService us = new UserServiceImpl();	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		boolean isLogin = us.login(id, pwd);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(isLogin) {
			out.println("로그인 성공!");
		}else {
			out.println("로그인 실패.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
