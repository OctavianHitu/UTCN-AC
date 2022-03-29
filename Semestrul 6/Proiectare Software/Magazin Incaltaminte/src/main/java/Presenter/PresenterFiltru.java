package Presenter;

import Model.IncaltainteMagazin;
import Model.Incaltaminte;
import Model.IncaltamintePersistenta;
import View.AngajatView;
import View.FiltruPret;

import java.util.ArrayList;
import java.util.List;

public class PresenterFiltru {

    private FiltruPret filtruPret;
    private IncaltamintePersistenta ipers;


    public  PresenterFiltru(FiltruPret f)
    {
        this.filtruPret=f;
        this.ipers= new IncaltamintePersistenta();
    }

    public void filtrarefin()
    {
        Integer minim= Integer.parseInt(filtruPret.getPretminimtf());
        Integer maxim = Integer.parseInt(filtruPret.getPretmaximtf());

        List<IncaltainteMagazin> incaltainteMagazins= IncaltamintePersistenta.getIncaltamintelist();
        List<IncaltainteMagazin> afis= new ArrayList<>();
        for (IncaltainteMagazin in: incaltainteMagazins)
        {
            for(Incaltaminte inc:in.getIncaltamintes())
            {

                if(inc.getPret()<=maxim)
                {
                    if(inc.getPret()>=minim)
                    {

                        IncaltainteMagazin im = new IncaltainteMagazin(in.getNumemagazin());
                        im.addincaltaminteinmagazin(inc);
                        afis.add(im);
                    }

                }

            }
        }
        filtruPret.afisareincal(afis.toString());
    }



}
