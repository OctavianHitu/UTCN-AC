import Businesslayer.DeliveryService;
import controller.ControllerFirstFrame;
import presentation.AdministratorFrame;
import presentation.Firstframe;
import presentation.UserPassFrame;
import presentation.ClientFrame;

import java.io.IOException;

public class Main {

    public static  void main(String[] args) throws IOException {

       Firstframe fs= new Firstframe();
       fs.setVisible(true);
        DeliveryService delivsrv= new DeliveryService();
        ControllerFirstFrame ct= new ControllerFirstFrame(fs,delivsrv);
    }



}
