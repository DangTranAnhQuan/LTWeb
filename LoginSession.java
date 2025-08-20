package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; 
import java.io.IOException;
import java.io.PrintWriter; 

/**
 * Servlet implementation class LoginSession
 */

public class LoginSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginSession() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if ("Quan".equals(username) && "123".equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("loggedInUser", username);

			response.sendRedirect("session.html");
		} else {
			response.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out = response.getWriter();
			out.println("<h2>Đăng nhập thất bại.</h2>");
			out.println("<p>Tên đăng nhập hoặc mật khẩu không đúng.</p>");
			out.close();
		}
	}
}