package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.model.User;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;

import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    private static final String SESSION_ACCOUNT  = "account";
    private static final String SESSION_USERNAME = "username";
    private static final String COOKIE_REMEMBER  = "username";
    private static final String VIEW_LOGIN       = "/views/login.jsp"; 
    private static final String AFTER_LOGIN      = "/waiting";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute(SESSION_ACCOUNT) != null) {
            resp.sendRedirect(req.getContextPath() + AFTER_LOGIN);
            return;
        }

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (COOKIE_REMEMBER.equals(c.getName())) {
                    session = req.getSession(true);
                    session.setAttribute(SESSION_USERNAME, c.getValue());
                    resp.sendRedirect(req.getContextPath() + AFTER_LOGIN);
                    return;
                }
            }
        }

        req.getRequestDispatcher(VIEW_LOGIN).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username  = req.getParameter("username");
        String password  = req.getParameter("password");
        String remember  = req.getParameter("remember");
        boolean isRememberMe = "on".equals(remember);

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
            req.getRequestDispatcher(VIEW_LOGIN).forward(req, resp);
            return;
        }

        UserService service = new UserServiceImpl();
        User user = service.login(username, password);

        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);
            session.setAttribute("username", user.getUserName());
            if (isRememberMe) saveRememberMe(resp, user.getUserName());

            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        } else {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        }
    }

    private void saveRememberMe(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie(COOKIE_REMEMBER, username);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
