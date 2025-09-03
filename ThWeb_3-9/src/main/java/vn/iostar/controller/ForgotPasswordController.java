package vn.iostar.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import vn.iostar.service.UserService;
import vn.iostar.service.UserServiceImpl;

/**
 * Servlet implementation class ForgotPasswordController
 */
@SuppressWarnings("serial")
@WebServlet("/forgotpass")
public class ForgotPasswordController extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("forgot-password.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");

        boolean userExists = userService.checkExistEmail(email);

        if (userExists) {
            // Lưu email/username vào session để sử dụng ở trang tiếp theo
            HttpSession session = request.getSession();
            session.setAttribute("resetEmail", email);

            response.sendRedirect(request.getContextPath() + "/new-password");

        } else {
            request.setAttribute("alert", "Email không tồn tại.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("forgot-password.jsp");
            dispatcher.forward(request, response);
        }
    }
}