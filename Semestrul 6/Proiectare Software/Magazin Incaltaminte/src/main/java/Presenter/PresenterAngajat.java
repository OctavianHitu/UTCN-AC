package Presenter;

import Model.IncaltainteMagazin;
import Model.Incaltaminte;
import Model.IncaltaminteDisponibila;
import Model.IncaltamintePersistenta;
import View.AdministratorView;
import View.AngajatView;
import View.FiltruPret;

import javax.swing.*;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PresenterAngajat {

    private AngajatView angajatView;
    private IncaltamintePersistenta ipers;



    public PresenterAngajat(AngajatView v) {
        this.angajatView = v;
        this.ipers = new IncaltamintePersistenta();

    }

    public void afisarepapuci() {
        angajatView.afisareIncaltaminte(ipers.toString());
    }

    public void createincaltaminte() {
        String numemagazin = angajatView.getMagazincrudtf();
        String nume = angajatView.getNumecrudtf();
        String producator = angajatView.getProducatorcrudtf();
        String pret = angajatView.getPretcrudtf();
        String marime = angajatView.getMarimecrudtf();
        String exvandute = angajatView.getExvandutecrudtf();
        String exinitiale = angajatView.getExinitialecrudtf();

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
                                    IncaltaminteDisponibila inc = new IncaltaminteDisponibila(Integer.parseInt(marime), Integer.parseInt(exinitiale), Integer.parseInt(exvandute));
                                    Incaltaminte incaltaminte = new Incaltaminte(nume, producator, Integer.parseInt(pret), inc);
                                    IncaltainteMagazin magazin = new IncaltainteMagazin(numemagazin);

                                    magazin.addincaltaminteinmagazin(incaltaminte);
                                    System.out.println(magazin.toString());

                                    if (!ipers.addIncaltaminte(magazin)) {
                                        JOptionPane.showMessageDialog(null, "Incaltamintea exista deja!");
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        afisarepapuci();
    }

    public void deleteincaltaminte() {
        String numemagazin = angajatView.getMagazincrudtf();
        String nume = angajatView.getNumecrudtf();
        String producator = angajatView.getProducatorcrudtf();
        String marime = angajatView.getMarimecrudtf();

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
                        IncaltainteMagazin incaltainteMagazin = IncaltamintePersistenta.getincaltaminte(numemagazin, nume, producator, Integer.parseInt(marime));
                        if (incaltainteMagazin != null) {
                            IncaltamintePersistenta.deleteincaltaminte(incaltainteMagazin);
                        } else {
                            JOptionPane.showMessageDialog(null, "A aparut o eraore la stergere!");
                        }


                    }
                }
            }
        }

        afisarepapuci();
    }

    public void updateincaltaminte() {

        String numemagazin = angajatView.getMagazincrudtf();
        String nume = angajatView.getNumecrudtf();
        String producator = angajatView.getProducatorcrudtf();
        String marime = angajatView.getMarimecrudtf();

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


                        IncaltainteMagazin incaltainteMagazin = IncaltamintePersistenta.getincaltaminte(numemagazin, nume, producator, Integer.parseInt(marime));

                        String newnumemagazin = angajatView.getMagazinsrctf();
                        String newnume = angajatView.getNumesrctf();
                        String newproducator = angajatView.getProducatorsrctf();
                        String newpret = angajatView.getPretsrctf();
                        String newmarime = angajatView.getMarimesrctf();
                        String newexvandute = angajatView.getExvandutesrctf();
                        String newexinitiale = angajatView.getExinitialesrctf();


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
                            newpret = incaltainteMagazin.getIncaltamintes().get(0).getPret().toString();

                        }

                        if (newexinitiale.isEmpty()) {
                            newexinitiale = incaltainteMagazin.getIncaltamintes().get(0).getIncaltaminteDisponibila().getNumarexemplareinitiale().toString();
                        }

                        if (newexvandute.isEmpty()) {
                            newexvandute = incaltainteMagazin.getIncaltamintes().get(0).getIncaltaminteDisponibila().getGetNumarexemplarevandute().toString();
                        }


                        IncaltaminteDisponibila inc = new IncaltaminteDisponibila(Integer.parseInt(newmarime), Integer.parseInt(newexinitiale), Integer.parseInt(newexvandute));
                        Incaltaminte incaltaminte = new Incaltaminte(newnume, newproducator, Integer.parseInt(newpret), inc);
                        IncaltainteMagazin magazin = new IncaltainteMagazin(newnumemagazin);
                        magazin.addincaltaminteinmagazin(incaltaminte);

                        IncaltamintePersistenta.updateincaltamintep(incaltainteMagazin, magazin);

                    }
                }
            }

        }
        afisarepapuci();
    }

    public void afisaremagazin()
    {
        String numegazin=angajatView.getMagazincrudtf();

        if(numegazin.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Campul de nume magazin este gol!");

        }
        else
        {
            List<IncaltainteMagazin> incaltainteMagazins= new ArrayList<>();
            incaltainteMagazins=ipers.getIncaltainteMagazins().stream().filter(incaltainteMagazin -> incaltainteMagazin.getNumemagazin().equals(numegazin)).collect(Collectors.toList());
            angajatView.afisareIncaltaminte(incaltainteMagazins.toString());


        }
    }


    public void filtrateproducator()
    {
       String producator= angajatView.getProducatorcrudtf();

        List<IncaltainteMagazin> incaltainteMagazins= IncaltamintePersistenta.getIncaltamintelist();
        List<IncaltainteMagazin> afis= new ArrayList<>();

        if(producator.isEmpty()||producator=="producator")
        {
            JOptionPane.showMessageDialog(null,"Introduceti un nume valid de Producator!");
        }
        else {
            for (IncaltainteMagazin in : incaltainteMagazins) {
                for (Incaltaminte inc : in.getIncaltamintes()) {
                    if (inc.getProducator().equals(producator)) {
                        IncaltainteMagazin im = new IncaltainteMagazin(in.getNumemagazin());
                        im.addincaltaminteinmagazin(inc);


                        afis.add(im);

                    }
                }
            }
            angajatView.afisareIncaltaminte(afis.toString());
        }

    }

    public void cautarenume()
    {
        String nume= angajatView.getNumecrudtf();

        List<IncaltainteMagazin> incaltainteMagazins= IncaltamintePersistenta.getIncaltamintelist();
        List<IncaltainteMagazin> afis= new ArrayList<>();

        if(nume.isEmpty()||nume=="nume")
        {
            JOptionPane.showMessageDialog(null,"Introduceti un nume valid!");
        }
        else {
            for (IncaltainteMagazin in : incaltainteMagazins) {
                for (Incaltaminte inc : in.getIncaltamintes()) {
                    if (inc.getNume().equals(nume)) {

                        IncaltainteMagazin im = new IncaltainteMagazin(in.getNumemagazin());
                        im.addincaltaminteinmagazin(inc);

                        afis.add(im);

                    }
                }
            }
            angajatView.afisareIncaltaminte(afis.toString());
        }

    }

    public void filtraredisponibilitate()
    {
        List<IncaltainteMagazin> incaltainteMagazins= IncaltamintePersistenta.getIncaltamintelist();
        List<IncaltainteMagazin> afis= new ArrayList<>();
        for (IncaltainteMagazin in: incaltainteMagazins)
        {
            for(Incaltaminte inc:in.getIncaltamintes())
            {
                Integer init= inc.getIncaltaminteDisponibila().getNumarexemplareinitiale();
                Integer vandut= inc.getIncaltaminteDisponibila().getGetNumarexemplarevandute();
                if(init-vandut!=0)
                {

                    IncaltainteMagazin im = new IncaltainteMagazin(in.getNumemagazin());
                    im.addincaltaminteinmagazin(inc);
                    afis.add(im);
                }

            }
        }

        angajatView.afisareIncaltaminte(afis.toString());


    }

    public  void saveothfiles() throws IOException {
        ipers.savejson(ipers.getIncaltainteMagazins());
        ipers.savecsv(ipers.getIncaltainteMagazins());
    }

    public void filtrarepret()
    {
        new FiltruPret();

    }
    




}
