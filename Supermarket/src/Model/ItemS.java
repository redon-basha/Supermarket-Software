package Model;

import java.io.Serializable;

public class ItemS implements Serializable {

    public String item_name;
    public String barcode;
    public String category;
    public double selling_price;
    public int quanty;

    public ItemS(String item_name, String barcode, String category, double selling_price,int quanty) {
        this.item_name = item_name;
        this.barcode = barcode;
        this.category = category;
        this.selling_price = selling_price;
        this.quanty=quanty;

    }

    public int getQuanty() {
        return quanty;
    }

    public void setQuanty(int quanty) {
        this.quanty = quanty;
    }

    public double getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(double selling_price) {
        this.selling_price = selling_price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

}
