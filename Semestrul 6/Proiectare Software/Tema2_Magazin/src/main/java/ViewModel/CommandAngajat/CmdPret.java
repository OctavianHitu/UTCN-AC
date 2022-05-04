package ViewModel.CommandAngajat;

import Model.Incaltaminte;
import Model.IncaltaminteMagazin;
import Model.PersistentaIncaltaminte;
import ViewModel.ICommand;
import ViewModel.VMAngajat;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CmdPret implements ICommand {

    private VMAngajat vmAngajat;
    private PersistentaIncaltaminte persistentaIncaltaminte;

    public CmdPret(ViewModel.VMAngajat vm, PersistentaIncaltaminte pe) {
        this.persistentaIncaltaminte = pe;
        this.vmAngajat = vm;

    }

    @Override
    public void Execute()
    {
        String pret= vmAngajat.getPret().get();


        List<IncaltaminteMagazin> afis= new ArrayList<>();

        if(pret.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Campul de pret este gol!");

        }
        else
        {
            List<IncaltaminteMagazin> list = persistentaIncaltaminte.getIncaltaminteMagazins();
            for (IncaltaminteMagazin i : list)
            {
                for (Incaltaminte ii : i.getIncaltamintes())
                {
                    if(ii.getPret()<Integer.parseInt(pret))
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
