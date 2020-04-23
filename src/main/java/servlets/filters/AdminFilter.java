package servlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter",
        urlPatterns = "/admin/*")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String role = (String) req.getSession().getAttribute("role");

        if(role.equalsIgnoreCase("admin")) {
            chain.doFilter(req, res);
        } else {
            response.getWriter().println("Access denied");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
