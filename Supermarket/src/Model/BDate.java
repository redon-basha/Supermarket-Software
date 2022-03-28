package Model;

import java.io.Serializable;

public class BDate implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int day, month, year;

    public BDate(int day, int month, int year) {
        super();
        this.day = day;
        this.month = month;
        this.year = year;
    }



    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }



    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }



    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }



    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }



    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }



    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }



    public String toString(){
        return this.day+"-"+this.month+"-"+this.year;
    }
}
