package vn.iotstar.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import vn.iotstar.model.Category;
import vn.iotstar.model.User;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/admin/category/add" })
public class CategoryAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/add-category.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("account");
		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		String name = req.getParameter("name");
		String icon = req.getParameter("icon"); 

		if (name == null || name.isBlank()) {
			req.setAttribute("alert", "Tên danh mục không được để trống");
			RequestDispatcher rd = req.getRequestDispatcher("/views/admin/add-category.jsp");
			rd.forward(req, resp);
			return;
		}

		Category c = new Category();
		c.setCatename(name.trim());
		c.setIcon(icon != null ? icon.trim() : null);
		c.setUserId(user.getId());

		cateService.insert(c);
		resp.sendRedirect(req.getContextPath() + "/admin/category/list");
	}
}
