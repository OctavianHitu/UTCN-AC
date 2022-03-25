package bll;

import java.util.List;
import java.util.NoSuchElementException;

import dao.OrderDao;
import model.Orders;

public class Orderbll {

    private OrderDao order;

    public Orderbll() {
        order = new OrderDao();
    }

    public List<Orders> selectq()
    {
        return order.findAll();
    }

    public Orders findById(int id) {
        Orders ord = order.findById(id);
        if (ord == null) {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return ord;
    }

    public void insert(Orders or) {
        order.insert(or);

    }

    public void  update(Orders or)
    {
        order.update(or);
    }

    public void delete(Orders or)
    {
        order.delete(or);
    }

    public Object[][] gettable2(){ return order.gettable(selectq());}
    public Object[] getheader(){return order.getheaderoftable();}

}

