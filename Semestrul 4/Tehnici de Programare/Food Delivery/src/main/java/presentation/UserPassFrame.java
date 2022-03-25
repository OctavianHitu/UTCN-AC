package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserPassFrame extends JFrame{
    private JLabel userlbl=new JLabel("USERNAME:");
    private JLabel passlbl=new JLabel("PASSWORD:");

    private JTextField usertf=new JTextField("Write user here /pass down");
    private JPasswordField passtf=new JPasswordField("password");

    private JButton logbut=new JButton("LOGIN");
    private JButton regbut=new JButton("REGISTER");

    //private JPanel mainpanel = new JPanel();


    public UserPassFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("Login/Register Meniu");
        this.setBounds(0,0,400,400);
        //this.add(mainpanel);


        this.setLayout(new GridLayout(3,2));
        this.add(userlbl);
        this.add(usertf);
        this.add(passlbl);
        this.add(passtf);
        this.add(logbut);
        this.add(regbut);

    }

    public void loginbutlistener(ActionListener logbut)
    {
        this.logbut.addActionListener(logbut);
    }
    public void regbutlistener(ActionListener regbut)
    {
        this.regbut.addActionListener(regbut);
    }
    public String getusernametf()
    {
        return usertf.getText();
    }
    public String getpasswordtf()
    {
        return  passtf.getText();
    }

}
