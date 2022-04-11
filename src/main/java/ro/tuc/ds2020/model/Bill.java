package ro.tuc.ds2020.model;

import java.sql.Time;
import java.sql.Timestamp;

public class Bill {
    private int id;
    private int clientid;
    private int productid;
    private int quantity;
    private Timestamp timestamp;
    private int price;

    public Bill() {
    }

    public Bill(int id, int clientid, int productid, int quantity, Timestamp timestamp, int price) {
        this.id = id;
        this.clientid = clientid;
        this.productid = productid;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.price = price;
    }

    public Bill(int clientid, int productid, int quantity, Timestamp timestamp, int price) {
        this.clientid = clientid;
        this.productid = productid;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.price = price;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"clientid\":" + clientid +
                ", \"productid\":" + productid +
                ", \"quantity\":" + quantity +
                ", \"timestamp\":" + timestamp +
                ", \"price\":" + price +
                '}';
    }
}
