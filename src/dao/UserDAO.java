package dao;

import bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.ArrayList;
/**
 * Created by wangshouli on 17-5-6.
 */
public class UserDAO {
    public UserDAO() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookstore?characterEncoding=UTF-8",
                "root","1235812358");
    }

    public User find(int id) {
        User user=null;
        try(Connection connection=getConnection(); Statement statement=connection.createStatement()){
            String sql="SELECT * FROM `user_` WHERE id="+id;
            ResultSet resultSet=statement.executeQuery(sql);
            if(resultSet.next()){
                user=new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public void add(User user) {
        String sql="INSERT INTO `user_` value(null,?,?)";
        try (Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.execute();
            ResultSet resultSet=preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                user.setId(resultSet.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(User user){
        String sql="UPDATE `user_` SET`name`=?,`password`=? WHERE `id`="+user.getId();
        try (Connection connection=getConnection();PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.execute();
            ResultSet resultSet=preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                user.setId(resultSet.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void delete(int id){
        String sql="DELETE FROM `user_` WHERE `id`="+id;
        try (Connection connection=getConnection();Statement statement=connection.createStatement()){
            statement.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public String getPasswd(String name){
        String passwd=null;
        String sql="SELECT `password` FROM `user_` WHERE name=?";
        try (Connection connection=getConnection();PreparedStatement preparedStatement =connection.prepareStatement(sql)){
            preparedStatement.setString(1,name);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                passwd=resultSet.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  passwd;
    }
}
