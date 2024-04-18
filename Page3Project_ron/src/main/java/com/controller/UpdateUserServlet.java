package com.controller;

import com.dao.UserDao;
import com.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve user data from the request
        int uid;
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String mail = request.getParameter("mail");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String phone2 = request.getParameter("phone2");
        int permission;

        try {
            uid = Integer.parseInt(request.getParameter("uid")); // Corrected parameter name
            permission = Integer.parseInt(request.getParameter("permission"));
        } catch (NumberFormatException e) {
            // Handle the case where uid or permission is not a valid integer
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid uid or permission value");
            return;
        }

        // Validate required fields
        if (account == null || password == null || mail == null || name == null || gender == null || address == null
                || phone == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required fields");
            return;
        }

        // Convert birth date String to Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate;
        try {
            birthDate = dateFormat.parse(request.getParameter("birthDate"));
        } catch (ParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid birthDate format");
            return;
        }

        // Create a new User object
        User user = new User(uid, account, password, mail, name, birthDate, gender, address, phone, phone2, permission);

        // Update the user in the database
        userDao.updateUser(user);

        // Redirect back to user list page
        response.sendRedirect(request.getContextPath() + "/users");
    }
}
