package servlet;

import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wangshouli on 17-5-6.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String name=request.getParameter("name");
      String password=request.getParameter("password");
      if(password == new UserDAO().getPasswd(name)){
          System.out.println("successsful..............................");
          request.getSession().setAttribute("name",name);
          response.sendRedirect("list");
      }else {
          response.sendRedirect("ligin.jsp");
      }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request,response);
    }
}
