package controller;

import model.*;
import Businesslayer.*;
import presentation.ClientFrame;
import presentation.UserPassFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerUserPassFrame {

    private UserPassFrame usp = new UserPassFrame();
    private DeliveryService delivsrv = new DeliveryService();

    public ControllerUserPassFrame(UserPassFrame usp, DeliveryService dvs)
    {
        this.usp=usp;
        this.delivsrv=dvs;
        usp.loginbutlistener(new loginbuactionlistener());
        usp.regbutlistener(new regbutactionlistener());

    }

    public class loginbuactionlistener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            String username = usp.getusernametf();
            String password = usp.getpasswordtf();
            Account acc= new Account(username,password);
            if(delivsrv.searchaccount(acc))
            {
                ClientFrame clientfr= new ClientFrame();
                ControllerClientframe ctclfr= new ControllerClientframe(clientfr,delivsrv,acc);

            }
            else
            {
                JOptionPane.showMessageDialog(new JFrame(), "Client nevalid?\nTrebuie inregistrare!");
            }

        }
    }

    public class regbutactionlistener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usp.getusernametf();
            String password = usp.getpasswordtf();
            Account acc= new Account(username,password);
            delivsrv.addaccount(acc);

        }
    }

}
