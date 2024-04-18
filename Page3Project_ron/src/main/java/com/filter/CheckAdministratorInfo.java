package com.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import com.util.JdbcConnUtil;

//這個filter用來篩選"管理員"身份，具備管理員身份才能順利進到管理員系統的頁面

@WebFilter(urlPatterns = {"/OP.jsp"}) //←進到括弧中這些頁面之前必須先通過此filter
public class CheckAdministratorInfo implements Filter {
	
	private Connection conn;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = ((HttpServletRequest) request).getSession();

		String userAccount = (String)session.getAttribute("userAccount");
		String userPwd = (String)session.getAttribute("userPwd");
		
		System.out.println("In CheckAdministratorInfo Filter");
		System.out.println("filter info userAccount:" + userAccount);
		System.out.println("filter info password:" + userPwd);
				
		boolean AdminInfoCorrect = false;
		
		try {
			JdbcConnUtil connUtil = new JdbcConnUtil();
			conn = connUtil.createConn();
			String sql = "select * from [dbo].[User] where account=? and password=? and permission=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userAccount);
			pstmt.setString(2, userPwd);
			pstmt.setInt(3, 0);//管理員對應的permission為0
						
			ResultSet rs = pstmt.executeQuery();
			AdminInfoCorrect = rs.next();//如果有查到這個管理員，會是true
			connUtil.closeConn();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(AdminInfoCorrect) {
			System.err.println(userAccount + "驗證成功，此帳號為管理員");
			chain.doFilter(request, response);
		} else {
			System.out.println(userAccount + "驗證失敗，重新回到登入頁面");
			out.write("您目前尚未登入，<br>或者登入的帳密有誤，<br>請再重新登入，謝謝！<hr>");
			out.write("<a href='http://localhost:8080/Page3Project_ron/LoginPage.jsp'>Back to Login Page</a><br>");
		}
		out.close();		
	}
}