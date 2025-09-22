package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;
import vn.iotstar.services.VideoService;
import vn.iotstar.services.impl.VideoServiceImpl;

@WebServlet(urlPatterns = {"/admin/video/add"})
public class VideoAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final VideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("pageTitle", "Thêm video");
        req.setAttribute("view", "/view/admin/video-form.jsp");
        req.getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Video v = new Video();
        v.setTitle(req.getParameter("title"));
        v.setHref(req.getParameter("href"));
        v.setPoster(req.getParameter("poster"));
        v.setDescription(req.getParameter("description"));
        v.setActive("on".equals(req.getParameter("active")));

        String cateId = req.getParameter("cateid");
        if (cateId != null && !cateId.isBlank()) {
            try {
                Category c = new Category();
                c.setId(Integer.valueOf(cateId.trim()));
                v.setCategory(c);
            } catch (NumberFormatException ignore) {  }
        }

        videoService.save(v);
        resp.sendRedirect(req.getContextPath() + "/admin/video/list?msg=Thêm+thành+công");
    }
}
