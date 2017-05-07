package servlet;

import bean.Book;
import com.sun.org.apache.bcel.internal.generic.NEW;
import dao.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wangshouli on 17-5-6.
 */
@WebServlet(name = "AddServlet")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        System.out.println(request.getParameter("price"));
        float  price=Float.parseFloat(request.getParameter("price"));
        Book book=new Book();
        book.setName(name);
        book.setPrice(price);
        new BookDAO().add(book);
        response.sendRedirect("management.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request,response);
    }
}
