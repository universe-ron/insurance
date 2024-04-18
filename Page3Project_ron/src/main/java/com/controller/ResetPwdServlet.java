package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.util.JdbcConnUtil;

@WebServlet("/ResetPwd.do")
public class ResetPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private Connection conn;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 抓取欄位
		String userName = request.getParameter("userName");
		String userBirthDate = request.getParameter("userbirthDate");
		String userGender = request.getParameter("userGender");
		String userMail = request.getParameter("userMail");
		String userAccount = request.getParameter("userAccount");
		String userPwd = request.getParameter("userPwd");

		try {
			out = response.getWriter();
			JdbcConnUtil connUtil = new JdbcConnUtil();
			conn = connUtil.createConn();

			String sql = "update [dbo].[User] set password=? where name=? and birthDate=? and gender=? and account=? and mail=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userPwd);
			pstmt.setString(2, userName);
			pstmt.setString(3, userBirthDate);
			pstmt.setString(4, userGender);
			pstmt.setString(5, userAccount);
			pstmt.setString(6, userMail);

			int count = pstmt.executeUpdate();
			String contextPath = request.getContextPath();
			
			if (count > 0) {// 如果有成功更動
				out.write("<h2>" + userAccount + "更新密碼成功!</h2>");
				out.write("<h2>可點選以下連結回到登入畫面</h2>");
				out.write("<a href='" + contextPath + "/LoginPage.jsp'>Back to Login Page</a><br>");
				//out.write("<a href='http://localhost:8080/LogInTeam/loginPageH.html'>Back to Login Page</a><br>");
			} else {
				out.write("您所輸入的資料有誤，請再重新確認!");
				out.write("<h2>可點選以下連結回到重設密碼畫面</h2>");				
				out.write("<a href='" + contextPath + "/ResetPwdPage.jsp'>Back to Reset Page</a><br>");
				//out.write("<a href='http://localhost:8080/LogInTeam/ResetPwdPage.jsp'>Back to Reset Page</a><br>");
			}

			connUtil.closeConn();

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
