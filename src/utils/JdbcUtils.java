package utils;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Connection;
import  com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Created by wangshouli on 17-5-7.
 */
public class JdbcUtils {
    private static DataSource dataSource=null;
    static {
        dataSource=new ComboPooledDataSource();
    }
    public static DataSource getDataSource(){
        return dataSource;
    }
    public static Connection getConnextion() throws SQLException{
        return dataSource.getConnection();
    }
}
