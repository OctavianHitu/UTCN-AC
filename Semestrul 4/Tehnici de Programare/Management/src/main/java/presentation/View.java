package presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class View  extends JFrame{
    private JPanel mainpanel = new JPanel();
    private JPanel clientpanel = new JPanel();
    private JPanel productpanel = new JPanel();
    private JPanel orderpanel = new JPanel();
    private JButton clientbutton;
    private JButton productbutton;
    private JButton orderbutton;
    //butoane pt client
    private JButton addclientbutton;
    private  JButton deleteclientbutton;
    private JButton editclientbutton;
    private JButton viewclientbutton;
    private JButton backclientbutton;
    //tf pt client
    private TextField idclnttf= new TextField();
    private TextField fnclnttf= new TextField();
    private TextField lnclnttf= new TextField();
    private TextField addclnttf= new TextField();
    //butoane pt product
    private JButton addproductbutton;
    private  JButton deleteproductbutton;
    private JButton editproductbutton;
    private JButton viewproductbutton;
    private JButton backproductbutton;
    //tf pt product
    private TextField idprotf= new TextField();
    private TextField nmprotf= new TextField();
    private TextField prprotf= new TextField();
    private TextField quaprotf= new TextField();
    //butoane pt order
    private JButton addorderbutton;
    private  JButton deleteorderbutton;
    private JButton editorderbutton;
    private JButton vieworderbutton;
    private JButton backorderbutton;
    //tf pt order
    private TextField idordertf= new TextField();
    private TextField clientidordertf= new TextField();
    private TextField productidordertf= new TextField();
    private TextField quaordertf= new TextField();
    public View()
    { this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0,0,700,500);
        this.setTitle("WareHouse");

        //MAIN PANEL
        mainpanel.setBounds(0,0,700,500);
        this.add(mainpanel);
        mainpanel.setLayout(null);

        clientbutton= new JButton("Client");
        clientbutton.setBounds(20,20,90,20);
        mainpanel.add(clientbutton);
        clientbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainpanel.setVisible(false);
                clientpanel.setVisible(true);
            }
        });
        productbutton = new JButton("Product");
        productbutton.setBounds(20, 120, 90, 20);
        mainpanel.add(productbutton);
        productbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainpanel.setVisible(false);
                productpanel.setVisible(true);
            }
        });
        orderbutton = new JButton("Order");
        orderbutton.setBounds(20, 220,90, 20);
        mainpanel.add(orderbutton);
        this.setVisible(true);
        orderbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainpanel.setVisible(false);
                orderpanel.setVisible(true);
            }
        });
        //CLIENT PANEL
        clientpanel.setBounds(0,0,700,500);
        this.add(clientpanel);
        clientpanel.setLayout(new GridLayout(2,0));
        clientpanel.setVisible(false);
        JPanel tfclnt= new JPanel();
        tfclnt.setLayout(new GridLayout(4,2));
        tfclnt.add(new JLabel("  Id"));
        tfclnt.add(idclnttf);
        tfclnt.add(new JLabel("  Firstname"));
        tfclnt.add(fnclnttf);
        tfclnt.add(new JLabel("  Lastname"));
        tfclnt.add(lnclnttf);
        tfclnt.add(new JLabel("  Address"));
        tfclnt.add(addclnttf);
        clientpanel.add(tfclnt);
        JPanel buttonclnt= new JPanel(new GridLayout(3,2));
        addclientbutton = new JButton("Add Client");
        buttonclnt.add(addclientbutton);
        deleteclientbutton = new JButton("Delete CLient");
        buttonclnt.add(deleteclientbutton);
        editclientbutton = new JButton("Edit Client");
        buttonclnt.add(editclientbutton);
        viewclientbutton= new JButton("View");
        buttonclnt.add(viewclientbutton);
        backclientbutton = new JButton("Back Main");
        buttonclnt.add(backclientbutton);
        backclientbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientpanel.setVisible(false);
                productpanel.setVisible(false);
                orderpanel.setVisible(false);
                mainpanel.setVisible(true);
            }
        });
        clientpanel.add(buttonclnt);
        //PRODUCT PANEL
            productpanel.setBounds(0, 0, 700, 500);
            this.add(productpanel);
            productpanel.setLayout(new GridLayout(2, 0));
            productpanel.setVisible(false);
            JPanel tfpro = new JPanel();
            tfpro.setLayout(new GridLayout(4, 2));
            tfpro.add(new JLabel("  Id"));
            tfpro.add(idprotf);
            tfpro.add(new JLabel("  Productname"));
            tfpro.add(nmprotf);
            tfpro.add(new JLabel("  Price"));
            tfpro.add(prprotf);
            tfpro.add(new JLabel("  Quantity"));
            tfpro.add(quaprotf);
            productpanel.add(tfpro);

            JPanel buttonpro = new JPanel(new GridLayout(3, 2));

            addproductbutton = new JButton("Add Product");
            buttonpro.add(addproductbutton);
            deleteproductbutton = new JButton("Delete Product");
            buttonpro.add(deleteproductbutton);
            editproductbutton = new JButton("Edit Product");
            buttonpro.add(editproductbutton);
            viewproductbutton = new JButton("View");
            buttonpro.add(viewproductbutton);
            backproductbutton = new JButton("Back Main");
            buttonpro.add(backproductbutton);
            backproductbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clientpanel.setVisible(false);
                    productpanel.setVisible(false);
                    orderpanel.setVisible(false);
                    mainpanel.setVisible(true);
                }
            });
            productpanel.add(buttonpro);
        //ORDER PANEL
        orderpanel.setBounds(0, 0, 700, 500);
        this.add(orderpanel);
        orderpanel.setLayout(new GridLayout(2, 0));
        orderpanel.setVisible(false);
        JPanel tford = new JPanel();
        tford.setLayout(new GridLayout(4, 2));
        tford.add(new JLabel("  Id"));
        tford.add(idordertf);
        tford.add(new JLabel("  Clientid"));
        tford.add(clientidordertf);
        tford.add(new JLabel("  Productid"));
        tford.add(productidordertf);
        tford.add(new JLabel("  Quantityorder"));
        tford.add(quaordertf);
        orderpanel.add(tford);
        JPanel buttonord = new JPanel(new GridLayout(3, 2));
        addorderbutton = new JButton("Add Order");
        buttonord.add(addorderbutton);
        deleteorderbutton = new JButton("Delete Order");
        buttonord.add(deleteorderbutton);
        editorderbutton = new JButton("Edit Order");
        buttonord.add(editorderbutton);
        vieworderbutton = new JButton("View");
        buttonord.add(vieworderbutton);
        backorderbutton = new JButton("Back Main");
        buttonord.add(backorderbutton);
        backorderbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientpanel.setVisible(false);
                productpanel.setVisible(false);
                orderpanel.setVisible(false);
                mainpanel.setVisible(true);
            }
        });
        orderpanel.add(buttonord);

    }
    public void newframeforview(Object[][] obj,Object[] clm)
    {
        JFrame viewframe = new JFrame();
        viewframe.setTitle("View");
        JTable table = new JTable(obj,clm);
        JScrollPane scrlpnl = new JScrollPane(table);
        viewframe.add(scrlpnl);

        viewframe.setBounds(0,0,700,500);
        viewframe.setVisible(true);

    }
    public String getIdclnttf() {
        return idclnttf.getText();
    }
    public String getFnclnttf() {
        return fnclnttf.getText();
    }
    public String getLnclnttf() {
        return lnclnttf.getText();
    }
    public String getAddclnttf() {
        return addclnttf.getText();
    }
    public String getIdprotf() {
        return idprotf.getText();
    }
    public String getNmprotf() {
        return nmprotf.getText();
    }
    public String getPrprotf() {
        return prprotf.getText();
    }
    public String getQuaprotf() {
        return quaprotf.getText();
    }
    public String getIdordertf() {
        return idordertf.getText();
    }
    public String getClientidordertf() {
        return clientidordertf.getText();
    }
    public String getProductidordertf() {
        return productidordertf.getText();
    }
    public String getQuaordertf() {
        return quaordertf.getText();
    }
    public void CLientaddlistener(ActionListener c)
    {
        this.addclientbutton.addActionListener(c);
    }
    public void CLientDeleteListener(ActionListener c)
    {
        this.deleteclientbutton.addActionListener(c);
    }
    public void CLienteditlistener(ActionListener c)
    {
        this.editclientbutton.addActionListener(c);
    }
    public void Clientviewlistener(ActionListener c)
    {
        this.viewclientbutton.addActionListener(c);
    }
    public void Productaddlistener(ActionListener c)
    {
        this.addproductbutton.addActionListener(c);
    }
    public void Productdeletelistener(ActionListener c)
    {
        this.deleteproductbutton.addActionListener(c);
    }
    public void Producteditlistener(ActionListener c)
    {
        this.editproductbutton.addActionListener(c);
    }
    public void ProductViewlistener(ActionListener c)
    {
        this.viewproductbutton.addActionListener(c);
    }
    public void Orderaddlistener(ActionListener c)
    {
        this.addorderbutton.addActionListener(c);
    }
    public void Orderdeletelistener(ActionListener c)
    {
        this.deleteorderbutton.addActionListener(c);
    }
    public void Ordereditlistener(ActionListener c)
    {
        this.editorderbutton.addActionListener(c);
    }
    public void Orderviewlistener(ActionListener c)
    {
        this.vieworderbutton.addActionListener(c);
    }
}
