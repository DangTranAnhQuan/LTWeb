package vn.iotstar.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.entity.User;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin/user/add"})
public class UserAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("pageTitle", "Thêm người dùng");
        req.setAttribute("view", "/view/admin/user-form.jsp");
        req.getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        User u = new User();
        u.setEmail(req.getParameter("email"));
        u.setUsername(req.getParameter("username"));
        u.setFullName(req.getParameter("fullName"));
        u.setPassword(req.getParameter("password"));
        u.setAvatar(req.getParameter("avatar"));


        int role = 3;
        String roleParam = req.getParameter("roleId");
        if (roleParam != null && !roleParam.isBlank()) {
            try { role = Integer.parseInt(roleParam.trim()); } catch (NumberFormatException ignore) {}
        }
        if (role < 1 || role > 3) role = 3;
        u.setRoleId(role);

        u.setPhone(req.getParameter("phone"));
        u.setCreatedDate(LocalDateTime.now());

        service.save(u);
        resp.sendRedirect(req.getContextPath() + "/admin/user/list?msg=Thêm+thành+công");
    }
}
