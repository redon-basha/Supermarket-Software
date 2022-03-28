package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class  Users  implements Serializable {
   public String firstName;
    public String lastName;
    public String salary;
    public String phone;
    public String usename;
    public String password;
    public Date birthday;
    public Date date_created;
    public String acces_level;


    public Users(String firstName, String lastName,String salary,String phone,String usename,String password,Date birthday, String acces_level) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.phone = phone;
        this.usename = usename;
        this.password = password;
        this.acces_level=acces_level;
        this.birthday = birthday;
        this.date_created = new Date();

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }
}