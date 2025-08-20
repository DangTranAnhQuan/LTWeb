package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if ("Quan".equals(username) && "123".equals(password)) {
        
            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(60 * 60 * 24); 
            response.addCookie(userCookie);

            out.println("<h2>Đăng nhập thành công, " + username + "!</h2>");
            out.println("<p><a href='Welcome'>Đi tới trang chào mừng</a></p>");

        } else {
            out.println("<h2>Đăng nhập thất bại.</h2>");
            out.println("<p>Tên đăng nhập hoặc mật khẩu không đúng.</p>");
        }
    }
}