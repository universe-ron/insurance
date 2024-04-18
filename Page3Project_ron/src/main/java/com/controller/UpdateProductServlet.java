package com.controller;

import com.model.Product;
import com.dao.ProductDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateProduct")
public class UpdateProductServlet extends HttpServlet {
    private ProductDao productDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        // 在这里初始化 ProductDAO 实例
        productDAO = new ProductDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单数据
        int pid = Integer.parseInt(request.getParameter("pid"));
        String pname = request.getParameter("pname");
        String generations = request.getParameter("generations");
        String type = request.getParameter("type");
        int price = Integer.parseInt(request.getParameter("price"));

        // 创建 Product 对象
        Product product = new Product();
        product.setPid(pid);
        product.setPname(pname);
        product.setGenerations(generations);
        product.setType(type);
        product.setPrice(price);

        // 更新产品信息
        productDAO.updateProduct(product);

        // 重定向到产品列表页面或其他适当页面
        response.sendRedirect(request.getContextPath() + "/products");
    }
}
