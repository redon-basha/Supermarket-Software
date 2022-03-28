package Model;

import java.io.Serializable;
import java.util.Date;

public class Admin extends Users implements Checker {
    public String acces_level;

    public Admin(String firstName, String lastName, String salary, String phone, String usename, String password, Date birthday, String acces_level) {
        super(firstName, lastName, salary, phone, usename, password, birthday,"Admin");

    }

    public String getAcces_level() {
        return acces_level;
    }

    public void setAcces_level(String acces_level) {
        this.acces_level = acces_level;
    }

    @Override
    public boolean checkUser(String user, String pass) {
        return user.equals(this.getUsename()) && pass.equals(this.getPassword());
    }

}
