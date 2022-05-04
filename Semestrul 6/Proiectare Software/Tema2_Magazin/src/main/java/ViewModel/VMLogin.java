package ViewModel;

import lombok.Getter;
import lombok.Setter;
import ViewModel.CommandLoginUser.CmdLoginUser;
import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;


@Getter
@Setter
public class VMLogin {

private Property<String> username= PropertyFactory.createProperty("usernametf",this,String.class);
private Property<String> password= PropertyFactory.createProperty("passwordtf",this,String.class);
public ICommand userlogincommand;


public VMLogin()
{
    this.userlogincommand= new CmdLoginUser(this);
}


}
