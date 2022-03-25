package model;

public class Client {

    private int id;
    private String lastname;
    private String firstname;
    private String address;


    public Client(int id, String ln, String fn, String adr) {
        this.id = id;
        this.firstname = fn;
        this.lastname = ln;
        this.address = adr;
    }

    public  Client()
    {

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
