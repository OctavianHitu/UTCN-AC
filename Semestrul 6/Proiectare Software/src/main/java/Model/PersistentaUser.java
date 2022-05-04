package Model;

import jdk.jfr.StackTrace;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PersistentaUser extends  UserDao  {

    private List<User> userlist;

    public PersistentaUser( )
    {
        this.userlist= new ArrayList<>();
        this.userlist=this.findallusers();

    }

    public User getuserl(String username)
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
            this.insertusersdao(user);
            return true;
        }
    }

    public void removeuser(User usr){
        this.deleteuserdao(usr);
        this.userlist=this.findallusers();

    }

    public void updateuser(User oldu, User newu)  {

       this.removeuser(oldu);
        this.adduser(newu);
        this.userlist=findallusers();
    }


}
