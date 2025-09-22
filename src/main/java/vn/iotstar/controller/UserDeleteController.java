package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin/user/delete"})
public class UserDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idParam = req.getParameter("id");
        try {
            Integer id = Integer.valueOf(idParam);
            service.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/user/list?msg=Đã+xóa");
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/user/list?msg=ID+không+hợp+lệ");
        }
    }
}
