package ViewModel.CommandAngajat;

import Model.Incaltaminte;
import Model.IncaltaminteMagazin;
import Model.PersistentaIncaltaminte;
import ViewModel.ICommand;
import ViewModel.VMAngajat;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CmdProducator implements ICommand {

    private VMAngajat vmAngajat;
    private PersistentaIncaltaminte persistentaIncaltaminte;

    public CmdProducator(ViewModel.VMAngajat vm, PersistentaIncaltaminte pe)
    {
        this.persistentaIncaltaminte=pe;
        this.vmAngajat=vm;
    }


    @Override
    public void Execute()
    {
        String producator=vmAngajat.getProducator().get();
        List<IncaltaminteMagazin> afis= new ArrayList<>();
        if(producator.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Campul de producator este gol!");
        }
        else
        {
            List<IncaltaminteMagazin> list= persistentaIncaltaminte.getIncaltaminteMagazins();

            for(IncaltaminteMagazin i: list)
            {
                for(Incaltaminte ii :i.getIncaltamintes())
                {
                    if(ii.getProducator().equals(producator))
                    {
                        IncaltaminteMagazin im= new IncaltaminteMagazin(i.getNumemagazin());
                        im.addincaltaminteinmagazin(ii);

                        afis.add(im);
                    }
                }
            }

            vmAngajat.setData(afis.toString());
        }
    }
}
