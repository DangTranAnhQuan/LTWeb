package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/forgot"})
public class ForgotController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/view/ForgotPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String newPass = req.getParameter("password");

        boolean ok = false;
        if (email != null && !email.isBlank() && newPass != null && !newPass.isBlank()) {
            ok = userService.updatePasswordByEmail(email.trim(), newPass.trim());
        }

        if (ok) {
            req.setAttribute("alert", "Đổi mật khẩu thành công! Hãy đăng nhập lại.");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("alert", "Không tìm thấy email hoặc dữ liệu không hợp lệ.");
            req.getRequestDispatcher("/view/ForgotPassword.jsp").forward(req, resp);
        }
    }
}
