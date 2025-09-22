package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.services.VideoService;
import vn.iotstar.services.impl.VideoServiceImpl;

@WebServlet(urlPatterns = {"/admin/video/delete"})
public class VideoDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final VideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idParam = req.getParameter("id");
        try {
            Integer id = Integer.valueOf(idParam);
            videoService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/video/list?msg=Đã+xóa");
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/video/list?msg=ID+không+hợp+lệ");
        }
    }
}
