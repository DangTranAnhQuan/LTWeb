package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.util.UUID;

import vn.iotstar.model.User;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/profile")
@MultipartConfig(maxFileSize = 2 * 1024 * 1024) 
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

   
        HttpSession session = req.getSession(false);
        User u = (session != null) ? (User) session.getAttribute("account") : null;
        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/login?returnUrl=" + req.getRequestURI());
            return;
        }

        req.setAttribute("pageTitle", "Hồ sơ cá nhân");
        req.setAttribute("view", "/view/profile.jsp");
        req.getRequestDispatcher("/WEB-INF/layout/web.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        User u = (session != null) ? (User) session.getAttribute("account") : null;
        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String fullName = trim(req.getParameter("fullName"));
        String phone    = trim(req.getParameter("phone"));
        Part   avatar   = null;
        try { avatar = req.getPart("avatar"); } catch (Exception ignore) {}

      
        if (fullName == null || fullName.isBlank()) {
            req.setAttribute("err", "Họ tên không được rỗng");
            doGet(req, resp);
            return;
        }

        String imagePath = u.getAvatar(); 
        if (avatar != null && avatar.getSize() > 0) {
            String ct = avatar.getContentType();
            if (ct == null || !(ct.startsWith("image/jpeg") || ct.startsWith("image/png"))) {
                req.setAttribute("err", "Chỉ chấp nhận JPG/PNG");
                doGet(req, resp);
                return;
            }

            String uploadRoot = req.getServletContext().getRealPath("/uploads/screenshots");
            Files.createDirectories(Paths.get(uploadRoot));

            String ext = ct.contains("png") ? ".png" : ".jpg";
            String fileName = UUID.randomUUID().toString() + ext;
            try (InputStream is = avatar.getInputStream()) {
                Files.copy(is, Paths.get(uploadRoot, fileName), StandardCopyOption.REPLACE_EXISTING);
            }

            imagePath = "/uploads/screenshots/" + fileName; 
        }

        u.setFullName(fullName);
        u.setPhone(phone);
        u.setAvatar(imagePath);
        userService.update(u);      
        
        session.setAttribute("account", u);
        req.setAttribute("msg", "Cập nhật thành công");

        doGet(req, resp);
    }

    private static String trim(String s){ return s==null? null : s.trim(); }
}
