package vn.iotstar.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.model.User;

@WebFilter(urlPatterns = { "/admin/*" })
public class AuthFilter extends HttpFilter implements Filter {
    private static final long serialVersionUID = 1L;

    private static final Set<String> ALLOW_LIST = Set.of(
        "/admin/login",          
        "/admin/auth",        
        "/admin/assets/",     
        "/admin/uploads/"         
    );

    private boolean isAllowed(HttpServletRequest req) {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        return ALLOW_LIST.stream().anyMatch(allow ->
            path.equals(allow) || (allow.endsWith("/") && path.startsWith(allow)));
    }

    @Override
    public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req  = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (isAllowed(req)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        User account = (session != null) ? (User) session.getAttribute("account") : null;

        if (account == null) {
            String returnUrl = URLEncoder.encode(req.getRequestURI()
                                   + (req.getQueryString() != null ? "?" + req.getQueryString() : ""),
                                   StandardCharsets.UTF_8);
            resp.sendRedirect(req.getContextPath() + "/login?returnUrl=" + returnUrl);
            return;
        }

        Integer role = account.getRoleid();
        if (role == null || role.intValue() != 1) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập.");
            return;
        }

        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0);

        chain.doFilter(request, response);
    }

    @Override public void init(FilterConfig fConfig) throws ServletException {}
    @Override public void destroy() {}
}
