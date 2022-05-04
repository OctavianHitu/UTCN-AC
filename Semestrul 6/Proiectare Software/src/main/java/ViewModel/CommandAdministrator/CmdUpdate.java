package ViewModel.CommandAdministrator;

import Model.BuilderUser;
import Model.PersistentaUser;
import Model.User;
import ViewModel.ICommand;
import ViewModel.VMAdministrator;

import javax.swing.*;

public class CmdUpdate implements ICommand {

    private VMAdministrator vmAdministrator;
    private PersistentaUser persistentaUser;


    public CmdUpdate(VMAdministrator vm, PersistentaUser pu)
    {
        this.vmAdministrator=vm;
        this.persistentaUser=pu;
    }

    @Override
    public void Execute()
    {
        String username= vmAdministrator.getUsername().get();

        if (username.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Casuta de username este goala!");

        }
        else
        {
            User oldu = persistentaUser.getuserl(username);
            if(oldu==null)
            {
                JOptionPane.showMessageDialog(null,"Nu exista userul!");
            }
            else
            {
                String newpassword= vmAdministrator.getPassword().get();
                String newrol= vmAdministrator.getRol().get();

                if(newpassword.isEmpty())
                {
                    newpassword=oldu.getPassword();

                }
                if (newrol.isEmpty())
                {
                    newrol=oldu.getRol();
                }
                User newuser= new BuilderUser().setusername(username).setpassword(newpassword).setrol(newrol).build();
                persistentaUser.updateuser(oldu,newuser);
            }
        }
    }


}
