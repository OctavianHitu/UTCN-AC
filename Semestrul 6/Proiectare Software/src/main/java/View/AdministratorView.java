package View;


import ViewModel.VMAdministrator;
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
public class AdministratorView extends  JFrame {
    private JPanel mainAdministratorPanel;
    private JButton savebutton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton Afisare;
    private JTextPane afistf;

    @Bind(value = "text", target = "username.value")
    private JTextField usernamecrudtf;
    @Bind(value = "text", target = "password.value")
    private JTextField passwordcrudtf;
    @Bind(value = "text", target = "rol.value")
    private JTextField rolecrudtf;

    private JTable table;
    private final Object[] nume={"Username","Password","rol"};
    private VMAdministrator vmAdministrator;

    public AdministratorView()  {
        this.setTitle("Administrator");
        this.setSize(1000,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(mainAdministratorPanel);
        this.setVisible(true);

        this.vmAdministrator= new VMAdministrator();
        try {
            Binder.bind(this,vmAdministrator);
        }catch (BindingException b)
        {
            System.out.println("Eroare la Administrator View!");
        }

        savebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vmAdministrator.insertcommand.Execute();
                    vmAdministrator.afisarecommand.Execute();
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
                    ex.printStackTrace();
                }

                afistf.setText(vmAdministrator.getData());
            }
        });

        Afisare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    vmAdministrator.afisarecommand.Execute();
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
                    ex.printStackTrace();
                }
                afistf.setText(vmAdministrator.getData());

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vmAdministrator.deletecommand.Execute();
                    vmAdministrator.afisarecommand.Execute();
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
                    ex.printStackTrace();
                }

                afistf.setText(vmAdministrator.getData());
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vmAdministrator.updatecommand.Execute();
                    vmAdministrator.afisarecommand.Execute();
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
                    ex.printStackTrace();
                }

                afistf.setText(vmAdministrator.getData());
            }
        });


    }


}
