package com.controller;

import java.io.IOException;
import java.util.List;

import com.dao.UserInsuranceDao;
import com.model.Totalbean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/displayData")
public class DisplayDataServlet extends HttpServlet {

	private UserInsuranceDao userInsuranceDao;

	@Override
	public void init() throws ServletException {
		super.init();
		userInsuranceDao = new UserInsuranceDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 从请求中获取 UID 参数
//        int uid = Integer.parseInt(request.getParameter("uid"));
//		Integer uid = 1001;

		HttpSession session = ((HttpServletRequest) request).getSession();
		Integer uid=(int)session.getAttribute("userId");
		
		// 获取与特定 UID 相关的用户保险数据，并转换为 Totalbean 对象列表
		List<Totalbean> totalbeans = userInsuranceDao.getUserInsuranceByUid(uid);
//		List<Totalbean> totalbeans = userInsuranceDao.getAllUserInsurance();

		// 将 Totalbean 对象列表存储在请求属性中
		request.setAttribute("totalbeans", totalbeans);

		// 转发到 JSP 页面
		//這裡做權限判斷
		
//		request.getRequestDispatcher("OPdisplayData.jsp").forward(request, response);
		
		request.getRequestDispatcher("displayData.jsp").forward(request, response);
	}
}
