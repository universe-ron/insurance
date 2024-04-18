package com.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.JdbcConnUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//這個filter用來篩選"會員"身份，具備會員身份的使用者才能順利進到頁面3和頁面5

@WebFilter(urlPatterns = {"/InsurePage.jsp","/displayData"}) //←進到括弧中這些頁面之前必須先通過此filter
public class CheckMemberInfo implements Filter {
	
	private Connection conn;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = ((HttpServletRequest) request).getSession();

		String userAccount = (String)session.getAttribute("userAccount");
		String userPwd = (String)session.getAttribute("userPwd");
		
		System.out.println("In CheckMemberInfo Filter");
		System.out.println("filter info userAccount:" + userAccount);
		System.out.println("filter info password:" + userPwd);
				
		boolean memberInfoCorrect = false;
		
		try {
			JdbcConnUtil connUtil = new JdbcConnUtil();
			conn = connUtil.createConn();
			String sql = "select * from [dbo].[User] where account=? and password=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userAccount);
			pstmt.setString(2, userPwd);
						
			ResultSet rs = pstmt.executeQuery();
			memberInfoCorrect = rs.next();//如果有查到這個會員，會是true
			connUtil.closeConn();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(memberInfoCorrect) {
			System.err.println(userAccount + "驗證成功，是我們的會員");			
			chain.doFilter(request, response);
		} else {
			System.out.println(userAccount + "驗證失敗，重新回到登入頁面");
			out.write("您目前尚未登入，<br>或者登入的帳密有誤，<br>請再重新登入，謝謝！<hr>");
			out.write("<a href='http://localhost:8080/Page3Project_ron/LoginPage.jsp'>Back to Login Page</a><br>");
		}
		out.close();		
	}
}
