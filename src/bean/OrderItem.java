package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangshouli on 17-5-7.
 */
public class OrderItem {
    private int id;
    private Book book;
    private int quantity;
    private double price;




    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {

        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
