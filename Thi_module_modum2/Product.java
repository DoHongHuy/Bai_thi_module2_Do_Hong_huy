package Thi_module_modum2;

import java.time.Instant;
import java.util.Scanner;

public class Product {
    private int id;
    private String name;
    private Double price;
    private int quantity;
    private String describe;

public Product(){

}
public Product(int id, String name, Double price, int quantity, String describe){
    this.id = id;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.describe = describe;

}
    public static Product parse(String record) {
        Product product = new Product();
        String[] fields = record.split(",");
        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        double pricess = Double.parseDouble(fields[2]);
        int quantity = Integer.parseInt(fields[3]);
        String describe = fields[4];
        return new Product(id, name, pricess, quantity, describe);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", describe='" + describe + '\'' +
                '}';
    }
}