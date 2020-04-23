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
import java.util.List;

@WebServlet(name = "UserListServlet", urlPatterns = "/admin/userList")
public class UserListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<User> allUsers = UserServiceImpl.getUserService().getAllUsers();
        request.setAttribute("allUsers", allUsers);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/showUsers.jsp");
        requestDispatcher.forward(request, response);
    }

}

