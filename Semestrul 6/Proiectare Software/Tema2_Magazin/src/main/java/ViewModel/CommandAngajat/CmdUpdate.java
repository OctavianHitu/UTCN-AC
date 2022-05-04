package ViewModel.CommandAngajat;

import Model.*;
import ViewModel.ICommand;
import ViewModel.VMAdministrator;
import ViewModel.VMAngajat;

import javax.swing.*;

public class CmdUpdate implements ICommand {

    private VMAngajat vmAngajat;
    private PersistentaIncaltaminte persistentaIncaltaminte;


    public CmdUpdate(VMAngajat vm, PersistentaIncaltaminte pu)
    {
        this.vmAngajat= vm;
        this.persistentaIncaltaminte =pu;
    }

    @Override
    public void Execute()
    {
        String numemagazin= vmAngajat.getNumemagazin().get();
        String nume = vmAngajat.getNume().get();
        String producator= vmAngajat.getProducator().get();
        String marime = vmAngajat.getMarime().get();

        if (numemagazin.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campul de nume magazin e gol!");

        } else {
            if (nume.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campul de nume  e gol!");
            } else {
                if (producator.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Campul de producator e gol!");
                } else {
                    if (marime.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campul de marime e gol!");
                    } else {

                        IncaltaminteMagazin inc= persistentaIncaltaminte.getincaltaminte(numemagazin,nume,producator,Integer.parseInt(marime));

                        String newnumemagazin= vmAngajat.getNumemagazinu().get();
                        String newnume= vmAngajat.getNumeu().get();
                        String newproducator= vmAngajat.getProducatoru().get();
                        String newpret= vmAngajat.getPretu().get();
                        String newmarime= vmAngajat.getMarimeu().get();
                        String newexinitiale= vmAngajat.getNumarexemplareinitialeu().get();
                        String newexvandute= vmAngajat.getNumarexemplarevanduteu().get();

                        if (newnumemagazin.isEmpty()) {
                            newnumemagazin = numemagazin;

                        }
                        if (newnume.isEmpty()) {
                            newnume = nume;
                        }

                        if (newproducator.isEmpty()) {
                            newproducator = producator;
                        }

                        if (newmarime.isEmpty()) {
                            newmarime = marime;
                        }

                        if (newpret.isEmpty()) {
                            newpret = inc.getIncaltamintes().get(0).getPret().toString();

                        }

                        if (newexinitiale.isEmpty()) {
                            newexinitiale = inc.getIncaltamintes().get(0).getIncaltaminteDisponibila().getNumarexemplareinitiale().toString();
                        }

                        if (newexvandute.isEmpty()) {
                           newexvandute = inc.getIncaltamintes().get(0).getIncaltaminteDisponibila().getNumarexemplarevandute().toString();
                        }

                        //IncaltaminteDisponibila i= new IncaltaminteDisponibila(Integer.parseInt(newmarime),Integer.parseInt(newexinitiale),Integer.parseInt(newexvandute));
                        IncaltaminteDisponibila i= new BuilderIncaltaminteDisp().setmarimeb(Integer.parseInt(newmarime)).setNumarexemplareinitialeb(Integer.parseInt(newexinitiale)).setNumarexemplarevanduteb(Integer.parseInt(newexvandute)).build();
                        Incaltaminte ii= new Incaltaminte(newnume,newproducator,Integer.parseInt(newpret),i);
                        IncaltaminteMagazin iii = new IncaltaminteMagazin(newnumemagazin);
                        iii.addincaltaminteinmagazin(ii);

                        persistentaIncaltaminte.updateincaltaminte(inc,iii);

                    }


                }
            }
        }
    }


}
