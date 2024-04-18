package com.controller;

import java.io.IOException;
import com.dao.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userAccount = request.getParameter("userAccount");//抓取欄位
		String userPwd = request.getParameter("userPwd");
		Integer permission = Integer.parseInt(request.getParameter("permission"));
		
		//確認是否抓到資料
		System.out.println("login info userAccount:" + userAccount);
		System.out.println("login info userPwd:" + userPwd);
		System.out.println("login info permission:" + permission);
		
		UserDao userDao = new UserDao();
		int userId = userDao.findUid(userAccount,userPwd);
	
		HttpSession session = request.getSession();//取得session
		session.setAttribute("userAccount", userAccount);//設成屬性
		session.setAttribute("userPwd", userPwd);
		session.setAttribute("userId", userId);
		session.setAttribute("permission", permission);		
		System.out.println("--------------------------");
		
		if(permission == 0) {//管理員對應的permission為0
			response.sendRedirect(request.getContextPath() + "/OP.jsp");//導入管理員系統的頁面
		} else {
			response.sendRedirect(request.getContextPath() + "/HomePage.jsp");//應該要導入會員中心頁面(因目前尚無，故先以投保頁面代替)
		}
	}
}

