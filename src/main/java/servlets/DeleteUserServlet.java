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

@WebServlet(name = "DeleteUserServlet", urlPatterns = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = UserServiceImpl.getUserService().getUserById(Long.parseLong(request.getParameter("id")));
        request.setAttribute("userForDelete", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/deleteUser.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl.getUserService().deleteUser(Long.parseLong(request.getParameter("idForDelete")));
        response.sendRedirect(request.getContextPath() + "/userList");
    }
}
