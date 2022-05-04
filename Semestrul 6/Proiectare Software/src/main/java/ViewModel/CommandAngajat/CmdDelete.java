package ViewModel.CommandAngajat;

import Model.IncaltaminteMagazin;
import Model.PersistentaIncaltaminte;
import Model.PersistentaUser;
import ViewModel.ICommand;
import ViewModel.VMAngajat;

import javax.swing.*;

public class CmdDelete implements ICommand {

    private VMAngajat vmAngajat;
    private PersistentaIncaltaminte persistentaIncaltaminte;


    public CmdDelete(VMAngajat vm, PersistentaIncaltaminte pe)
    {
        this.persistentaIncaltaminte=pe;
        this.vmAngajat=vm;

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

                        if(inc==null)
                        {
                            JOptionPane.showMessageDialog(null,"Nu exista incaltamintea!");
                        }
                         else
                        {
                            persistentaIncaltaminte.removeincaltaminte(inc);
                        }
                        }


                    }
                }
            }
        }

    }



