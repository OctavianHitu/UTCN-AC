package Presenter;

import View.AdministratorView;
import View.AngajatView;
import View.LoginUser;
import Model.User;
import Model.PersistentaUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PresenterUser {
    private PersistentaUser persistentaUser;
    private  LoginUser loginUser;

    public PresenterUser(LoginUser loginUser)
    {
        this.loginUser=loginUser;
        this.persistentaUser=new PersistentaUser();
    }


    public void actionListenerlogin()
    {
        String username,password;
        username =this.loginUser.getusername();
        password =this.loginUser.getpassword();
        User user = persistentaUser.getuser(username);

        if(user!=null)
        {
            if(user.getPassword().equals(password))
            {
                if(user.getRol().equals("administrator"))
                {
                    new AdministratorView();
                }else
                {
                    new AngajatView();
                }
                this.loginUser.setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Password incorrect");

            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Username doesn't exist");
        }
    }
}
