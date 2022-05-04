package ViewModel.CommandAngajat;

import Model.Incaltaminte;
import Model.IncaltaminteMagazin;
import Model.PersistentaIncaltaminte;
import ViewModel.ICommand;
import ViewModel.VMAngajat;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CmdDisponibilitate implements ICommand {

    private VMAngajat vmAngajat;
    private PersistentaIncaltaminte persistentaIncaltaminte;

    public CmdDisponibilitate(ViewModel.VMAngajat vm, PersistentaIncaltaminte pe) {
        this.persistentaIncaltaminte = pe;
        this.vmAngajat = vm;
    }


    @Override
    public void Execute() {

        List<IncaltaminteMagazin> afis = new ArrayList<>();
            List<IncaltaminteMagazin> list = persistentaIncaltaminte.getIncaltaminteMagazins();

            for (IncaltaminteMagazin i : list) {
                for (Incaltaminte ii : i.getIncaltamintes()) {

                    Integer init = ii.getIncaltaminteDisponibila().getNumarexemplareinitiale();
                    Integer vandut = ii.getIncaltaminteDisponibila().getNumarexemplarevandute();


                    if (init-vandut>0) {
                        IncaltaminteMagazin im = new IncaltaminteMagazin(i.getNumemagazin());
                        im.addincaltaminteinmagazin(ii);

                        afis.add(im);
                    }
                }
            }

            vmAngajat.setData(afis.toString());

    }
}
