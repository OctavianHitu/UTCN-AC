package view;

import java.awt.event.ActionListener;
import javax.swing.*;




public class View extends JFrame {
    JPanel panel = new JPanel();
    //text field urile pt introducere monoame din model.polinom 1
   private JTextField coeficient1tf = new JTextField(10);
   private JTextField exponent1tf = new JTextField(10);
//text field urile pt introducere monoame model.polinom 2
    private JTextField coeficient2tf = new JTextField(10);
    private JTextField exponent2tf = new JTextField(10);
//label urile pentru text fielduri
    private JLabel coeficient1lbl = new JLabel(" Coeficinet:");
    private JLabel exponent1lbl = new JLabel(" Exponent:");
    private JLabel coeficient2lbl = new JLabel("Coeficinet:");
    private JLabel exponent2lbl = new JLabel("Exponent:");
//text  urile pentru polinoame si rezultat
    private JTextField polinom1tf = new JTextField(20);
    private JTextField polinom2tf = new JTextField(20);
    private  JTextField polinomfinaltf = new JTextField(20);
    //label urile pt polinoame
    private JLabel polinom1lbl = new JLabel("Primul model.polinom:");
    private JLabel polinom2lbl = new JLabel("Al doilea model.polinom:");
    private  JLabel polinomfinallbl = new JLabel("Polinom final:");

    //butoane de adaugare
    JButton butadauga1 = new JButton("Adauga");
    JButton butadauga2 = new JButton("Adauga");

    JButton butsuma = new JButton("suma");
    JButton butscadere = new JButton("scadere");
    JButton butinmultire = new JButton("mul");
    JButton butderivare = new JButton("derivare");
    JButton butintegrare = new JButton("integrare");
    JButton butimpartire = new JButton("impartire");
    JButton butclear = new JButton("clear");

    public JPanel getpanel(){
        return panel;
    }
    public JTextField getcoef1tf(){
        return coeficient1tf;
    }
    public JTextField getexp1tf(){
        return exponent1tf;
    }
    public  JTextField getcoef2tf(){
        return  coeficient2tf;
    }
    public JTextField getexp2tf()
    {
        return exponent2tf;
    }

    public JTextField getpolinom1tf()
    {
        return polinom1tf;
    }
    public JTextField getpolinom2tf()
    {
        return polinom2tf;
    }
    public JTextField getpolinomfinaltf()
    {
        return polinomfinaltf;
    }

    public View()
    {
        this.setBounds(500,500,650,589);
        this.setVisible(true);
        this.setTitle("Calculator de polinoame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setBounds(0,0,600,600);
        this.add(panel);
        panel.setLayout(null);

        coeficient1lbl.setBounds(30,10,80,20);
        panel.add(coeficient1lbl);
        coeficient1tf.setBounds(30,40,50,20);
        panel.add(coeficient1tf);
        exponent1lbl.setBounds(120,10,80,20);
        panel.add(exponent1lbl);
        exponent1tf.setBounds(120,40,50,20);
        panel.add(exponent1tf);
        polinom1lbl.setBounds(30,110,130,20);
        panel.add(polinom1lbl);
        polinom1tf.setBounds(30,140,280,40);
        panel.add(polinom1tf);
        polinom1tf.setEditable(false);
        butadauga1.setBounds(40,70,100,30);
        panel.add(butadauga1);

        coeficient2lbl.setBounds(300,10,80,20);
        panel.add(coeficient2lbl);
        coeficient2tf.setBounds(300,40,50,20);
        panel.add(coeficient2tf);
        exponent2lbl.setBounds(410,10,80,20);
        panel.add(exponent2lbl);
        exponent2tf.setBounds(410,40,50,20);
        panel.add(exponent2tf);
        polinom2lbl.setBounds(300,110,130,20);
        panel.add(polinom2lbl);
        polinom2tf.setBounds(340,140,280,40);
        panel.add(polinom2tf);
        polinom2tf.setEditable(false);
        butadauga2.setBounds(350,70,100,30);
        panel.add(butadauga2);


        butsuma.setBounds(40,230,100,30);
        panel.add(butsuma);
        butscadere.setBounds(40,260,100,30);
        panel.add(butscadere);
        butinmultire.setBounds(140,230,100,30);
        panel.add(butinmultire);
        butderivare.setBounds(140,260,100,30);
        panel.add(butderivare);
        butintegrare.setBounds(240,230,100,30);
        panel.add(butintegrare);
        butimpartire.setBounds(240,260,100,30);
        panel.add(butimpartire);


        polinomfinallbl.setBounds(260,300,80,20);
        panel.add(polinomfinallbl);
        polinomfinaltf.setBounds(100,340,400,40);
        panel.add(polinomfinaltf);
        polinomfinaltf.setEditable(false);

        butclear.setBounds(260,390,100,40);
        panel.add(butclear);


    }

    public void butadauga1listener(ActionListener a) { butadauga1.addActionListener(a); }
    public void butadauga2listener(ActionListener a) { butadauga2.addActionListener(a); }
    public void butsumalistener(ActionListener a) { butsuma.addActionListener(a); }
    public void butscaderelistener(ActionListener a) { butscadere.addActionListener(a); }
    public void butinmultirelistener(ActionListener a){butinmultire.addActionListener(a);}
    public void butderivarelistener(ActionListener a){butderivare.addActionListener(a);}
    public void butintegrarelistener(ActionListener a){butintegrare.addActionListener(a);}
    public void butimpartirelistener (ActionListener a){butimpartire.addActionListener(a);}
    public void butclearlistener(ActionListener a) { butclear.addActionListener(a); }




}
