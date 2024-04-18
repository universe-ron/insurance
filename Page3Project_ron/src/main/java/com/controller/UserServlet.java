package com.controller;

import java.io.IOException;
import java.util.List;

import com.dao.UserDao;
import com.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
            case "edit":
                String userIdStringUP = request.getParameter("id1");
                System.out.println(userIdStringUP);
                if (userIdStringUP != null && !userIdStringUP.isEmpty()) {
                    int uid = Integer.parseInt(userIdStringUP);
                    
                    System.out.println(uid);
                    // 根据用户 ID 从数据库中检索用户详细信息
                    User userToEdit = userDao.getUserById(uid);
                    System.out.println(userToEdit);
                    if (userToEdit != null) {
                        // 将用户详细信息设置为请求属性，以便在编辑页面中使用
                        request.setAttribute("user", userToEdit);
                        // 将请求转发到编辑用户页面
                        request.getRequestDispatcher("edit_user.jsp").forward(request, response);
                    } else {
                        // 如果用户不存在，则可能显示错误信息或重定向到错误页面
                        response.sendRedirect(request.getContextPath() + "/error.jsp");
                    }
                } else {
                    // Handle null or empty id parameter
                    response.sendRedirect(request.getContextPath() + "/error.jsp");
                }
                break;
                case "delete":
                    // 获取要删除用户的ID
                    String userIdStringDEL = request.getParameter("id2");
                    if (userIdStringDEL != null && !userIdStringDEL.isEmpty()) {
                        int userIdToDelete = Integer.parseInt(userIdStringDEL);
                        userDao.deleteUser(userIdToDelete);
                    }
                    response.sendRedirect(request.getContextPath() + "/users");
                    break;
                default:
                    // Handle other actions
                    break;
            }
        } else {
            // 如果没有action参数，则显示所有用户列表
            List<User> users = userDao.getAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("users.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process the form data
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String mail = request.getParameter("mail");
        String name = request.getParameter("name");
        // You should parse the date from the request parameter and convert it to Date object
        // Date birthDate = parseDate(request.getParameter("birthDate"));
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String phone2 = request.getParameter("phone2");
        int permission = 0; // Set a default value if permission is not provided

        if (request.getParameter("permission") != null && !request.getParameter("permission").isEmpty()) {
            permission = Integer.parseInt(request.getParameter("permission"));
        }

        // Create a new user object
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setMail(mail);
        user.setName(name);
        // user.setBirthDate(birthDate);
        user.setGender(gender);
        user.setAddress(address);
        user.setPhone(phone);
        user.setPhone2(phone2);
        user.setPermission(permission);

        // Add the user to the database
        userDao.addUser(user);

        // Redirect to the users page
        response.sendRedirect(request.getContextPath() + "/users");
    }
}
