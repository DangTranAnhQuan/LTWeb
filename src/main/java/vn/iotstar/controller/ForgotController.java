package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.model.User;

@WebServlet(urlPatterns = "/forgot")
public class ForgotController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final UserDao userDao = new UserDaoImpl();

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
        String confirm  = req.getParameter("confirm");

        if (newPass == null && confirm == null) {
            User u = userDao.getByEmail(email);
            if (u == null) {
                req.setAttribute("alert", "Email không tồn tại trong hệ thống.");
            } else {
                req.setAttribute("emailOk", true);
                req.setAttribute("email", email);
            }
            req.getRequestDispatcher("/view/ForgotPassword.jsp").forward(req, resp);
            return;
        }

        if (email == null || email.isEmpty()) {
            req.setAttribute("alert", "Thiếu email xác nhận.");
            req.getRequestDispatcher("/view/ForgotPassword.jsp").forward(req, resp);
            return;
        }
        if (!newPass.equals(confirm)) {
            req.setAttribute("alert", "Mật khẩu nhập lại không khớp.");
            req.setAttribute("emailOk", true);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/view/ForgotPassword.jsp").forward(req, resp);
            return;
        }

        boolean ok = userDao.updatePasswordByEmail(email, newPass);
        if (ok) {
            req.setAttribute("success", "Đổi mật khẩu thành công. Hãy đăng nhập lại.");
        } else {
            req.setAttribute("alert", "Không thể cập nhật mật khẩu. Thử lại sau.");
        }
        req.getRequestDispatcher("/view/ForgotPassword.jsp").forward(req, resp);
    }
}
