package utils;

/**
 * Created by wangshouli on 17-5-7.
 */
public class DaoFactory {
    private static final DaoFactory factory=new DaoFactory();
    private DaoFactory(){}
    public static DaoFactory getInstance(){
        return factory;
    }
    public <T> T createDao(String classNmae,Class<T> clazz){
        try {
            T t=(T) Class.forName(classNmae).newInstance();
            return t;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
