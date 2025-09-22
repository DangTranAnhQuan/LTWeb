package vn.iotstar.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.entity.User;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin/user/list"})
public class UserListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String kw = req.getParameter("kw");
        List<User> items = (kw == null || kw.isBlank())
                ? service.findAll()
                : service.search(kw.trim());

        req.setAttribute("items", items);
        req.setAttribute("pageTitle", "Quản lý người dùng");
        req.setAttribute("view", "/view/admin/user-list.jsp");
        req.getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(req, resp);
    }
}
