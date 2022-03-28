package Model;

import java.io.Serializable;

public class ProducT implements Serializable {

    public String barcode;
    public String name;
    public String category;
    public double price;
    public String supplier;
    public int quanty;

    public ProducT(String barcode, String name, String category, double price,String supplier,int quanty) {
        this.barcode = barcode;
        this.name = name;
        this.category = category;
        this.price = price;
        this.supplier=supplier;
        this.quanty=quanty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getQuanty() {
        return quanty;
    }

    public void setQuanty(int quanty) {
        this.quanty = quanty;
    }
}

