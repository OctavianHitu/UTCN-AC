package ViewModel.CommandLoginUser;

import Model.PersistentaUser;
import Model.User;
import View.AdministratorView;
import View.AngajatView;
import ViewModel.VMLogin;
import ViewModel.ICommand;

import javax.swing.*;

public class CmdLoginUser implements ICommand{

    private VMLogin vmLogin;
    private PersistentaUser persistentaUser;

    public CmdLoginUser(VMLogin vmLogin)
    {
        this.vmLogin=vmLogin;
        this.persistentaUser= new PersistentaUser();
    }
    @Override
    public void Execute()
    {
        String username,password;
        username =vmLogin.getUsername().get();
        password =vmLogin.getPassword().get();
        User user = persistentaUser.getuserl(username);

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
