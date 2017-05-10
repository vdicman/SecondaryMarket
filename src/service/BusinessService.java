package service;

import bean.Book;
import bean.User;
import dao.BookDAO;
import dao.UserDAO;
import utils.DaoFactory;

import java.util.List;

/**
 * Created by wangshouli on 17-5-7.
 */
public class BusinessService {
    private BookDAO bookDAO= DaoFactory.getInstance().createDao("dao.BookDao",BookDAO.class);
    private UserDAO userDAO=DaoFactory.getInstance().createDao("dao.UserDao", UserDAO.class);

    public void addBook(Book book){
        bookDAO.add(book);
    }
    public List<Book> listBook(){
        return bookDAO.getAll();
    }
}
