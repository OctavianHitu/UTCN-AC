package View;


import ViewModel.VMLogin;
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

public class LoginUser extends JFrame  {
    private JPanel mainPanel;
    private JButton loginButton;

    @Bind(value = "text",target = "username.value")
    private JTextField usernametf;

    @Bind(value = "text",target = "password.value")
    private JPasswordField passwordtf;

    private VMLogin vmLogin;

    public LoginUser() {
        this.setTitle("Login");
        this.setSize(400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(mainPanel);
        this.setVisible(true);

        this.vmLogin= new VMLogin();
        try {
            Binder.bind(this,vmLogin);
        }catch (BindingException b)
        {
            System.out.println("Eroare la login!");
        }

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vmLogin.getUserlogincommand().Execute();
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
                    ex.printStackTrace();
                }
            }
        });



    }






}
