package Model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class IncaltaminteMagazin  {
    private String numemagazin;
    public List<Incaltaminte> incaltamintes;

    public  IncaltaminteMagazin(String numemagazin)
    {
        this.numemagazin=numemagazin;
        this.incaltamintes=new ArrayList<>();
    }

    public  void addincaltaminteinmagazin(Incaltaminte i)
    {
        this.incaltamintes.add(i);
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
