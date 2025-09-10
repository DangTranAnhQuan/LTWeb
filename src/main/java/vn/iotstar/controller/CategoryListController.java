package vn.iotstar.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.model.Category;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = "/admin/category/list")
public class CategoryListController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final CategoryService categoryService = new CategoryServiceImpl();

  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");

    List<Category> cateList = categoryService.getAll();
    req.setAttribute("cateList", cateList);

    req.setAttribute("pageTitle", "Quản lý Danh mục");
    req.setAttribute("currentMenu", "cat-list");
    req.setAttribute("view", "/view/admin/category-list.jsp");
    req.getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(req, resp);
  }
}

