package servlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final HttpSession session = req.getSession();

        String role = (String) session.getAttribute("role");

        if (role != null) {
            chain.doFilter(req, response);
        } else {
            req.getRequestDispatcher("/login").forward(req, res);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }


    public void destroy() {
    }
}


