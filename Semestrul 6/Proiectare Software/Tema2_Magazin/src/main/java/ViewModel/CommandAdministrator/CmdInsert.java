package ViewModel.CommandAdministrator;

import Model.BuilderUser;
import Model.PersistentaUser;
import Model.User;
import ViewModel.ICommand;
import ViewModel.VMAdministrator;

import javax.swing.*;

public class CmdInsert implements ICommand {

    private VMAdministrator vmAdministrator;
    private PersistentaUser persistentaUser;

    public CmdInsert(VMAdministrator vm,PersistentaUser pu)
    {
        this.persistentaUser=pu;
        this.vmAdministrator=vm;
    }
    @Override
    public void Execute()
    {
        String username= vmAdministrator.getUsername().get();
        String password= vmAdministrator.getPassword().get();
        String rol= vmAdministrator.getRol().get();

        if(username.isEmpty()|| password.isEmpty()||rol.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"O casuta nu e completata!");

        }
        else
        {
            User user= new BuilderUser().setusername(username).setpassword(password).setrol(rol).build();
            if(!persistentaUser.adduser(user))
            {
                JOptionPane.showMessageDialog(null,"Userul exista deja!");

            }
        }
    }

}
