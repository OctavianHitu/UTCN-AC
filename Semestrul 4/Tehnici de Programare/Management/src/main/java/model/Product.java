package model;

public class Product {
    private int id;
    private String productname;
    private float price;
    private int quantity;

    public Product(int id,String nm,float price,int q)

    {
        this.id =id;
        this.productname=nm;
        this.price=price;
        this.quantity=q;
    }

    public Product()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + id +
                ", productname='" + productname + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
