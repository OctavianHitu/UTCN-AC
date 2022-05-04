package View;



import ViewModel.VMAdministrator;
import ViewModel.VMAngajat;
import lombok.Getter;
import lombok.Setter;
import net.sds.mvvm.bindings.Bind;
import net.sds.mvvm.bindings.Binder;
import net.sds.mvvm.bindings.BindingException;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

@Getter
@Setter
public class AngajatView extends JFrame {
    private JPanel mainAngajatPanel;


    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton afisareButton;
    private JButton searchButton;

    private JTextPane afisincaltaminte;


    private JLabel exvandute;
    private JButton magazin;
    private JButton filtrpretbut;
    private JButton filtrareProducatorButton;
    private JButton filtrareDisponibilitateButton;
    private JButton salvareButton;
    private JButton statisticiButton;

    @Bind(value = "text", target = "producator.value")
    private JTextField producatorcrudtf;
    @Bind(value = "text", target = "nume.value")
    private JTextField numecrudtf;
    @Bind(value = "text", target = "pret.value")
    private JTextField pretcrudtf;
    @Bind(value = "text", target = "marime.value")
    private JTextField marimecrudtf;
    @Bind(value = "text", target = "numemagazin.value")
    private JTextField magazincrudtf;
    @Bind(value = "text", target = "numarexemplareinitiale.value")
    private JTextField exinitialecrudtf;
    @Bind(value = "text", target = "numarexemplarevandute.value")
    private JTextField exvandutecrudtf;


    @Bind(value = "text", target = "producatoru.value")
    private JTextField producatorsrctf;
    @Bind(value = "text", target = "numeu.value")
    private JTextField numesrctf;
    @Bind(value = "text", target = "pretu.value")
    private JTextField pretsrctf;
    @Bind(value = "text", target = "marimeu.value")
    private JTextField marimesrctf;
    @Bind(value = "text", target = "numemagazinu.value")
    private JTextField magazinsrctf;
    @Bind(value = "text", target = "numarexemplareinitialeu.value")
    private JTextField exinitialesrctf;
    @Bind(value = "text", target = "numarexemplarevanduteu.value")
    private JTextField exvandutesrctf;

    private VMAngajat vmAngajat;

    public AngajatView() {
        this.setTitle("Angajat");
        this.setSize(1500,800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(mainAngajatPanel);
        this.setVisible(true);

        this.vmAngajat= new VMAngajat();
        try {
            Binder.bind(this,vmAngajat);
        }catch (BindingException b)
        {
            System.out.println("Eroare la Angajat View!");
        }

        afisareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vmAngajat.afisarecommand.Execute();
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
                    ex.printStackTrace();
                }
                afisincaltaminte.setText(vmAngajat.getData());
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    vmAngajat.addcommand.Execute();
                    vmAngajat.afisarecommand.Execute();
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
                    ex.printStackTrace();
                }
                afisincaltaminte.setText(vmAngajat.getData());
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("am ajuns in action delete");
                    vmAngajat.deletecommand.Execute();
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
                    ex.printStackTrace();
                }
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vmAngajat.updatecommand.Execute();
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
                    ex.printStackTrace();
                }
            }
        });

        magazin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vmAngajat.magazincommand.Execute();

                } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
                    ex.printStackTrace();
                }
                afisincaltaminte.setText(vmAngajat.getData());
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vmAngajat.numecommand.Execute();
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
                    ex.printStackTrace();
                }
                afisincaltaminte.setText(vmAngajat.getData());
            }
        });

        filtrareProducatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vmAngajat.producatorcommand.Execute();
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
                    ex.printStackTrace();
                }
                afisincaltaminte.setText(vmAngajat.getData());
            }
        });

        filtrareDisponibilitateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vmAngajat.disponibilitatecommand.Execute();
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
                    ex.printStackTrace();
                }
                afisincaltaminte.setText(vmAngajat.getData());
            }

        });

        filtrpretbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vmAngajat.pretcommand.Execute();
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
                    ex.printStackTrace();
                }
                afisincaltaminte.setText(vmAngajat.getData());
            }
        });

        salvareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vmAngajat.savecommand.Execute();
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
                    ex.printStackTrace();
                }
            }
        });

        statisticiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vmAngajat.chartcommand.Execute();
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
                    ex.printStackTrace();

                }
            }
        });

    }

}
