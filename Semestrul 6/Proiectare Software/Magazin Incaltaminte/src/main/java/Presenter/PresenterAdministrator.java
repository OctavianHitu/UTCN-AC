package Presenter;

import Model.PersistentaUser;
import Model.User;
import View.AdministratorView;

import javax.swing.*;
import javax.xml.transform.TransformerException;

public class PresenterAdministrator {
    private AdministratorView adminview;
    private PersistentaUser persistentaUser;

    public PresenterAdministrator(AdministratorView v)
    {
        this.adminview=v;
        this.persistentaUser= new PersistentaUser();
    }

    public void afisangajati()
    {
        adminview.afisareusers(persistentaUser.toString());
    }

    public void createuser()  {
        String username= adminview.getUsernamecrudtf();
        String password= adminview.getPasswordcrudtf();
        String rol= adminview.getRolecrudtf();

        if(username.isEmpty()|| password.isEmpty()||rol.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"O casuta nu e completata!");

        }
        else
        {
            User user= new User(username,password,rol);
            if(!persistentaUser.adduser(user))
            {
                JOptionPane.showMessageDialog(null,"Userul exista deja!");

            }
        }
        afisangajati();
    }

    public void deleteuser() {
        String username= adminview.getUsernamecrudtf();
        if(username.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Casuta de username este goala!");

        }
        else
        {
            if(username=="octavian")
            {
                JOptionPane.showMessageDialog(null,"Nu poti sterge CEO-ul!");
            }
            User user= persistentaUser.getuser(username);
            if(user==null)
            {
                JOptionPane.showMessageDialog(null,"Nu exista userul!");
            }
            else
            {
                persistentaUser.removeuser(user);
            }

        }
        afisangajati();
    }

    public void updateuser() {
        String username= adminview.getUsernamecrudtf();
        if (username.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Casuta de username este goala!");

        }
        else
        {
            if(persistentaUser.getuser(username)==null)
            {
                JOptionPane.showMessageDialog(null,"Nu exista userul!");
            }
            else
            {
                String newpassword= adminview.getPasswordcrudtf();
                String newrol= adminview.getRolecrudtf();

                if(newpassword.isEmpty())
                {
                    newpassword=persistentaUser.getuser(username).getPassword();

                }
                if (newrol.isEmpty())
                {
                    newrol=persistentaUser.getuser(username).getRol();
                }
                User newuser= new User(username,newpassword,newrol);
                persistentaUser.updateuser(persistentaUser.getuser(username),newuser);
            }
        }
        afisangajati();
    }

}
