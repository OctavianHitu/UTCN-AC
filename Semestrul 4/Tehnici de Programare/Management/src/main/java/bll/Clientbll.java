package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import dao.AbstarctDao;
import dao.ClientDao;
import model.Client;

public class Clientbll {

    private ClientDao clientdao;

    public Clientbll()
    {
        clientdao=new ClientDao();
    }

    public List<Client> selectq()
    {
        return clientdao.findAll();
    }

    public Client findById(int id)
    {
        Client clnt= clientdao.findById(id);
        if(clnt == null)
        {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return clnt;
    }

    public void insert(Client c)
    {
         clientdao.insert(c);

    }

    public void update(Client c)
    {
        clientdao.update(c);

    }

    public void delete(Client c)
    {
        clientdao.delete(c);
    }

    public Object[][] gettable2(){ return clientdao.gettable(selectq());}

    public Object[] getheader (){return clientdao.getheaderoftable();}


}
