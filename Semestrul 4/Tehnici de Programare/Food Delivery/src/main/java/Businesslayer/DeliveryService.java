package Businesslayer;

import datalayer.Serialization;
import model.Account;
import model.Baseproduct;
import model.Order;
import datalayer.ReadFromFile;
import presentation.EmployeeFrame;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
public  class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {

    private List<Account> accountList;
    private List<Order> orderlist;
    private List<Baseproduct> baseproductList;
    private Serialization serial;
    private  Observer observ;
   private Map<Order,List<Baseproduct>> maporderlist;

    public DeliveryService()
    {
        orderlist= new ArrayList<>();
        accountList= new ArrayList<>();
        maporderlist = new HashMap<>();
        serial = new Serialization();
        baseproductList = new ArrayList<>();
        observ= new EmployeeFrame();
        try {
            baseproductList=(ArrayList<Baseproduct>)serial.deserializate("products.txt");
            accountList=(List<Account>)serial.deserializate("accounts.txt");
            orderlist=(List<Order>)serial.deserializate("orders.txt");
            maporderlist=(Map<Order, List<Baseproduct>>)serial.deserializate("maporderlist.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Override
    public void readproduct()  {
        ReadFromFile file= new ReadFromFile();
        HashSet<Baseproduct> product= null;
        try {
            product = file.readfile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Baseproduct> bpro = new ArrayList<>();
        for(Baseproduct baseproduct : product)
        {
            bpro.add(baseproduct);
        }
        bpro.sort(new Comparator<Baseproduct>() {
            @Override
            public int compare(Baseproduct o1, Baseproduct o2) {
                return o1.getname().compareTo(o2.getname());
            }
        });
        this.baseproductList=bpro;
        try {
            serial.serializate("products.txt",bpro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Baseproduct> getproduct() {
        return baseproductList;
    }

    @Override
    public void addaccount(Account acc)  {
        assert acc!= null;
        accountList.add(acc);
        try {
            serial.serializate("accounts.txt",accountList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert formedwell();
    }
    @Override
    public boolean searchaccount(Account acc)
    {
        assert acc!=null && accountList !=null;
        boolean exist=true;
        String username =acc.getUsername();
        String password = acc.getPassword();
        long aux=accountList.stream().filter(e->e.getUsername().equals(username)).filter(e->e.getPassword().equals(password)).count();
        if(aux==0)
        {
            exist=false;
        }
        return exist;


    }
    @Override
    public void addbaseproduct(Baseproduct bp)  {
        assert bp !=null;
        this.baseproductList.add(bp);
        try {
            serial.serializate("products.txt",baseproductList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert formedwell();
    }

    @Override
    public void setproduct(List<Baseproduct> p) {
        assert p!=null;
        this.baseproductList=p;
        try {
            serial.serializate("products.txt",baseproductList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert formedwell();

    }


    public Map<Order, List<Baseproduct>> getMaporderlist() {
        return maporderlist;
    }

    @Override
    public void deletebaseproduct(Baseproduct bp)  {
        assert bp !=null;
        this.baseproductList.remove(bp);
        try {
            serial.serializate("products.txt",baseproductList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert formedwell();
    }
    @Override
    public void addsimpleorder(Order ord)
    {   assert  ord!=null;
        orderlist.add(ord);
        try {
            serial.serializate("orders.txt",orderlist);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert formedwell();
    }
    @Override
    public void addmaporderlist(Order o, List<Baseproduct> m){
        assert o!=null;
        assert m!=null;
        maporderlist.put(o,m);
        try {
            serial.serializate("maporderlist.txt",maporderlist);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert formedwell();

    }

    @Override
    public boolean formedwell() {
        if(orderlist==null|| maporderlist==null||accountList==null || baseproductList==null)
        {
            return false;
        }
        return true;
    }

    public void addemployeeobs(Order ord)
    {
        observ.update(this,ord);
    }
    public List<Order> getOrderlist()
    {
        return orderlist;
    }
    public List<Account> getaccountlist()
    {
        return accountList;
    }

}
