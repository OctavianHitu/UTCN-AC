package Model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {

    private String username;
    private String password;
    public String rol;

    public User(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }



}
