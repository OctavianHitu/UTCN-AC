package ViewModel.CommandAdministrator;

import Model.PersistentaUser;
import Model.User;
import ViewModel.ICommand;
import ViewModel.VMAdministrator;

import javax.swing.*;

public class CmdDelete implements ICommand {

    private VMAdministrator vmAdministrator;
    private PersistentaUser persistentaUser;


    public CmdDelete(VMAdministrator vm, PersistentaUser pu)
    {
        this.vmAdministrator=vm;
        this.persistentaUser=pu;
    }


    @Override
    public void Execute()
    {
        String username= vmAdministrator.getUsername().get();

        if(username.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Casuta de username este goala!");

        }
        else
        {
            User user= persistentaUser.getuserl(username);
            if(user==null)
            {
                JOptionPane.showMessageDialog(null,"Nu exista userul!");
            }
            else
            {
                System.out.println("Am Ajuns Aici");
                System.out.println(user);
                persistentaUser.removeuser(user);
            }

        }

    }

}
