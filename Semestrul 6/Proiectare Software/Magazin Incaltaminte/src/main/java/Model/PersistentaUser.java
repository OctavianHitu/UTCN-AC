package Model;

import javax.xml.transform.TransformerException;
import java.util.ArrayList;
import java.util.List;

public class PersistentaUser extends UserXml {

    private List<User> userlist;



    public PersistentaUser(List<User> userlist) {
        this.userlist = userlist;

    }
    public PersistentaUser( )
    {
        this.userlist= new ArrayList<>();
        this.userlist= this.readfromxml();
        System.out.println(this.userlist);

    }

    public List<User> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<User> userlist) {
        this.userlist = userlist;
    }

    public User getuser(String username)
    {
        for(User user:userlist)
        {
            if(user.getUsername().equals(username))
            {
                return user;
            }
        }
        return null;
    }

    public boolean existUser(User user){
        boolean exist = false;
        for (User usr:userlist) {
            if (usr.getUsername().equals(user.getUsername())){
                exist=true;
            }
        }
        return exist;
    }

    public boolean adduser(User user)  {
        if(this.existUser(user))
        {
            return false;
        }
        else {
            this.userlist.add(user);
            this.addusertodoc(user);
            return true;
        }
    }

    public boolean removeuser(User user)  {
        boolean ex=false;
        int i=-1;
        for(User u:userlist)
        {
            if((u.getUsername().equals(user.getUsername()))&&(u.getPassword().equals(user.getPassword())))
            {
                ex=true;
                i=userlist.indexOf(u);
            }
        }
        if(ex){
            this.userlist.remove(i);
            this.deleteuserxml(user);
            return true;
        }else {
            return false;
        }
    }

    public boolean updateuser(User oldu, User newu)  {
        if(this.removeuser(oldu))
        {
            this.adduser(newu);
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String toString() {
        String str  ="";
        for (User usr: userlist ) {
            str  += usr.toString();
        }
    return str;
    }
}
