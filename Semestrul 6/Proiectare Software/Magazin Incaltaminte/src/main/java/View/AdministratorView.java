package View;

import Presenter.PresenterAdministrator;

import javax.swing.*;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministratorView extends  JFrame implements AdministratorViewI{
    private JPanel mainAdministratorPanel;
    private JButton savebutton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton Afisare;
    private JTextField usernamecrudtf;
    private JTextField passwordcrudtf;
    private JTextField rolecrudtf;
    private JTextPane afistf;


    public AdministratorView()  {
        this.setTitle("Administrator");
        this.setSize(1000,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(mainAdministratorPanel);
        this.setVisible(true);

        PresenterAdministrator presenterAdministrator= new PresenterAdministrator(this);

        savebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    presenterAdministrator.createuser();

            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    presenterAdministrator.updateuser();

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    presenterAdministrator.deleteuser();

            }
        });

        Afisare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenterAdministrator.afisangajati();
            }
        });


    }


    @Override
    public void afisareusers(String a) {
        this.afistf.setText(a);
    }

    @Override
    public String getUsernamecrudtf() {
        return this.usernamecrudtf.getText();
    }


    @Override
    public String getPasswordcrudtf() {
        return this.passwordcrudtf.getText();
    }



    @Override
    public String getRolecrudtf() {
        return this.rolecrudtf.getText();
    }


}
