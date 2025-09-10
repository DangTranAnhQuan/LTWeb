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

@WebServlet(urlPatterns = "/admin/category/edit")
public class CategoryEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		Integer id = parseInt(req.getParameter("id"));
		String name = trim(req.getParameter("name"));
		String icon = trim(req.getParameter("icon"));

		if (id == null || isBlank(name)) {
			resp.sendRedirect(req.getContextPath() + "/admin/category/list?err=Thiếu+tham+số");
			return;
		}

		try {
			Category c = categoryService.findById(id);
			if (c == null) {
				resp.sendRedirect(req.getContextPath() + "/admin/category/list?err=Không+tồn+tại");
				return;
			}

			c.setCatename(name);
			c.setIcon(icon);
			categoryService.update(c);

			resp.sendRedirect(req.getContextPath() + "/admin/category/list?msg=Đã+cập+nhật");
		} catch (Exception e) {
			resp.sendRedirect(req.getContextPath() + "/admin/category/list?err=Lỗi+lưu");
		}

	}

	private static String trim(String s) {
		return s == null ? null : s.trim();
	}

	private static boolean isBlank(String s) {
		return s == null || s.isBlank();
	}

	private static Integer parseInt(String s) {
		try {
			return (s == null || s.isBlank()) ? null : Integer.valueOf(s);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
