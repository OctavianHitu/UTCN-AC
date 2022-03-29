package Model;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class IncaltainteMagazin extends Incaltaminte{
    private String numemagazin;
    public List<Incaltaminte> incaltamintes;

    public IncaltainteMagazin(String numemagazin, List<Incaltaminte> incaltamintes) {
        this.numemagazin = numemagazin;
        this.incaltamintes = incaltamintes;
    }

    public IncaltainteMagazin(String numemagazin)
    {
        this.numemagazin=numemagazin;
        this.incaltamintes=new ArrayList<>();
    }

    public String getNumemagazin() {
        return numemagazin;
    }

    public void setNumemagazin(String numemagazin) {
        this.numemagazin = numemagazin;
    }

    public List<Incaltaminte> getIncaltamintes() {
        return this.incaltamintes;
    }

    public void setIncaltamintes(List<Incaltaminte> incaltamintes) {
        this.incaltamintes = incaltamintes;
    }

    public  void addincaltaminteinmagazin(Incaltaminte i)
    {
        this.incaltamintes.add(i);
    }

    public  void removeincaltaminteinmagazin(Incaltaminte i)
    {
        this.incaltamintes.remove(i);
    }



    @Override
    public String toString() {

        String result ="";

        result = "Nume Magazin:" + numemagazin + ".\n";
        for(Incaltaminte p: incaltamintes){
            result+="   ";
            result+=p;
            result+="\n";
        }
        result+="\n";

        return result;
    }


}
