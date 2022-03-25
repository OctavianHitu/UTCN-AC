package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdministratorFrame extends JFrame {

    JTextField titletf = new JTextField();
    JTextField pricetf = new JTextField();
    JTextField sodiumtf = new JTextField();
    JTextField ratingtf = new JTextField();
    JTextField proteintf = new JTextField();
    JTextField fattf = new JTextField();
    JTextField caloriestf = new JTextField();
    JTextField titlecomposmenutf = new JTextField();
    JTextField starthourtf = new JTextField();
    JTextField endhourtf = new JTextField();
    JTextField productordermoretf=new JTextField();
    JTextField specificnumbertf = new JTextField();
    JTextField amounttf = new JTextField();
    JTextField specificdatetf = new JTextField();

    JButton importbut;
    JButton addbaseprobut ;
    JButton deletebaseprobut ;
    JButton addlistbut ;
    JButton composebut ;
    JButton editbaseprobut ;
    JButton rap1but ;
    JButton rap2but ;
    JButton rap3but;
    JButton rap4but;

    JLabel titlulbl= new JLabel("Title");
    JLabel pricelbl= new JLabel("Price");
    JLabel sodiumlbl= new JLabel("Sodium");
    JLabel ratinglbl= new JLabel("Rating");
    JLabel proteinlbl= new JLabel("Protein");
    JLabel calorieslbl= new JLabel("Calories");
    JLabel fatlbl= new JLabel("Fat");
    JLabel titlucomplbl= new JLabel("Title for Compose Menu");
    JLabel starthlbl= new JLabel("Start h1");
    JLabel endhlbl= new JLabel("End h1");
    JLabel prodordmlbl= new JLabel("More Products2");
    JLabel specificnumberlbl= new JLabel("Number times3");
    JLabel amountlbl= new JLabel("Amount3");
    JLabel specifiddatelbl= new JLabel("Date specified4");

    JTextPane prod = new JTextPane();
    JScrollPane scrollprod = new JScrollPane(prod);

    JTextPane comp = new JTextPane();
    JScrollPane scrollcomp = new JScrollPane(comp);

    JTextPane orders = new JTextPane();
    JScrollPane scrollorders= new JScrollPane(orders);



    public AdministratorFrame(){
        this.setSize(1800,1000);
        this.setVisible(true);
        this.setTitle("ADMINISTRATION");


        JPanel panelsuperior = new JPanel();
        panelsuperior.setLayout(new GridLayout(0,3));
        this.setLayout(new GridLayout(2,0));


        JPanel panelmijloc = new JPanel();
        panelmijloc.setLayout(new GridLayout(8,2));
        panelmijloc.add(titlulbl);
        panelmijloc.add(titletf);
        panelmijloc.add(pricelbl);
        panelmijloc.add(pricetf);
        panelmijloc.add(ratinglbl);
        panelmijloc.add(ratingtf);
        panelmijloc.add(proteinlbl);
        panelmijloc.add(proteintf);
        panelmijloc.add(sodiumlbl);
        panelmijloc.add(sodiumtf);
        panelmijloc.add(calorieslbl);
        panelmijloc.add(caloriestf);
        panelmijloc.add(fatlbl);
        panelmijloc.add(fattf);
        panelmijloc.add(titlucomplbl);
        panelmijloc.add(titlecomposmenutf);

        panelsuperior.add(panelmijloc);
        panelsuperior.add(scrollcomp);
        panelsuperior.add(scrollprod);
        this.add(panelsuperior);

        JPanel panelinferior = new JPanel();
        panelinferior.setLayout(new GridLayout(0,3));

        JPanel panelinfstanga = new JPanel();
        panelinfstanga.setLayout(new GridLayout(5,2));
         importbut= new JButton("Import");
         addbaseprobut = new JButton("Add base prod");
         deletebaseprobut = new JButton("Delete base prod");
         addlistbut = new JButton("Add list");
         composebut = new JButton("Compose");
         editbaseprobut = new JButton("Edit base prod");
         rap1but = new JButton("Raport_1");
         rap2but = new JButton("Raport_2");
         rap3but= new JButton("Raport_3");
         rap4but= new JButton("Raport_4");

        panelinfstanga.add(importbut);
        panelinfstanga.add(deletebaseprobut);
        panelinfstanga.add(editbaseprobut);
        panelinfstanga.add(addbaseprobut);
        panelinfstanga.add(addlistbut);
        panelinfstanga.add(composebut);
        panelinfstanga.add(rap1but);
        panelinfstanga.add(rap2but);
        panelinfstanga.add(rap3but);
        panelinfstanga.add(rap4but);

        JPanel panelinfmijloc = new JPanel();
        panelinfmijloc.setLayout(new GridLayout(6,2));

        panelinfmijloc.add(starthlbl);
        panelinfmijloc.add(starthourtf);
        panelinfmijloc.add(endhlbl);
        panelinfmijloc.add(endhourtf);
        panelinfmijloc.add(specificnumberlbl);
        panelinfmijloc.add(specificnumbertf);
        panelinfmijloc.add(specifiddatelbl);
        panelinfmijloc.add(specificdatetf);
        panelinfmijloc.add(prodordmlbl);
        panelinfmijloc.add(productordermoretf);
        panelinfmijloc.add(amountlbl);
        panelinfmijloc.add(amounttf);

        panelinferior.add(panelinfstanga);
        panelinferior.add(panelinfmijloc);
        panelinferior.add(scrollorders);
        this.add(panelinferior);






    }

    public String getTitletf() {
        return titletf.getText();
    }

    public String getPricetf() {
        return pricetf.getText();
    }

    public String getSodiumtf() {
        return sodiumtf.getText();
    }

    public String getRatingtf() {
        return ratingtf.getText();
    }

    public String getProteintf() {
        return proteintf.getText();
    }

    public String getFattf() {
        return fattf.getText();
    }

    public String getCaloriestf() {
        return caloriestf.getText();
    }

    public String getTitlecomposmenutf() {
        return titlecomposmenutf.getText();
    }

    public String getStarthourtf() {
        return starthourtf.getText();
    }

    public String getEndhourtf() {
        return endhourtf.getText();
    }

    public String getProductordermoretf() {
        return productordermoretf.getText();
    }

    public String getSpecificnumbertf() { return specificnumbertf.getText(); }

    public String getAmounttf() {
        return amounttf.getText();
    }

    public String getSpecificdatetf() {
        return specificdatetf.getText();
    }


    public void importListener(ActionListener importd)
    {
        this.importbut.addActionListener(importd);
    }
    public void editbaseprodListener(ActionListener a)
    {
        this.editbaseprobut.addActionListener(a);
    }
    public void addbaseprodListenr(ActionListener a)
    {
        this.addbaseprobut.addActionListener(a);
    }
    public void deletebaseprodListener(ActionListener a)
    {
        this.deletebaseprobut.addActionListener(a);
    }
    public void addlistListener(ActionListener a)
    {
        this.addlistbut.addActionListener(a);
    }
    public void composeListener(ActionListener a)
    {
        this.composebut.addActionListener(a);
    }
    public void rap1Lisneter(ActionListener a)
    {
        this.rap1but.addActionListener(a);
    }
    public void rap2Lisneter(ActionListener a)
    {
        this.rap2but.addActionListener(a);
    }
    public void rap3Lisneter(ActionListener a)
    {
        this.rap3but.addActionListener(a);
    }
    public void rap4Lisneter(ActionListener a)
    {
        this.rap4but.addActionListener(a);
    }

    public void setalldataprods(String s)
    {
        prod.setText(s);
    }
    public void setalldataorders(String s)
    {
        orders.setText(s);
    }
    public void setalldatacomp(String s)
    {
        comp.setText(s);
    }



}
