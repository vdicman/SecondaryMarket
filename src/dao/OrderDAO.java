package dao;

import bean.Book;
import bean.Order;
import bean.OrderItem;
import bean.User;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import javax.jws.soap.SOAPBinding;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.List;
import java.util.Set;

/**
 * Created by wangshouli on 17-5-7.
 */
public class OrderDAO {
    public  void add(Order order){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore?chacterEncoding=UTF-8",
                    "root","1235812358");
            String sql="insert into orders_(ordertime, price, state, user_id) values(?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setDate(1,(Date) order.getOrdertime());
            preparedStatement.setDouble(2,order.getPrice());
            preparedStatement.setBoolean(3,order.isState());
            preparedStatement.setInt(4,order.getUser_id());
            preparedStatement.execute();
            ResultSet resultSet=preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                order.setId(resultSet.getInt(1));
            }

            QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());


            Set<OrderItem> items=order.getOrderItemSet();
            for(OrderItem item : items){
                sql="insert into orderitem_(quantity,price,order_id,book_id) VALUES (?,?,?,?)";
                Object params[]={item.getQuantity(),item.getPrice(),order.getId(),item.getBook().getId()};
                runner.update(sql,params);
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }

    public Order find(int id){
        try{
            QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
            String sql="select * from order_ where id=?";
            Order order= (Order)runner.query(sql,id,new BeanHandler(Order.class));
            sql="select * from orderitem_ where order_od=?";
            List<OrderItem> items=(List<OrderItem>)runner.query(sql,order.getId(),new BeanListHandler(OrderItem.class));

            for (OrderItem item :items){
                sql="select book.* from orderitem_, book_ where orderitem_.id=? and orderitem_.book_id=book_.id";
                 Book book=(Book)runner.query(sql,item.getId(),new BeanHandler(Book.class));
                 item.setBook(book);
            }
            order.getOrderItemSet ().addAll(items);

            sql="select * from order_, user_ where order_.id=? and oder.user_id=user_.id";
            User user=(User)runner.query(sql,order.getId(),new BeanHandler(User.class));
            order.setUser_id(user.getId());
            return order;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }

    public List<Order> getAll(boolean state){
        try{
            QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
            String sql="select * from order_ where state=?";
            List<Order> orders=(List<Order>)runner.query(sql,state,new BeanListHandler(Order.class));
            User user=null;
            for(Order item : orders){
                sql="select user_.* from user_, order_ where order_.id=? and user_.id=orders_.id";
                user=(User)runner.query(sql,item.getId(),new BeanHandler(User.class));
                item.setUser_id(user.getId());
            }
            return orders;

        }catch (SQLException e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }


    public List<Order> getAll(boolean state,int user_id){
        try {
            QueryRunner runner= new QueryRunner(JdbcUtils.getDataSource());
            String sql="select * from oders_ where state=? and orders_.user_id=? ";
            Object params[]={state,user_id};
            List<Order> orders =(List<Order>)runner.query(sql,params,new BeanListHandler(Order.class));
            User user=null;
            for(Order item : orders){
                sql="select * from user_ where user_.id=?";
                user=(User)runner.query(sql,user_id,new BeanHandler(User.class));
                item.setUser_id(user.getId());
            }
            return orders;
        }catch (SQLException e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }

    public List<Order> getAll(int user_id){
        try {
            QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
            String sql="select * from order_ where user_id=?";
            List<Order> orders=(List<Order>)runner.query(sql,user_id,new BeanListHandler(Order.class));
            for(Order item :orders){
                sql="select * from user_ where id=?";
                User user=(User)runner.query(sql,user_id,new BeanHandler(User.class));
                item.setUser_id(user.getId());
            }
            return  orders;
        }catch (SQLException e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }

    public  void  update(Order order){
        try {
            QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
            String sql="update orders_ set state=? where id=?";
            Object param[]={order.isState(),order.getId()};
            runner.update(sql,param);

        }catch (SQLException e){
            e.printStackTrace();
            throw  new  RuntimeException(e);
        }
    }


}
