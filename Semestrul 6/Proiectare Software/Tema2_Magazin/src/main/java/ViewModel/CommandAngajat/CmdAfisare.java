package ViewModel.CommandAngajat;

import Model.Incaltaminte;
import Model.IncaltaminteMagazin;
import Model.PersistentaIncaltaminte;
import ViewModel.ICommand;
import ViewModel.VMAngajat;

import java.util.ArrayList;
import java.util.List;


public class CmdAfisare implements ICommand {

    private VMAngajat vmAngajat;
    private PersistentaIncaltaminte persistentaIncaltaminte;

    public CmdAfisare(VMAngajat vm, PersistentaIncaltaminte pr)
    {
        this.persistentaIncaltaminte=pr;
        this.vmAngajat=vm;
    }

    @Override
    public void Execute()
    {
        List<IncaltaminteMagazin> incaltaminteMagazins= persistentaIncaltaminte.getIncaltaminteMagazins();

        String afisare = new String();

        for(IncaltaminteMagazin ic:incaltaminteMagazins)
        {


                afisare+=ic;
                afisare+='\n';


        }

        vmAngajat.setData(afisare);

    }
}
