package dao;

import bean.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.awt.geom.QuadCurve2D;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wangshouli on 17-5-7.
 */
public class CategoryDAO {
    public void add(Category category){
        try{
            QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
            String sql="insert into category_(id, name, description) values(null,?,?)";
            Object params[]={category.getName(),category.getDescription()};
            runner.update(sql,params);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Category find(int id){
        try{
            QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
            String sql="select * from category_ where id=?";
            return  (Category)runner.query(sql,id, new BeanHandler(Category.class));
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Category> findAll(){
        try{
            QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
            String sql="select * from category";
            return  (List<Category>)runner.query(sql, new BeanListHandler(Category.class));
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
