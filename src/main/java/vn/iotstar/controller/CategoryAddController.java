package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.model.Category;
import vn.iotstar.model.User;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = "/admin/category/add")
public class CategoryAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("pageTitle", "Thêm danh mục");
		req.setAttribute("currentMenu", "cat-add");
		req.setAttribute("view", "/view/admin/category-form.jsp");
		req.getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String name = trim(req.getParameter("name"));
		String icon = trim(req.getParameter("icon"));

		if (isBlank(name)) {
			req.setAttribute("error", "Tên danh mục không được rỗng");
			doGet(req, resp);
			return;
		}

		Category c = new Category();
		c.setCatename(name);
		c.setIcon(icon);
		try {
			categoryService.insert(c);
			resp.sendRedirect(req.getContextPath() + "/admin/category/list?msg=Đã+thêm");
		} catch (Exception e) {
			req.setAttribute("error", "Lỗi lưu: " + e.getMessage());
			doGet(req, resp);
		}
	}

	private static String trim(String s) {
		return s == null ? null : s.trim();
	}

	private static boolean isBlank(String s) {
		return s == null || s.isBlank();
	}
}
