package servlet;

import bean.User;
import dao.UserDAO;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

/**
 * Created by wangshouli on 17-5-6.
 */
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        String name = request.getParameter("name");
        String pasword = request.getParameter("password");
        if (new UserDAO().getPasswd(name) == null) {
            System.out.println(new UserDAO().getPasswd(name));
            User user = new User();
            user.setName(name);
            user.setPassword(pasword);
            new UserDAO().add(user);
            response.sendRedirect("login.jsp");
        }else{
            response.sendRedirect("regist.jsp");
        }
    }
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
