package ViewModel.CommandAdministrator;

import Model.PersistentaUser;
import Model.User;
import ViewModel.ICommand;
import ViewModel.VMAdministrator;

import java.util.List;

public class CmdAfisare implements ICommand {

    private VMAdministrator vmAdministrator;
    private PersistentaUser persistentaUser;


    public CmdAfisare(VMAdministrator vm, PersistentaUser pu)
    {
        this.vmAdministrator=vm;
        this.persistentaUser=pu;
    }

    @Override
    public  void Execute()
    {
        List<User> userList= persistentaUser.getUserlist();
        String afis = new String();
        for (int i = 0; i<userList.size();i++) {
            afis += userList.get(i);
            afis += "\n";
        }
        vmAdministrator.setData(afis);
    }


}
