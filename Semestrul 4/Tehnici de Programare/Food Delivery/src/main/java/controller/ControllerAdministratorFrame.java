package controller;

import Businesslayer.DeliveryService;
import datalayer.ReadFromFile;
import model.Baseproduct;
import model.CompositeProduct;
import model.Order;
import presentation.AdministratorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

public class ControllerAdministratorFrame {

    private AdministratorFrame adminframe = new AdministratorFrame();
    private DeliveryService delisvrv = new DeliveryService();
    private List<Baseproduct> composeproducts = new ArrayList<>();

    public ControllerAdministratorFrame(AdministratorFrame adminframe,DeliveryService delivsrv)
    {
        this.adminframe= adminframe;
        this.delisvrv = delivsrv;
        adminframe.importListener(new importActionlistener());
        adminframe.editbaseprodListener(new editbaseprodactionlistener());
        adminframe.deletebaseprodListener(new deletebaseprodactionlistener());
        adminframe.addbaseprodListenr(new addbaseprodactionlistener());
        adminframe.addlistListener(new addlistactionlistener());
        adminframe.composeListener(new composeactionlistener());
        adminframe.rap1Lisneter(new rap1actionlistener());
        adminframe.rap2Lisneter(new rap2actionlistener());
        adminframe.rap3Lisneter(new rap3actionlistener());
        adminframe.rap4Lisneter(new rap4actionlistener());
        importorders();
    }



    public void importorders() {
        StringBuilder finals = new StringBuilder();
        for (Order orde : delisvrv.getOrderlist()) {
            finals.append(orde.toString());
            finals.append(" \n");
        }
        adminframe.setalldataorders(finals.toString());
    }



    public class importActionlistener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            List<Baseproduct> baselisy = delisvrv.getproduct();
            String finals="";
            for(Baseproduct base:baselisy)
            { finals+=base.toString();
                finals+="\n";
            }
            adminframe.setalldataprods(finals);
        }}

    public class editbaseprodactionlistener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name= adminframe.getTitletf();
            List<Baseproduct> baseprolist = delisvrv.getproduct();
            double rating=Double.parseDouble(adminframe.getRatingtf());
            double calories=Double.parseDouble(adminframe.getCaloriestf());
            double protein=Double.parseDouble(adminframe.getProteintf());
            double fat=Double.parseDouble(adminframe.getFattf());
            double sodium=Double.parseDouble(adminframe.getSodiumtf());
            float price=Float.parseFloat(adminframe.getPricetf());
            boolean finded = false;
            String finals="";
            while(finded==false) {
                for (Baseproduct bspro : baseprolist) {
                    if (bspro.getname().equals(name)) {
                        bspro.setPrice(price);
                        bspro.setSodium(sodium);
                        bspro.setFat(fat);
                        bspro.setCalories(calories);
                        bspro.setRating(rating);
                        bspro.setProtein(protein);
                        finded = true;
                    } } }
            if(finded==true)
            { for(Baseproduct base:baseprolist)
                { finals+=base.toString();
                    finals+="\n";
                }
                adminframe.setalldataprods(finals);
                delisvrv.setproduct(baseprolist);
            } }}

    public class deletebaseprodactionlistener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        { String name= adminframe.getTitletf();
            List<Baseproduct> baseprolist = delisvrv.getproduct();
            boolean finded= false;
            String finals="";
            while (finded==false)
            { for (Baseproduct bsp : baseprolist)
                { if (bsp.getname().equals(name))
                    { delisvrv.deletebaseproduct(bsp);
                        finded=false;
                    } } }
            if(finded==true)
            {
                for(Baseproduct base:baseprolist)
                { finals+=base.toString();
                    finals+="\n";
                }
                adminframe.setalldataprods(finals);
                delisvrv.setproduct(baseprolist);
            } }}

    public class addbaseprodactionlistener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name= adminframe.getTitletf();
            List<Baseproduct> baseprolist = delisvrv.getproduct();
            double rating=Double.parseDouble(adminframe.getRatingtf());
            double calories=Double.parseDouble(adminframe.getCaloriestf());
            double protein=Double.parseDouble(adminframe.getProteintf());
            double fat=Double.parseDouble(adminframe.getFattf());
            double sodium=Double.parseDouble(adminframe.getSodiumtf());
            float price=Float.parseFloat(adminframe.getPricetf());
            String finals="";
            for(Baseproduct base:baseprolist)
            {
                finals+=base.toString();
                finals+="\n";

            }
            finals+= new Baseproduct(name,rating,calories,protein,fat,sodium,price);
            delisvrv.addbaseproduct(new Baseproduct(name,rating,calories,protein,fat,sodium,price));
            adminframe.setalldataprods(finals);
        }}
    public class addlistactionlistener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name= adminframe.getTitletf();

            List<Baseproduct> baseprolist = delisvrv.getproduct();
            boolean finded= false;

            while(finded==false)
            {
                for(Baseproduct bsp: baseprolist)
                {
                    if(bsp.getname().equals(name))
                    {
                        composeproducts.add(bsp);

                        finded=true;
                    } }}
            if(finded==true)
            {
                String finals="";
                for(Baseproduct base:composeproducts)
                { finals+=base.toString();
                    finals+="\n";
                }
                adminframe.setalldatacomp(finals);
            } }}
    public class composeactionlistener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            List<Baseproduct> baseprolist = delisvrv.getproduct();
            String composetitle =adminframe.getTitlecomposmenutf();
            CompositeProduct compos = new CompositeProduct(composetitle,composeproducts);
            String finals="";
            for(Baseproduct bsp:baseprolist)
            {
                finals+=bsp.toString();
                finals+="\n";
            }
            Baseproduct basep;
            basep= new Baseproduct(composetitle,compos.ratingcompose(),compos.caloriesaddcomp(),compos.proteinaddcomp(),compos.fataddcomp(),compos.sodiumaddcomp(),compos.priceadd());
            finals+=basep.toString();
            finals+="\n";
            adminframe.setalldataprods(finals);
            delisvrv.addbaseproduct(basep);
        }}
    private class rap1actionlistener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String hour1=adminframe.getStarthourtf();
            String hour2=adminframe.getEndhourtf();
            int starth= Integer.parseInt(hour1);
            int endh=Integer.parseInt(hour2);
            System.out.println(adminframe.getStarthourtf());
            System.out.println(starth);
            List<Order> orders= delisvrv.getOrderlist();
            List<Order> listorder= orders.stream().filter(p->p.getDate().getHours()>starth && p.getDate().getHours()<endh).collect(Collectors.toList());

            File  f= new File("Raport1.txt");
            try {
                f.createNewFile();
                FileWriter w= new FileWriter(f);
                w.write(listorder.toString());
                w.flush();
                w.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } }}
    public class rap2actionlistener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            double    ordermore=Double.parseDouble(adminframe.getProductordermoretf());
            Map<Order, List<Baseproduct>> lists= delisvrv.getMaporderlist();
            Map<String,Long> listmap =lists.entrySet().stream().map(p->p.getValue()).flatMap(Collection::stream).collect(Collectors.groupingBy(Baseproduct::getname,Collectors.counting()));
            AtomicReference<String> finals= new AtomicReference<>("");
            listmap.entrySet().stream().filter(p->p.getValue()>=ordermore).forEach(p->finals.set(finals+p.getKey()+"\n"));
            File f= new File("raport2.txt");
            try {
                f.createNewFile();
                FileWriter w= new FileWriter(f);
                w.write(finals.toString());
                w.flush();
                w.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } }}
    public class rap3actionlistener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            int specificord = Integer.parseInt(adminframe.getSpecificnumbertf());
            double setedamount = Double.parseDouble(adminframe.getAmounttf());
            List<Order> orders = delisvrv.getOrderlist();
            List<Order> listorders;
            listorders =orders.stream().filter(o->o.getPrice()>=setedamount).collect(Collectors.toList());
            Map<Integer,Long> listorders2;
            listorders2=listorders.stream().collect(Collectors.groupingBy(Order::getClientid,Collectors.counting()));
            AtomicReference<String> finals= new AtomicReference<>("");
            listorders2.entrySet().stream().filter(integerLongEntry -> integerLongEntry.getValue()>=specificord).forEach(p->finals.set(finals+"id client"+ p.getKey()+"Number times:"+p.getValue()+"\n"));
            File f= new File("raport3.txt");
            try {
                f.createNewFile();
                FileWriter w= new FileWriter(f);
                w.write(finals.toString());
                w.flush();
                w.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } }}




    public class rap4actionlistener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
        float d= Float.parseFloat(adminframe.getSpecificdatetf());
        Map<String,Long> list =delisvrv.getMaporderlist().entrySet().stream().filter(orderListEntry ->
                orderListEntry.getKey().getDate().getDate()==d).map(p->p.getValue()).flatMap(Collection::stream).collect(Collectors.groupingBy(Baseproduct::getname,Collectors.counting()));
        AtomicReference<String> finals= new AtomicReference<>("");
        list.entrySet().stream().forEach(stringLongEntry -> finals.set(finals+ stringLongEntry.getKey()+"Number times "+stringLongEntry.getValue()+"\n"));
            File f= new File("raport4.txt");
            try {
                f.createNewFile();
                FileWriter w= new FileWriter(f);
                w.write(finals.toString());
                w.flush();
                w.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        }
}

