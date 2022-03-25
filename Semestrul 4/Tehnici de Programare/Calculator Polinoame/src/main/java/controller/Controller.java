package controller;
import model.*;
import view.*;
import  java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
public class Controller {
    private View view;
    private Polinom polinom1 = new Polinom();
    private Polinom polinom2 = new Polinom();
    private Polinom polinomf = new Polinom();
    private Monom monom1 = new Monom();
    private Monom monom2 = new Monom();

    public Controller(View v)
    { this.view=v;
        v.butadauga1listener(new adaugalistener());
        v.butadauga2listener(new adauga2listener());
        v.butsumalistener(new sumalistener());
        v.butscaderelistener(new scaderelistener());
        v.butinmultirelistener(new inmultirelistener());
        v.butderivarelistener(new derivarelistener());
        v.butintegrarelistener(new integrarelistener());
        v.butimpartirelistener(new impartirelistener());
        v.butclearlistener(new clearlistener());
    }

    class adaugalistener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            int coeficient =0;
            int exponent=0;
            try{
                coeficient= Integer.parseInt(view.getcoef1tf().getText());
                exponent= Integer.parseInt(view.getexp1tf().getText());
                Monom mon= new Monom(coeficient,exponent);
                polinom1.adaugaelementinpolinom(mon);
                view.getpolinom1tf().setText(polinom1.printarepolinom());
                view.getcoef1tf().setText("");
                view.getexp1tf().setText("");
            }catch(NumberFormatException n) {
                JOptionPane.showMessageDialog(view.getpanel(),"Nu e bine!");
            } }}

    class adauga2listener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {   int coeficient =0;
            int exponent=0;
            try{
                coeficient= Integer.parseInt(view.getcoef2tf().getText());
                exponent= Integer.parseInt(view.getexp2tf().getText());
                Monom mon2= new Monom(coeficient,exponent);
                polinom2.adaugaelementinpolinom(mon2);
                view.getpolinom2tf().setText(polinom2.printarepolinom());
                view.getcoef2tf().setText("");
                view.getexp2tf().setText("");
            }catch(NumberFormatException n)
            {
                JOptionPane.showMessageDialog(view.getpanel(),"Nu e bine!");
            }
        }
    }

    class sumalistener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {   view.getpolinomfinaltf().setText("");
            polinomf= new Polinom() ;
            polinomf.adunarepolinoame(polinom1,polinom2);
            view.getpolinomfinaltf().setText(polinomf.printarepolinom());
        }}

    class scaderelistener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            view.getpolinomfinaltf().setText("");
            polinomf= new Polinom();
            polinomf.scaderepolinom(polinom1,polinom2);
            view.getpolinomfinaltf().setText(polinomf.printarepolinom());
        }}
    class inmultirelistener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            view.getpolinomfinaltf().setText("");
            polinomf= new Polinom();
            polinomf.inmultirepolinoame(polinom1,polinom2);
            view.getpolinomfinaltf().setText(polinomf.printarepolinom());
        }}
    class derivarelistener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            view.getpolinomfinaltf().setText("");

            polinom1.derivarepolinoame();
            view.getpolinomfinaltf().setText(polinom1.printarepolinom());
        }}

        class integrarelistener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            view.getpolinomfinaltf().setText("");
            polinom1.integrarepolinom();
            view.getpolinomfinaltf().setText(polinom1.printarepolinom());
        }
        }

        class impartirelistener implements  ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            view.getpolinomfinaltf().setText("");
            polinomf= new Polinom();
            polinomf.impartirepolinoame(polinom1,polinom2);
            view.getpolinomfinaltf().setText(polinomf.printarepolinom());

        }
        }

    class clearlistener implements ActionListener{
    public void actionPerformed(ActionEvent e)
    {   view.getpolinomfinaltf().setText("");
        view.getpolinom1tf().setText("");
        view.getpolinom2tf().setText("");
        polinom1.clear();
        polinom2.clear();
        polinomf.clear();
    }}

}
