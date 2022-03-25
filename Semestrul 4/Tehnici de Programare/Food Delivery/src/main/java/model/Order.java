package model;

import java.io.Serializable;
import java.time.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {

    private int orderid;
    private int clientid;
    private Date date;
    private double price;


    public Order(int orderid, int clientid, Date date, double price)
    {
        this.orderid = orderid;
        this.price=price;
        this.clientid = clientid;
        this.date = date;
    }



    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public Date getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderid == order.orderid && clientid == order.clientid && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderid, clientid, date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderid=" + orderid +
                ", clientid=" + clientid +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
