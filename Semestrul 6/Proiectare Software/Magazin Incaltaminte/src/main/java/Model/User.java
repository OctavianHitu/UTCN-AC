package Model;

public class User {

    private String username;
    private String password;
    public String rol;

    public User(){}

    public User(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "User(" +
                "username:'" + username + '\'' +
                ", password:'" + password + '\'' +
                ",rol:'" + rol + '\'' +
                ')'+'\n';
    }
}
