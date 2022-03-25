package bll;

import java.util.List;
import java.util.NoSuchElementException;

import dao.ProductDao;
import model.Product;



public class Productbll {

    private ProductDao product;

    public Productbll()
    {
        product= new ProductDao();
    }

    public List<Product> selectq()
    {
        return product.findAll();
    }

    public Product findById(int id)
    {
        Product prd= product.findById(id);
        if(prd == null)
        {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return prd;
    }

    public void insert(Product p)
    {
        product.insert(p);

    }

    public void update(Product p)
    {
        product.update(p);

    }

    public void delete(Product p)
    {
        product.delete(p);
    }


    public Object[][] gettable2(){ return product.gettable(selectq());}
    public Object[] getheader(){ return product.getheaderoftable();}
}
