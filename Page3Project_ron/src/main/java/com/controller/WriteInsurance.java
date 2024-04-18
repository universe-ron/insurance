package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.dao.InsuranceDao;
import com.dao.ProductDao;
import com.dao.UserInsuranceDao;
import com.dao.UserDao;
import com.model.Insurance;
import com.model.User;
import com.model.UserInsurance;
import com.util.HibernateUtil;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/WriteInsurance.do")
public class WriteInsurance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) 
	{
		

		String data = request.getParameter("data");
		String pname = request.getParameter("pname");
		String generations = request.getParameter("generations");
		String type = request.getParameter("type");
		String iname = request.getParameter("iname");
		
		
		
		
		System.out.println("Pname: " + pname);
        
        int iid;
        int uid = 1001;
        int pid = 0;
        ProductDao productDao = new ProductDao();
        pid = productDao.findPid(pname, generations, type);
        
		InsuranceDao insuranceDao = new InsuranceDao();
		Insurance insurance = insuranceDao.getInsurance(pid, iname);
		iid = insurance.getIid();
		
		HttpSession session = ((HttpServletRequest) request).getSession();
		uid=(int)session.getAttribute("userId");
		
        UserInsuranceDao userInsuranceDao = new UserInsuranceDao();
      
        
       
        
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.YEAR, 1);
        Date nextYear = calendar.getTime();
        
       
        
        UserInsurance userInsurance = new UserInsurance();
        userInsurance.setIid(iid); 
        userInsurance.setUid(uid); 
        userInsurance.setIdcode(data);
        userInsurance.setStartDate(today); 
        userInsurance.setEndDate(nextYear); 
        userInsurance.setState(1);
        
        boolean insertFlag = false;
        insertFlag=userInsuranceDao.insertUserInsurance(userInsurance);
        
        if(insertFlag)
        {
        	response.setHeader("Refresh",0 +";URL=displayData");//會員中心
        }else {
        	response.setHeader("Refresh",0 +";URL=falseNotice.jsp");
        }
        
     
//		response.setHeader("Refresh",0 +";URL=InsurePage.jsp");
	}

}
