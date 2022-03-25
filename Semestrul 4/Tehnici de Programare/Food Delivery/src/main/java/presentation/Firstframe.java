package presentation;


import javax.swing.*;
import java.awt.event.ActionListener;

public class Firstframe extends JFrame {
    private JPanel mainpanel = new JPanel();

    JButton clientbutton= new JButton("Client");
    JButton butadministrator= new JButton("Administrator");

    public Firstframe()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0,0,400,400);
        this.setTitle("First Meniu");


        mainpanel.setBounds(0,0,400,400);
        this.add(mainpanel);
        mainpanel.setLayout(null);

        clientbutton= new JButton("Client");
        clientbutton.setBounds(140,50,120,40);
        mainpanel.add(clientbutton);

        butadministrator = new JButton("Administrator");
        butadministrator.setBounds(140, 200, 120, 40);
        mainpanel.add(butadministrator);


    }

    public void administratorButtonListener(ActionListener administrator) {
        this.butadministrator.addActionListener(administrator);
    }

    public void clientButtonListener(ActionListener clientButton) {
        this.clientbutton.addActionListener(clientButton);
    }
}
