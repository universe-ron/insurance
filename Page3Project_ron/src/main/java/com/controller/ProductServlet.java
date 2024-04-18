package com.controller;

import com.dao.ProductDao;
import com.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        super.init();
        productDao = new ProductDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Add product
        String pname = request.getParameter("pname");
        String generations = request.getParameter("generations");
        String type = request.getParameter("type");
        int price = Integer.parseInt(request.getParameter("price"));

        Product product = new Product();
        product.setPname(pname);
        product.setGenerations(generations);
        product.setType(type);
        product.setPrice(price);

        productDao.addProduct(product);

        response.sendRedirect(request.getContextPath() + "/products");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        if (action != null) {
            switch (action) {
                case "edit":
                	int productId = Integer.parseInt(request.getParameter("id"));
                    // 根据产品 ID 从数据库中检索产品详细信息
                    Product productToEdit = productDao.getProductById(productId);
                    if (productToEdit != null) {
                        // 将产品详细信息设置为请求属性，以便在编辑页面中使用
                        request.setAttribute("product", productToEdit);
                        // 将请求转发到编辑产品页面
                        request.getRequestDispatcher("edit_product.jsp").forward(request, response);
                    } else {
                        // 如果产品不存在，则可能显示错误信息或重定向到错误页面
                        response.sendRedirect(request.getContextPath() + "/error.jsp");
                    }
                    break;
                  
                case "delete":
                    int pid = Integer.parseInt(request.getParameter("id"));
                    productDao.deleteProduct(pid);
                    response.sendRedirect(request.getContextPath() + "/products");
                    break;
                default:
                    // Handle other actions
                    break;
            }
        } else {
            // Display all products
            List<Product> products = productDao.getAllProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("products.jsp").forward(request, response);
        }
    }
}
