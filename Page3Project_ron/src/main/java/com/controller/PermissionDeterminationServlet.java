package com.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/PermissionDeterminationServlet.do")
public class PermissionDeterminationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();//取得session
		
		Object permission = session.getAttribute("permission");
		
		if (permission != null) {//代表此時為"已登入狀態"
			Integer intPermission = (Integer)permission;
			
			//確認目前登入者的權限，管理員的permission為0，一般會員的permission為1
			System.out.println("目前登入者的權限為：" + permission);
			System.out.println("-------------------------------");
			
				//權限判斷：若是管理員=>前往管理員系統；若是一般會員=>前往會員中心
				if(intPermission == 0) {
					response.sendRedirect(request.getContextPath() + "/OP.jsp");//導入管理員系統的頁面 (請放「管理員系統」的路徑)
				} else {
					response.sendRedirect(request.getContextPath() + "/displayData");//導入會員中心的頁面 (請放「會員中心」的路徑)
				}		
		} else {//代表此時為"未登入狀態"
			response.sendRedirect(request.getContextPath() + "/displayData");//同樣先導向會員中心頁面，讓filter去攔下來並提醒目前尚未登入
		}
	}
}
