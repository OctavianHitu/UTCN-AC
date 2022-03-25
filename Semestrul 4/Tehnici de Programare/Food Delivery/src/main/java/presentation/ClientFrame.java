package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientFrame extends JFrame {

    private JLabel titlelbl = new JLabel("Title");
    private JLabel pricelbl = new JLabel("Price");
    private JLabel sodiumlbl = new JLabel("Sodium");
    private JLabel ratinglbl = new JLabel("Rating");
    private JLabel proteinlbl = new JLabel("Protein");
    private JLabel calorieslbl = new JLabel("Calories");
    private JLabel fatlbl = new JLabel("Fat");

    private JTextField titletf = new JTextField();
    private JTextField ratingtf = new JTextField();
    private JTextField pricetf = new JTextField();
    private JTextField proteintf = new JTextField();
    private JTextField fattf = new JTextField();
    private JTextField caloriestf = new JTextField();
    private JTextField sodiumtf = new JTextField();

    private JTextPane products = new JTextPane();
    private JScrollPane productsscroll = new JScrollPane(products);

    private JTextPane orders = new JTextPane();
    private JScrollPane ordersscroll = new JScrollPane(orders);


    private JButton listprodsbut ;
    private JButton addlistbut;
    private JButton addorderbut;

    private JButton search ;






    public ClientFrame(){

        this.setSize(1800,1000);
        this.setVisible(true);
        this.setTitle("CLIENT");
        this.setLayout(new GridLayout(0,3));

        JPanel leftpanel = new JPanel();
        leftpanel.setLayout(new GridLayout(13,2));
        leftpanel.add(titlelbl);
        leftpanel.add(titletf);
        leftpanel.add(pricelbl);
        leftpanel.add(pricetf);
        leftpanel.add(ratinglbl);
        leftpanel.add(ratingtf);
        leftpanel.add(sodiumlbl);
        leftpanel.add(sodiumtf);
        leftpanel.add(calorieslbl);
        leftpanel.add(caloriestf);
        leftpanel.add(proteinlbl);
        leftpanel.add(proteintf);
        leftpanel.add(fatlbl);
        leftpanel.add(fattf);

         listprodsbut =new JButton("List Base Prods");
         addlistbut=new JButton("Add to list");
         addorderbut=new JButton("Add Order");

         search =new JButton("Search");
         leftpanel.add(listprodsbut);
        leftpanel.add(addlistbut);
        leftpanel.add(addorderbut);

        leftpanel.add(search);

        this.add(leftpanel);
        this.add(ordersscroll);
        this.add(productsscroll);

    }

    public void listproductslistener(ActionListener a)
    {
        this.listprodsbut.addActionListener(a);
    }
    public void addlistlistener(ActionListener a)
    {
        this.addlistbut.addActionListener(a);
    }
    public void addorderlistener(ActionListener a)
    {
        this.addorderbut.addActionListener(a);
    }

   public void searchsimplelistener(ActionListener a)
    {
        this.search.addActionListener(a);
    }



    public void setallproducts(String s)
    {
        products.setText(s);
    }
    public void setallorders(String s)
    {
        orders.setText(s);
    }

    public String getTitletf() {
        return titletf.getText();
    }

    public String getRatingtf() {
        return ratingtf.getText();
    }

    public String getPricetf() {
        return pricetf.getText();
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

    public String getSodiumtf() {
        return sodiumtf.getText();
    }



}
