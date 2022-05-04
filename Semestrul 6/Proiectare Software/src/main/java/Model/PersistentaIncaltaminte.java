package Model;

import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PersistentaIncaltaminte extends IncaltaminteDao implements  JsonSave,CsvSave,XmlSave{
    private static List<IncaltaminteMagazin> incaltaminteMagazins;

    public static List<IncaltaminteMagazin> getIncaltaminteMagazins() {
        return incaltaminteMagazins;
    }

    public PersistentaIncaltaminte()
    {
        this.incaltaminteMagazins= new ArrayList<>();
        this.incaltaminteMagazins=this.findallincalataminte();

    }

    public static IncaltaminteMagazin getincaltaminte(String numemagazn, String nume, String producator, Integer marime)
    {
        IncaltaminteMagazin incaltainteMagazin=null;
        for(IncaltaminteMagazin mg:incaltaminteMagazins)
        {
            if(mg.getNumemagazin().equals(numemagazn))
            {
                List<Incaltaminte> incaltamintes=mg.getIncaltamintes();
                for(Incaltaminte in: incaltamintes)
                {
                    if(in.getNume().equals(nume)&&
                            in.getProducator().equals(producator)&&
                            in.getIncaltaminteDisponibila().getMarime().equals(marime))
                    {
                        incaltainteMagazin= new IncaltaminteMagazin(mg.getNumemagazin());
                        incaltainteMagazin.addincaltaminteinmagazin(in);

                    }
                }

            }
        }
        return incaltainteMagazin;
    }

    public static boolean existincataminte(IncaltaminteMagazin mag)
    {
        boolean exist=false;

        for(IncaltaminteMagazin mg:incaltaminteMagazins)
        {
            if(mg.getNumemagazin().equals(mag.getNumemagazin()) &&
                    mg.getIncaltamintes().get(0).getNume().equals(mag.getIncaltamintes().get(0).getNume()) &&
                    mg.getIncaltamintes().get(0).getProducator().equals(mag.getIncaltamintes().get(0).getProducator()))
            {
                for(Incaltaminte i:mg.incaltamintes)
                {
                    if(i.getIncaltaminteDisponibila().getMarime().equals(mag.getIncaltamintes().get(0).getIncaltaminteDisponibila().getMarime()))
                    {
                        exist=true;
                    }
                }

            }
        }

        return exist;

    }

    public boolean addincaltaminte(IncaltaminteMagazin mag)
    {
        Incaltaminte incaltaminte= mag.getIncaltamintes().get(0);

        if(existincataminte(mag))
        {
            return false;
        }
        else
        {
            this.insertincaltamintedao(mag);
            incaltaminteMagazins=this.findallincalataminte();
            return true;

        }

    }

    public void removeincaltaminte(IncaltaminteMagazin inc)
    {
        this.deleteincaltamintedao(inc);
        this.incaltaminteMagazins= this.findallincalataminte();
    }

    public void updateincaltaminte(IncaltaminteMagazin oldm, IncaltaminteMagazin newm)
    {
        this.removeincaltaminte(oldm);
        this.addincaltaminte(newm);
        this.incaltaminteMagazins= this.findallincalataminte();

    }
}
