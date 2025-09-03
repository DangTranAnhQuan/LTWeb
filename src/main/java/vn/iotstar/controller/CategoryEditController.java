package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.model.Category;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/admin/category/edit" })
public class CategoryEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (id != null && !id.isBlank()) {
			Category category = cateService.get(Integer.parseInt(id));
			req.setAttribute("category", category);
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/editcategory.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String idStr = req.getParameter("id");
		String name = req.getParameter("name");
		String icon = req.getParameter("icon");

		Category c = new Category();
		c.setCateid(Integer.parseInt(idStr));
		c.setCatename(name);
		c.setIcon(icon == null || icon.isBlank() ? null : icon);

		cateService.edit(c);
		resp.sendRedirect(req.getContextPath() + "/admin/category/list");
	}
}
