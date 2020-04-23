package servlets;


import model.User;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "UpdateUserServlet", urlPatterns = "/admin/updateUser")
public class UpdateUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = UserServiceImpl.getUserService().getUserById(Long.parseLong(request.getParameter("id")));
        request.setAttribute("userForUpdate", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/updateUser.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl.getUserService().updateUser(
                new User(
                        Long.parseLong(request.getParameter("id")),
                        request.getParameter("login"),
                        request.getParameter("pass"),
                        request.getParameter("name"),
                        "user"));
        response.sendRedirect(request.getContextPath().concat("/admin/userList"));
    }
}

