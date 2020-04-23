package servlets;

import model.User;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String login = request.getParameter("login");
        final String password = request.getParameter("pass");

        final User user = UserServiceImpl.getUserService().getUser(login);

        final HttpSession session = request.getSession();

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("role", user.getRole());

            if (user.getRole().equalsIgnoreCase("admin")) {
                response.sendRedirect(request.getContextPath() + "/admin/userList");
            } else {
                response.sendRedirect(request.getContextPath() + "/userMenu");
            }

        } else {
            doGet(request, response);
        }
    }
}
