package bean;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by wangshouli on 17-5-7.
 */
public class Cart {
    private Map<Integer,Cartitem> map=new HashMap<Integer,Cartitem>();
    private double price;

    public void add(Book book){
        Cartitem item=map.get(book.getId());
        if(item == null){
            item=new Cartitem();
            item.setBook(book);
            item.setQuantity(1);
            map.put(book.getId(),item);
        }
        item.setQuantity(item.getQuantity()+1);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Map<Integer, Cartitem> getMap() {

        return map;
    }

    public void setMap(Map<Integer, Cartitem> map) {
        this.map = map;
    }
}
