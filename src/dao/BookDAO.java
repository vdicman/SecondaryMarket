package dao;

import bean.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JdbcUtils;

/**
 * Created by wangshouli on 17-5-6.
 */
public class BookDAO {
    public BookDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        }

    public Connection getConnection()throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookstore?characterConding=UTF-8",
                "root","1235812358");
    }
    public Book find(int id){

        try {
            QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
            String sql="select * from book_ where id=?";
            return (Book)runner.query(sql,id,new BeanHandler(Book.class));

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
//        Book book=null;
//        try(Connection connection=getConnection(); Statement statement=connection.createStatement()){
//            String sql="SELECT * FROM `book_` WHERE `id`"+id;
//            ResultSet resultSet= statement.executeQuery(sql);
//            if (resultSet.next()){
//                book.setId(resultSet.getInt(1));
//                book.setName(resultSet.getString(2));
//                book.setPrice(resultSet.getFloat(3));
//            }
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return book;
    }

    public void add(Book book){
        try{
            QueryRunner queryRunner=new QueryRunner(JdbcUtils.getDataSource());
            String sql="insert into book_(name,price) values(?,?)";
            Object params[]={book.getName(),book.getPrice()};
            queryRunner.update(sql,params);
        }catch (SQLException e){
            e.printStackTrace();
        }



//        String sql="INSERT INTO `book_` VALUE(null,?,?)";
//        try (Connection connection=getConnection();PreparedStatement preparedStatement=connection.prepareStatement(sql)){
//            preparedStatement.setString(1,book.getName());
//            preparedStatement.setFloat(2,book.getPrice());
//            preparedStatement.execute();
//            ResultSet resultSet=preparedStatement.getGeneratedKeys();
//            if(resultSet.next()){
//                book.setId(resultSet.getInt(1));
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//    public void update(Book book){
//        String sql="UPDATE `book_` SET name=?, price=? WHERE id="+book.getId();
//        try (Connection connection=getConnection();PreparedStatement preparedStatement=connection.prepareStatement(sql)){
//            preparedStatement.setString(1,book.getName());
//            preparedStatement.setFloat(2,book.getPrice());
//            preparedStatement.execute();
//            ResultSet resultSet=preparedStatement.getGeneratedKeys();
//            if(resultSet.next()){
//                book.setId(resultSet.getInt(1));
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
    }

    public int getTotalRecord(){
        try{
        QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
        String sql="select count(*) from book_";
        long sum =(Long) runner.query(sql,new ScalarHandler());
        return (int) sum;
     }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public List<Book> getPageData(int startIndex,int pageSize,int category_id){
        try{
            QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
            String sql="select * from book_ where category_id=? limit ?,?";
            Object params[]={category_id,startIndex,pageSize};
            return (List<Book>)runner.query(sql,params, new BeanListHandler(Book.class));
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int getTotalRecord(int category_id){
        try{
            QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
            String sql="select count(*) from book_ where category_id=?";
            return (int)runner.query(sql,category_id,new ScalarHandler());
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }




    public List<Book> getPageData(int start,int countNum){


        List<Book> books=new ArrayList<Book>();


        String sql="SELECT * FROM `book_` ORDER BY id DESC LIMIT ?,?";
        try(Connection connection=getConnection();PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setInt(1,start);
            preparedStatement.setInt(2,countNum);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Book book=new Book();
                book.setId(resultSet.getInt(1));
                book.setName(resultSet.getString(2));
                book.setPrice(resultSet.getFloat(3));
                books.add(book);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return books;
    }

    public List<Book> getAll(){
        return getPageData(0,Short.MAX_VALUE);
    }
}
