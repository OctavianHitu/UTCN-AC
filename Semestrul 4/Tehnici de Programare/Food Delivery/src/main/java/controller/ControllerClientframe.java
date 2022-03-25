package controller;




import Businesslayer.DeliveryService;
import model.Account;
import model.Baseproduct;
import model.Order;
import presentation.ClientFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observer;
import java.util.stream.Collectors;


public class ControllerClientframe {

    private ClientFrame clientFrame;
    private DeliveryService delivsrv;
    private Account accounts;
    public List<Baseproduct> baseprodlist= new ArrayList<>();

    public ControllerClientframe(ClientFrame clntfr, DeliveryService dlv,Account acc)
    {
        this.accounts=acc;
        this.clientFrame=clntfr;
        this.delivsrv= dlv;
        clientFrame.listproductslistener(new listproductsactionlistener());
       clientFrame.addlistlistener(new addlistactionlistener());
  clientFrame.addorderlistener(new addorderactionlistener());
    clientFrame.searchsimplelistener(new searchsimpleactionlistener());
    }
    public class listproductsactionlistener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            List<Baseproduct> lists= delivsrv.getproduct();
            StringBuilder finals= new StringBuilder();
            for(Baseproduct bsp : lists)
            {
                finals.append(bsp.toString());
                finals.append("\n\n");
            }
            clientFrame.setallproducts(finals.toString());
        }
    }
    public class addlistactionlistener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name= clientFrame.getTitletf();
            List<Baseproduct> lists= delivsrv.getproduct();
            List<Baseproduct> lislists = new ArrayList<>();
            lislists= lists.stream().filter((p)->p.getname().equals(name)).collect(Collectors.toList());
            lislists.forEach(p->baseprodlist.add(p));
            StringBuilder finals= new StringBuilder();
            for(Baseproduct bsp : baseprodlist)
            {
                finals.append(bsp.toString());
                finals.append("\n\n");
            }
            clientFrame.setallorders(finals.toString());


        }
    }


    public class addorderactionlistener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Account> acc= delivsrv.getaccountlist();
            int size= acc.size();
            int i=0;
            int id=0;
            double price=0;
            while(i<size)
            {
                Account accs= acc.get(i);
                if((accs.getPassword()==accounts.getPassword())&&(accs.getUsername()==accounts.getUsername()))
                {
                    id=i;
                }
                i++;
            }
            for (Baseproduct bsp: baseprodlist){
                price=price+ bsp.getPrice();
            }
            Date loc= new Date();
            Order neword=new Order(delivsrv.getOrderlist().size(),id, loc,price);

            delivsrv.addmaporderlist(neword,baseprodlist);
            delivsrv.addsimpleorder(neword);
            delivsrv.addemployeeobs(neword);
            clientFrame.setallorders("");


        }

    }
    private class searchsimpleactionlistener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Baseproduct> bsplist = delivsrv.getproduct();

            String name = clientFrame.getTitletf();
            double rating;
            if(clientFrame.getRatingtf()!="")
            {
                rating=Double.parseDouble(clientFrame.getRatingtf());
            }
            else
            {
                rating=Double.MIN_VALUE;
            }
            float price;
            if(clientFrame.getRatingtf()!="")
            {
                price=Float.parseFloat(clientFrame.getPricetf());
            }
            else
            {
                price=Float.MIN_VALUE;
            }
            double protein;
            if(clientFrame.getProteintf()!="")
            {
                protein=Double.parseDouble(clientFrame.getProteintf());
            }
            else
            {
                protein=Double.MIN_VALUE;
            }
            double fat;
            if(clientFrame.getFattf()!="")
            {
                fat=Double.parseDouble(clientFrame.getFattf());
            }
            else
            {
                fat=Double.MIN_VALUE;
            }
            double cals;
            if(clientFrame.getCaloriestf()!="")
            {
                cals=Double.parseDouble(clientFrame.getCaloriestf());
            }
            else
            {
                cals=Double.MIN_VALUE;
            }
            double sodium;
            if(clientFrame.getSodiumtf()!="")
            {
                sodium=Double.parseDouble(clientFrame.getSodiumtf());
            }
            else
            {
                sodium=Double.MIN_VALUE;
            }
            Double db=Double.MIN_VALUE;
            Float ft =Float.MIN_VALUE;
            List<Baseproduct> listbsplist=bsplist.stream().filter(p->(name.equals("")||p.getname().equals(name)))
                                                          .filter(p->(price==ft||p.getPrice()==price))
                    .filter(p->(fat==db||p.getFat()==fat))
                    .filter(p->(cals==db||p.getCalories()==cals))
                    .filter(p->(protein==db||p.getProtein()==protein))
                    .filter(p->(rating==db||p.getRating()==rating))
                    .filter(p->(sodium==db||p.getSodium()==sodium)).collect(Collectors.toList());

            StringBuilder tot= new StringBuilder();
            for(Baseproduct bs : listbsplist){
                tot.append(bs.toString());
                tot.append("\n\n");
            }

            clientFrame.setallproducts(tot.toString());




        }
    }



}
