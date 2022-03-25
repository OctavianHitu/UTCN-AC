package controller;
import bll.Clientbll;
import bll.Orderbll;
import bll.Productbll;
import model.*;
import presentation.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
     private View v;
     private Clientbll clientb;
     private Orderbll orderb;
     private Productbll productb;



    public Controller(View vi)
    {
        this.v=vi;
        this.clientb= new Clientbll();
        this.orderb= new Orderbll();
        this.productb= new Productbll();
        v.CLientaddlistener(new ClientaddActionListener());
        v.CLienteditlistener(new ClienteditActionListener());
        v.CLientDeleteListener(new ClientdeleteActionListener());
        v.Clientviewlistener( new ClientviewActionListener());


        v.Productaddlistener(new ProductaddActionListener());
        v.Productdeletelistener(new ProductdeleteActionListener());
        v.Producteditlistener(new ProducteditActionListener());
        v.ProductViewlistener(new ProductviewActionListener());

        v.Orderaddlistener(new OrderaddActionListener());
        v.Orderdeletelistener(new OrderdeleteActionListener());
        v.Ordereditlistener(new OrdereditActionListener());
        v.Orderviewlistener(new OrderviewActionListener());

    }

    public class ClientaddActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int id=Integer.parseInt(v.getIdclnttf());
            String lastname=v.getLnclnttf();
            String firstname=v.getFnclnttf();
            String address=v.getAddclnttf();

            Client clnt= new Client(id,lastname,firstname,address);
            clientb.insert(clnt);



        }
    }
    public class ClienteditActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int id=Integer.parseInt(v.getIdclnttf());
            String lastname=v.getLnclnttf();
            String firstname=v.getFnclnttf();
            String address=v.getAddclnttf();

            Client clnt= new Client(id,lastname,firstname,address);
            clientb.update(clnt);

        }
    }
    public class ClientdeleteActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int id=Integer.parseInt(v.getIdclnttf());
            String lastname=v.getLnclnttf();
            String firstname=v.getFnclnttf();
            String address=v.getAddclnttf();

            Client clnt= new Client(id,lastname,firstname,address);
            clientb.delete(clnt);

        }
    }
    public class ClientviewActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                Object[] obj = clientb.getheader();
                Object[][] obj2 = clientb.gettable2();
                v.newframeforview(obj2,obj);



        }
    }

    public class ProductaddActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int id=Integer.parseInt(v.getIdprotf());
            String productname=v.getNmprotf();
            float price= Integer.parseInt(v.getPrprotf());
            int quantity= Integer.parseInt(v.getQuaprotf());

            Product pro= new Product(id,productname,price,quantity);
            productb.insert(pro);


        }
    }
    public class ProductdeleteActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int id=Integer.parseInt(v.getIdprotf());
            String productname=v.getNmprotf();
            float price= Integer.parseInt(v.getPrprotf());
            int quantity= Integer.parseInt(v.getQuaprotf());

            Product pro= new Product(id,productname,price,quantity);
            productb.delete(pro);


        }
    }
    public class ProducteditActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int id=Integer.parseInt(v.getIdprotf());
            String productname=v.getNmprotf();
            float price= Integer.parseInt(v.getPrprotf());
            int quantity= Integer.parseInt(v.getQuaprotf());

            Product pro= new Product(id,productname,price,quantity);
            productb.update(pro);


        }
    }
    public class ProductviewActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            Object[] obj = productb.getheader();
            Object[][] obj2 = productb.gettable2();
            v.newframeforview(obj2,obj);

        }
    }

    public class OrderaddActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            int id=Integer.parseInt(v.getIdordertf());
            int clientid=Integer.parseInt(v.getClientidordertf());
            int productid=Integer.parseInt(v.getProductidordertf());
            int quantityorder=Integer.parseInt(v.getQuaordertf());



            Object[][] obj = orderb.gettable2();
            int sumacantitati=0;
            int i,j;
            for(i=0;i<obj.length;i++)
            {
                for(j=0;j<obj[i].length;j++)
                {
                    if(productid==(int)obj[i][2]&&j==3)
                    {
                        sumacantitati = sumacantitati + (int) obj[i][j];

                    }
                }
            }
            sumacantitati= sumacantitati + quantityorder;
            Product pr = productb.findById(productid);
            int qua = pr.getQuantity();
            if(sumacantitati<qua)
            {

                Orders ord = new Orders(id,clientid,productid,quantityorder);
                orderb.insert(ord);
            }
            else
            {
                throw new IllegalArgumentException("NU cantitate prea mare");
            }



        }
    }
    public class OrderdeleteActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            int id=Integer.parseInt(v.getIdordertf());
            int clientid=Integer.parseInt(v.getClientidordertf());
            int productid=Integer.parseInt(v.getProductidordertf());
            int quantityorder=Integer.parseInt(v.getQuaordertf());


            Orders ord = new Orders(id,clientid,productid,quantityorder);
            orderb.delete(ord);


        }
    }

    public class OrdereditActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {


            int id=Integer.parseInt(v.getIdordertf());
            int clientid=Integer.parseInt(v.getClientidordertf());
            int productid=Integer.parseInt(v.getProductidordertf());
            int quantityorder=Integer.parseInt(v.getQuaordertf());


            Orders ord = new Orders(id,clientid,productid,quantityorder);
            orderb.update(ord);

        }
    }

    public class OrderviewActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            Object[] obj = orderb.getheader();
            Object[][] obj2 = orderb.gettable2();
            v.newframeforview(obj2,obj);

        }
    }

}
