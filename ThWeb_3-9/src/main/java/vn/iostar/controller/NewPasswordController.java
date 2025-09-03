package vn.iostar.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import vn.iostar.service.UserService;
import vn.iostar.service.UserServiceImpl;

@WebServlet("/new-password")
public class NewPasswordController extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("new-password.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("resetEmail");

        if (email != null && newPassword != null) {
            // Cập nhật mật khẩu trong cơ sở dữ liệu
            userService.updatePassword(email, newPassword);

            session.removeAttribute("resetEmail");
            response.sendRedirect(request.getContextPath() + "/login?message=reset-success");

        } else {
            // Xử lý lỗi nếu không có session
            response.sendRedirect(request.getContextPath() + "/forgotpass");
        }
    }
}