package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.entity.User;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin/user/edit"})
public class UserEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Integer id = Integer.valueOf(req.getParameter("id"));
            req.setAttribute("item", service.findById(id));
            req.setAttribute("pageTitle", "Sửa người dùng");
            req.setAttribute("view", "/view/admin/user-form.jsp");
            req.getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/user/list?msg=ID+không+hợp+lệ");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            Integer id = Integer.valueOf(req.getParameter("id"));
            User u = service.findById(id);

            u.setEmail(req.getParameter("email"));
            u.setUsername(req.getParameter("username"));
            u.setFullName(req.getParameter("fullName"));
            u.setPassword(req.getParameter("password"));
            u.setAvatar(req.getParameter("avatar"));


            int role = (u.getRoleId() == null ? 3 : u.getRoleId());
            String roleParam = req.getParameter("roleId");
            if (roleParam != null && !roleParam.isBlank()) {
                try { role = Integer.parseInt(roleParam.trim()); } catch (NumberFormatException ignore) {}
            }
            if (role < 1 || role > 3) role = 3;
            u.setRoleId(role);

            u.setPhone(req.getParameter("phone"));

            service.save(u);
            resp.sendRedirect(req.getContextPath() + "/admin/user/list?msg=Cập+nhật+thành+công");
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/user/list?msg=ID+không+hợp+lệ");
        }
    }
}
