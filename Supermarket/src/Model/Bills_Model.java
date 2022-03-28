package Model;

import java.io.Serializable;
import java.util.Date;

public class Bills_Model implements Serializable {
    public String full_name;
    public String username;
    public int total_items;
    public double subtotal;
    public double discount;
    public double grand_total;
    public Date date;


    public Bills_Model(String full_name, String username, int total_items, double subtotal,double discount,double grand_total,Date date) {
        this.full_name = full_name;
        this.username = username;
        this.total_items = total_items;
        this.subtotal = subtotal;
        this.discount=discount;
        this.grand_total=grand_total;
        this.date=date;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotal_items() {
        return total_items;
    }

    public void setTotal_items(int total_items) {
        this.total_items = total_items;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(double grand_total) {
        this.grand_total = grand_total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
