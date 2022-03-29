package Model;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class IncaltamintePersistenta extends  IncaltaminteXml implements  Jsonsave,Csvsave{

    private static List<IncaltainteMagazin> incaltainteMagazins;

    public  IncaltamintePersistenta()
    {
        this.incaltainteMagazins= new ArrayList<>();
        this.incaltainteMagazins= readfromxml();
        System.out.println(this.incaltainteMagazins);
    }

    public static List<IncaltainteMagazin> getIncaltamintelist()
    {
        return incaltainteMagazins;
    }

    public static IncaltainteMagazin getincaltaminte(String numemagazn, String nume, String producator, Integer marime)
    {
        IncaltainteMagazin incaltainteMagazin=null;
        for(IncaltainteMagazin mg:incaltainteMagazins)
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
                        incaltainteMagazin= new IncaltainteMagazin(mg.getNumemagazin());
                        incaltainteMagazin.addincaltaminteinmagazin(in);

                    }
                }

            }
        }
        return incaltainteMagazin;
    }


    public static boolean existincaltaminte(IncaltainteMagazin mag)
    {
        boolean exist = false;
        for(IncaltainteMagazin mg:incaltainteMagazins)
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

    public static boolean addIncaltaminte(IncaltainteMagazin mag)  {

        Incaltaminte incaltaminte=mag.getIncaltamintes().get(0);

        if(existincaltaminte(mag)){

            return false;
        }
        else
        {
            incaltainteMagazins.add(mag);
            addincaltamintetodoc(mag);
            return true;
        }


    }

    public static void deleteincaltaminte(IncaltainteMagazin incaltainteMagazin)  {
        deleteincaltamintexml(incaltainteMagazin);
        incaltainteMagazins=readfromxml();
    }

    public static void updateincaltamintep(IncaltainteMagazin oldi, IncaltainteMagazin newi)  {
        deleteincaltaminte(oldi);
        addIncaltaminte(newi);
        incaltainteMagazins= readfromxml();
    }


    public List<IncaltainteMagazin> getIncaltainteMagazins() {
        return incaltainteMagazins;
    }
    @Override
    public String toString() {
        String result ="";

        for (IncaltainteMagazin im:incaltainteMagazins) {
            result+=im+"\n";
        }
        return result;
    }


}
