package ViewModel.CommandAngajat;

import Model.*;
import ViewModel.ICommand;
import ViewModel.VMAngajat;

import javax.swing.*;

public class CmdAdd implements ICommand {

    private VMAngajat vmAngajat;
    private PersistentaIncaltaminte persistentaIncaltaminte;


    public CmdAdd(VMAngajat vm, PersistentaIncaltaminte pe)
    {
        this.persistentaIncaltaminte=pe;
        this.vmAngajat= vm;
    }

    @Override
    public void Execute()
    {
        String numemagazin= vmAngajat.getNumemagazin().get();
        String producator= vmAngajat.getProducator().get();
        String nume = vmAngajat.getNume().get();
        String marime = vmAngajat.getMarime().get();
        String pret= vmAngajat.getPret().get();
        String exinitiale= vmAngajat.getNumarexemplareinitiale().get();
        String exvandute = vmAngajat.getNumarexemplarevandute().get();

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
                        if (pret.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Campul de pret este null!");

                        } else {
                            if (exinitiale.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Campul de exemplare initiale e gol!");
                            } else {
                                if (exvandute.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Campul de exempalre vandute e gol!");
                                } else {

                                   // IncaltaminteDisponibila inc = new IncaltaminteDisponibila(Integer.parseInt(marime),Integer.parseInt(exinitiale),Integer.parseInt(exvandute));
                                    IncaltaminteDisponibila inc = new BuilderIncaltaminteDisp().setmarimeb(Integer.parseInt(marime)).setNumarexemplareinitialeb(Integer.parseInt(exinitiale)).setNumarexemplarevanduteb(Integer.parseInt(exvandute)).build();
                                    Incaltaminte i= new Incaltaminte(nume, producator, Integer.parseInt(pret),inc);
                                    IncaltaminteMagazin mag= new IncaltaminteMagazin(numemagazin);
                                    mag.addincaltaminteinmagazin(i);

                                    System.out.println(mag);
                                    if(!persistentaIncaltaminte.addincaltaminte(mag))
                                    {
                                        JOptionPane.showMessageDialog(null, "Nu poate fi inserat aceasta incaltaminte!");
                                    }

                                }
                            }
                        }

                    }
                }
            }
        }

    }
}
