package Model;

import java.io.Serializable;
import java.util.Date;

public class Analytics implements Serializable {
    public String username;
    public double debit;
    public double credit;
    public String voucher_name;
    public Date date_of_action;



    public Date getDate_of_action() {
        return date_of_action;
    }

    public void setDate_of_action(Date date_of_action) {
        this.date_of_action = date_of_action;
    }

    public String getVoucher_name() {
        return voucher_name;
    }

    public void setVoucher_name(String voucher_name) {
        this.voucher_name = voucher_name;
    }


    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Analytics(String username, double debit, double credit, String voucher_name, Date date_of_action) {
        this.username = username;
        this.debit = debit;
        this.credit = credit;
        this.voucher_name = voucher_name;
        this.date_of_action = date_of_action;

    }
}