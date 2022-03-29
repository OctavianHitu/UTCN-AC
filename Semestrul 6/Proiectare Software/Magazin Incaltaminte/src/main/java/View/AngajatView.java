package View;

import Presenter.PresenterAngajat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AngajatView extends JFrame implements  AngajatViewI{
    private JPanel mainAngajatPanel;
    private JTextField producatorcrudtf;
    private JTextField producatorsrctf;
    private JTextField numecrudtf;
    private JTextField numesrctf;
    private JTextField pretcrudtf;
    private JTextField pretsrctf;
    private JTextField marimecrudtf;
    private JTextField marimesrctf;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton afisareButton;
    private JButton searchButton;
    private JTextField magazincrudtf;
    private JTextField magazinsrctf;
    private JTextArea afisincaltaminte;
    private JTextField exinitialecrudtf;
    private JTextField exinitialesrctf;
    private JTextField exvandutecrudtf;
    private JTextField exvandutesrctf;
    private JLabel exvandute;
    private JButton magazin;
    private JButton filtrpretbut;
    private JButton filtrareProducatorButton;
    private JButton filtrareDisponibilitateButton;
    private JButton salvareButton;


    public AngajatView() {
        this.setTitle("Angajat");
        this.setSize(1500,800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(mainAngajatPanel);
        this.setVisible(true);

        PresenterAngajat presenterAngajat= new PresenterAngajat(this);

        afisareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenterAngajat.afisarepapuci();
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    presenterAngajat.createincaltaminte();

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenterAngajat.deleteincaltaminte();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenterAngajat.updateincaltaminte();
            }
        });

        magazin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenterAngajat.afisaremagazin();


            }
        });
        filtrpretbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenterAngajat.filtrarepret();
            }
        });

        filtrareDisponibilitateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenterAngajat.filtraredisponibilitate();
            }
        });

        filtrareProducatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenterAngajat.filtrateproducator();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenterAngajat.cautarenume();
            }
        });

        salvareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    presenterAngajat.saveothfiles();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
    @Override
    public void afisareIncaltaminte(String af)
    {
        this.afisincaltaminte.setText(af);
    }

    @Override
    public String  getProducatorcrudtf() {
        return this.producatorcrudtf.getText();
    }

    public String getProducatorsrctf() {
        return this.producatorsrctf.getText();
    }

    @Override
    public String getNumecrudtf() {
        return this.numecrudtf.getText();
    }
    @Override
    public String getNumesrctf() {
        return this.numesrctf.getText();
    }

    @Override
    public String getPretcrudtf() {
        return this.pretcrudtf.getText();
    }
    @Override
    public String getPretsrctf() {
        return this.pretsrctf.getText();
    }

    @Override
    public String getMarimecrudtf() {
        return this.marimecrudtf.getText();
    }
    @Override
    public String getMarimesrctf() {
        return this.marimesrctf.getText();
    }

    public String getMagazincrudtf() {
        return this.magazincrudtf.getText();
    }
    @Override
    public String getMagazinsrctf() {
        return this.magazinsrctf.getText();
    }
    @Override
    public String getExinitialecrudtf() {
        return this.exinitialecrudtf.getText();
    }
    @Override
    public String getExinitialesrctf() {
        return this.exinitialesrctf.getText();
    }

    @Override
    public String getExvandutecrudtf() {
        return this.exvandutecrudtf.getText();
    }
    @Override
    public String getExvandutesrctf() {
        return this.exvandutesrctf.getText();
    }
}
