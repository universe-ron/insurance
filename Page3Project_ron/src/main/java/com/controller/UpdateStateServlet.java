package com.controller;
import java.io.IOException;

import com.dao.UserInsuranceDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateState")
public class UpdateStateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        // Retrieve the ID and updated state from request parameters
        int id = Integer.parseInt(request.getParameter("id"));
        int state = Integer.parseInt(request.getParameter("state"));

        // You may perform validation or sanitation of data here
        
        // Perform the update operation using your DAO or service layer
        UserInsuranceDao.updateStateById(id, state);
        
        // Redirect to DisplayDataServlet
        response.sendRedirect(request.getContextPath() +"/opdisplayData");
    }
}
