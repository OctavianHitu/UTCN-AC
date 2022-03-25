package controller;

import presentation.*;
import Businesslayer.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerFirstFrame {

    private Firstframe firstfr;
    private DeliveryService deliverysrv;

    public ControllerFirstFrame(Firstframe ff, DeliveryService delivsrv)
    {
        this.firstfr=ff;
        this.deliverysrv=delivsrv;
        firstfr.administratorButtonListener(new administratobutactionlistener());
        firstfr.clientButtonListener(new clientbutactionlistener());
    }

    public class administratobutactionlistener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            AdministratorFrame admfr= new AdministratorFrame();
            new ControllerAdministratorFrame(admfr,deliverysrv);

        }
    }

    public class clientbutactionlistener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            UserPassFrame newfrm = new UserPassFrame();
            ControllerUserPassFrame ct= new ControllerUserPassFrame(newfrm,deliverysrv);
        }
    }


}
