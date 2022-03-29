package View;

import Presenter.PresenterUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class LoginUser extends JFrame implements LoginuserI {
    private JPanel mainPanel;
    private JTextField usernametf;
    private JTextField passwordtf;
    private JButton loginButton;

    public LoginUser() {
        this.setTitle("Login");
        this.setSize(400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(mainPanel);
        this.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PresenterUser presenterUser = new PresenterUser(LoginUser.this);
                presenterUser.actionListenerlogin();
            }
        });
    }



    public String getusername()
    {
        return this.usernametf.getText();
    }


   public String getpassword()
    {
        return this.passwordtf.getText();
    }




}
