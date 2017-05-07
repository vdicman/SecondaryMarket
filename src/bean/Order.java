package bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangshouli on 17-5-6.
 */
public class Order {
    private  int id;
    private  int user_id;
    private  int number;
    private  Date ordertime;
    private  double price;
    private  boolean state;
    private Set<OrderItem> orderItemSet=new HashSet<OrderItem>();


    public Set<OrderItem> getOrderItemSet() {
        return orderItemSet;
    }

    public void setOrderItemSet(Set<OrderItem> orderItemSet) {
        this.orderItemSet = orderItemSet;
    }


    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getOrdertime() {

        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getUser_id() {

        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
