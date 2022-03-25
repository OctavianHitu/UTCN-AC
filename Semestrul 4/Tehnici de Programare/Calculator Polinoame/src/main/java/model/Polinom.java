package model;

import java.util.*;

public class Polinom {//polinomul va fi vazut de catre program ca o lista de monoame



    private List<Monom> polinom = new ArrayList<Monom>();
    public Polinom()
    {

    }

    public Polinom(List<Monom> polinom)
    {
        this.polinom=polinom;
    }

    public List<Monom> getpolinom() {
        return polinom;

    }
    public void setpolinom(List<Monom> polinom)
    {
        this.polinom=polinom;
    }

    public  void adaugaelementinpolinom(Monom mon)
    {
        Monom monom = new Monom(mon.getcoeficient(),mon.getexponent());
        this.polinom.add(monom);
    }


    public void adunarepolinoame(Polinom pol1 , Polinom pol2) {
        int i = 0;//contor model.polinom 1
        int j = 0;//contor model.polinom 2
        while (i < pol1.getpolinom().size() && j < pol2.getpolinom().size()) {
            if (pol1.getpolinom().get(i).getexponent() < pol2.getpolinom().get(j).getexponent()) {
                this.polinom.add(pol2.getpolinom().get(j));
                j++;
            } else if (pol1.getpolinom().get(i).getexponent() > pol2.getpolinom().get(j).getexponent()) {
                this.polinom.add(pol1.getpolinom().get(i));
                i++;
            } else if (pol1.getpolinom().get(i).getexponent() == pol2.getpolinom().get(j).getexponent())
            {
                Monom x = new Monom(pol1.getpolinom().get(i).getcoeficient() + pol2.getpolinom().get(j).getcoeficient(), pol1.getpolinom().get(i).getexponent());
                this.polinom.add(x);
                i++;
                j++; } }
            while (i < pol1.getpolinom().size()) {
                this.polinom.add(pol1.getpolinom().get(i));
                i++;
            }
            while (j < pol2.getpolinom().size()) {
                this.polinom.add(pol2.getpolinom().get(j));
                j++;
            }
    }

    public void scaderepolinom(Polinom pol1, Polinom pol2)
    {
        for(Monom i : pol2.getpolinom())
        {
            i.negare();
        }
        this.adunarepolinoame(pol1,pol2);
    }

    public String printarepolinom()
    {
        String s=" ";
        for(Monom m :polinom)
        {
            s=s+ m+" ";
        }
        return s;
    }

    public void clear()
    {
        polinom.clear();
    }



    public void inmultirepolinoame(Polinom pol1, Polinom pol2)
    {

        for (Monom i : pol1.getpolinom())
        {
                for(Monom j : pol2.getpolinom())
                {
                    Monom m= new Monom(1,0);
                    m.inmultiremonoame(i);
                    m.inmultiremonoame(j);
                    this.polinom.add(m);
                }
        }

        for(int i=0;i<this.polinom.size();i++)
        {
            for(int j=i+1;j<this.polinom.size();j++)
            {
                if(this.polinom.get(i).getexponent()==this.polinom.get(j).getexponent())
                {
                    this.getpolinom().get(i).adunaremonoame(this.getpolinom().get(j));
                    this.getpolinom().remove(j);


                }
            }
        }
    }


    public void derivarepolinoame()
    {
        for(Monom i: this.getpolinom())
        {
            i.derivaremonom();
        }
    }

    public void integrarepolinom()
    {
        for(Monom i : this.getpolinom())
        {
            i.integraremonom();
        }
    }


    public void impartirepolinoame(Polinom pol1, Polinom pol2)
    {
        while(pol1.getpolinom().get(0).getexponent() >= pol2.getpolinom().get(0).getexponent())
        {
            Monom mon3 = new Monom(pol2.getpolinom().get(0).getcoeficient()/pol1.getpolinom().get(0).getcoeficient(),pol2.getpolinom().get(0).getexponent()-pol1.getpolinom().get(0).getexponent());

            Polinom pol3 = new Polinom();
            pol3.adaugaelementinpolinom(mon3);
            pol3.inmultirepolinoame(pol2,pol3);
            this.adaugaelementinpolinom(mon3);
            pol1.scaderepolinom(pol1,pol3);
            pol1.polinom.remove(0);

        }
    }


    public void testadaugare(float coeficient ,int exponent)
    {
        Monom mon= new Monom(coeficient,exponent);
        this.polinom.add(mon);
    }




}




