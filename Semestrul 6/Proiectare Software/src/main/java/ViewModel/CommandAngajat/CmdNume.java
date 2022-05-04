package ViewModel.CommandAngajat;

import Model.Incaltaminte;
import Model.IncaltaminteMagazin;
import Model.PersistentaIncaltaminte;
import ViewModel.ICommand;
import ViewModel.VMAngajat;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CmdNume implements ICommand {

    private VMAngajat vmAngajat;
    private PersistentaIncaltaminte persistentaIncaltaminte;

    public CmdNume(ViewModel.VMAngajat vm, PersistentaIncaltaminte pe) {
        this.persistentaIncaltaminte = pe;
        this.vmAngajat = vm;

    }

    @Override
    public void Execute() {
        String nume = vmAngajat.getNume().get();
        List<IncaltaminteMagazin> afis = new ArrayList<>();
        if (nume.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campul de nume este gol!");
        } else {
            List<IncaltaminteMagazin> list = persistentaIncaltaminte.getIncaltaminteMagazins();

            for (IncaltaminteMagazin i : list)
            {
                for (Incaltaminte ii : i.getIncaltamintes())
                {
                    if(ii.getNume().equals(nume))
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
