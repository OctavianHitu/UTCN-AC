package ViewModel;

import Model.PersistentaUser;
import ViewModel.CommandAdministrator.CmdAfisare;
import ViewModel.CommandAdministrator.CmdDelete;
import ViewModel.CommandAdministrator.CmdInsert;
import ViewModel.CommandAdministrator.CmdUpdate;
import jdk.jfr.StackTrace;
import lombok.Getter;
import lombok.Setter;
import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;

@Getter
@Setter
public class VMAdministrator {


    private Property<String> username = PropertyFactory.createProperty("usernamecrutf", this, String.class);
    private Property<String> password = PropertyFactory.createProperty("passwordcrudtf", this, String.class);
    private Property<String> rol = PropertyFactory.createProperty("rolecrudtf", this, String.class);




    private PersistentaUser persistentaUser;
    private String data =null;

    public ICommand insertcommand;
    public ICommand afisarecommand;
    public ICommand updatecommand;
    public ICommand deletecommand;

    public VMAdministrator()
    {
        this.persistentaUser= new PersistentaUser();
        this.insertcommand= new CmdInsert(this,persistentaUser);
        this.afisarecommand= new CmdAfisare(this,  persistentaUser);
        this.updatecommand= new CmdUpdate(this,persistentaUser);
        this.deletecommand= new CmdDelete(this,  persistentaUser);

    }


}
