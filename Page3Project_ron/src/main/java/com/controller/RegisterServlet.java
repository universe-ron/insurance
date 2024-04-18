package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.util.JdbcConnUtil;

@WebServlet("/RegisterServlet.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private Connection conn;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/html;charset=UTF-8");

		String userName = request.getParameter("userName");
		String userBirthDate = request.getParameter("userbirthDate");
		String userGender = request.getParameter("userGender");
		String userMail = request.getParameter("userMail");
		String userAccount = request.getParameter("userAccount");
		String userPwd = request.getParameter("userPwd");
		String phone = request.getParameter("phone");
		String phone2 = request.getParameter("phone2");		
		String  address= request.getParameter("address");

		// 開始檢查有沒有註冊過
		boolean registerInfoCorrect = false;
		try {
			out = response.getWriter();
			JdbcConnUtil connUtil = new JdbcConnUtil();
			conn = connUtil.createConn();
			String sql = "select * from [dbo].[User] where mail=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userMail);

			ResultSet rs = pstmt.executeQuery();
			registerInfoCorrect = rs.next();// 如果有查到這個信箱，會為true
			connUtil.closeConn();

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		if (registerInfoCorrect) {// 代表註冊過了
			System.err.println(userMail + "很抱歉，您已經註冊過會員了!");
			out.write("<h2>" + userMail + "很抱歉，此帳號已經註冊過會員了!</h2>");
			out.write("<h2>可點選以下連結回到登入畫面</h2>");
			out.write("<a href='http://localhost:8080/Page3Project_ron/LoginPage.jsp'>Back to Login Page</a><br>");
//			chain.doFilter(request, response);
		} else {

			try {

				JdbcConnUtil connUtil = new JdbcConnUtil();
				Connection conn = connUtil.createConn();

				if (conn != null) {
					String sql = "insert into [dbo].[User](name,birthDate,gender,mail,account,password,address,phone,phone2,permission) values(?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userName);
					pstmt.setString(2, userBirthDate);
					pstmt.setString(3, userGender);
					pstmt.setString(4, userMail);
					pstmt.setString(5, userAccount);
					pstmt.setString(6, userPwd);
					pstmt.setString(7, address);
					pstmt.setString(8, phone);
					pstmt.setString(9, phone2);
					pstmt.setInt(10, 1);
					pstmt.executeUpdate();
					System.out.println(userMail + "新會員註冊成功註冊成功");
					out.write("<h2>註冊成功，可點選以下連結回到登入畫面</h2>");
					out.write("<a href='http://localhost:8080/Page3Project_ron/LoginPage.jsp'>Back to Login Page</a><br>");
					pstmt.close();

					connUtil.closeConn();
				}
				out.close();

//				RequestDispatcher dispatcher = request.getRequestDispatcher("loginPageH.html");
//			    dispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
