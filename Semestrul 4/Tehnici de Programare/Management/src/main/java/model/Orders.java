package model;

public class Orders {
    private int id;
    private int clientid;
    private int productid;
    private int quantityorder;

    public Orders(int orderid, int clientid, int productid, int quantityorder) {
        this.id = orderid;
        this.clientid = clientid;
        this.productid = productid;
        this.quantityorder = quantityorder;
    }
    public Orders()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getQuantityorder() {
        return quantityorder;
    }

    public void setQuantityorder(int quantityorder) {
        this.quantityorder = quantityorder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderid=" + id +
                ", clientid=" + clientid +
                ", productid=" + productid +
                ", quantityorder=" + quantityorder +
                '}';
    }
}
