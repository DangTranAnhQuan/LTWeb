package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.model.User;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = "/admin/category/delete")
public class CategoryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = parseInt(req.getParameter("id"));
		if (id == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/category/list?err=Thiếu+id");
			return;
		}
		try {
			categoryService.delete(id);
			resp.sendRedirect(req.getContextPath() + "/admin/category/list?msg=Đã+xóa");
		} catch (Exception e) {
			resp.sendRedirect(req.getContextPath() + "/admin/category/list?err=Lỗi+xóa");
		}
	}

	private static Integer parseInt(String s) {
		try {
			return (s == null || s.isBlank()) ? null : Integer.valueOf(s);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
