package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.controller.Page3Dao;

@WebServlet("/SubmitServlet.do")
public class SubmitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
		
		
		
	   }

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = null;
		try {
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    String selectedProduct = request.getParameter("productSelect");
	    String selectedSize = request.getParameter("sizeSelect");
	    String selectedModel = request.getParameter("modelSelect");
	    String selectedCategory = request.getParameter("categorySelect");
	    boolean cheackdata = true;
	    
	    int pid,price;
	    Page3Dao pd = new Page3Dao();
	    pid = pd.findPid(selectedProduct, selectedSize, selectedModel);
		price = pd.findPrice(pid);
	    if("全險".equals(selectedCategory))
	    {
	    	price=(int) (price*0.15);
	    }else {
	    	price=(int) (price*0.1);
	    }
	    
	    
		response.setContentType("text/html;charset=UTF-8");
		
		
		out.write("selectedProduct:" + selectedProduct +"<br/>");
		out.write("selectedSize:" + selectedSize +"<br/>");
		out.write("selectedModel:" + selectedModel +"<br/>");
		out.write("selectedCategory:" + selectedCategory +"<br/>");
		
		request.setAttribute("selectedProduct", selectedProduct);
	    request.setAttribute("selectedSize", selectedSize);
	    request.setAttribute("selectedModel", selectedModel);
	    request.setAttribute("selectedCategory", selectedCategory);
	    request.setAttribute("cheackdata",cheackdata);
	    
	    request.setAttribute("price",price);
	    
	    // 轉發回原本的JSP頁面
	    RequestDispatcher dispatcher = request.getRequestDispatcher("InsurePage.jsp");
	    dispatcher.forward(request, response);
		
	}
}
