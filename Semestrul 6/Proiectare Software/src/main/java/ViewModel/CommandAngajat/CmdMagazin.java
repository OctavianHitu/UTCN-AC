package ViewModel.CommandAngajat;

import Model.IncaltaminteMagazin;
import Model.PersistentaIncaltaminte;
import ViewModel.ICommand;
import ViewModel.VMAngajat;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CmdMagazin implements ICommand {

    private VMAngajat vmAngajat;
    private PersistentaIncaltaminte persistentaIncaltaminte;

    public CmdMagazin(VMAngajat vm, PersistentaIncaltaminte pe)
    {
        this.persistentaIncaltaminte=pe;
        this.vmAngajat=vm;
    }


    @Override
    public void Execute()
    {
        String numemagazin=vmAngajat.getNumemagazin().get();

        if(numemagazin.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Campul de nume magazin este gol!");
        }
        else
        {
            List<IncaltaminteMagazin> incaltaminteMagazins= new ArrayList<>();
               incaltaminteMagazins=     persistentaIncaltaminte.getIncaltaminteMagazins().stream().filter(im->im.getNumemagazin().equals(numemagazin)).collect(Collectors.toList());
            String afisare = new String();

            for(int i=0;i<incaltaminteMagazins.size();i++)
            {
                afisare+=incaltaminteMagazins.get(i);
                afisare+='\n';
            }

            
            vmAngajat.setData(afisare);
        }
    }
}
