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

@WebServlet(urlPatterns = {"/admin/video/edit"})
public class VideoEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final VideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Integer id = Integer.valueOf(req.getParameter("id"));
            req.setAttribute("item", videoService.findById(id));
            req.setAttribute("pageTitle", "Sửa video");
            req.setAttribute("view", "/view/admin/video-form.jsp");
            req.getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/video/list?msg=ID+không+hợp+lệ");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            Integer id = Integer.valueOf(req.getParameter("id"));
            Video v = videoService.findById(id);

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
                } catch (NumberFormatException ignore) {
                    v.setCategory(null);
                }
            } else {
                v.setCategory(null);
            }

            videoService.save(v);
            resp.sendRedirect(req.getContextPath() + "/admin/video/list?msg=Cập+nhật+thành+công");
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/video/list?msg=ID+không+hợp+lệ");
        }
    }
}
