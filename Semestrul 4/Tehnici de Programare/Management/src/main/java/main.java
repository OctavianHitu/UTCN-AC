import bll.Clientbll;
import controller.Controller;
import model.Client;
import presentation.View;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args)
    {

        View v= new View();
        v.setVisible(true);
        Controller c= new Controller(v);


    }

}
