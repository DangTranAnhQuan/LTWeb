package vn.iotstar.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.entity.Video;
import vn.iotstar.services.VideoService;
import vn.iotstar.services.impl.VideoServiceImpl;

@WebServlet(urlPatterns = {"/admin/video/list"})
public class VideoListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final VideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String kw = req.getParameter("kw");
        List<Video> items = (kw == null || kw.isBlank())
                ? videoService.findAll()
                : videoService.search(kw.trim());

        req.setAttribute("items", items);
        req.setAttribute("pageTitle", "Quản lý video");
        req.setAttribute("view", "/view/admin/video-list.jsp");
        req.getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(req, resp);
    }
}
