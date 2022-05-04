package Model;

public class BuilderUser {

    private String username;
    private String password;
    private  String rol;

    public BuilderUser()
    {
        this.username="";
        this.password="";
        this.rol="";
    }

    public BuilderUser setusername(String username)
    {
        this.username=username;
        return this;
    }

    public BuilderUser setpassword(String password)
    {
        this.password=password;
        return this;
    }
    public BuilderUser setrol(String rol)
    {
        this.rol=rol;
        return this;
    }

    public User build()
    {
        return new User(username,password,rol);
    }

}
