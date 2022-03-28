package Utils;

import Model.Analytics;
import Model.Bills_Model;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Analytics_Utils implements Serializable{
    private ArrayList<Analytics> analytics_demo;
    private static final String file = "Analytics.bin";
    private File fi6;

    public Analytics_Utils() {
        analytics_demo = new ArrayList<>();
        fi6 = new File(file);
        if (fi6.exists()) {
            readAnalytics();
        }
    }

    public ArrayList<Analytics> getBarcode() {
        return analytics_demo;
    }

    private void writeAnalytics() {
        try {
            FileOutputStream fos = new FileOutputStream(fi6);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(analytics_demo);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            System.err.println("File cannot be written!!!");
        } catch (IOException e) {
            System.err.println("Problem with writing object");
        }
    }

    private void readAnalytics() {
        try {
            FileInputStream fis = new FileInputStream(fi6);
            ObjectInputStream ois = new ObjectInputStream(fis);
            analytics_demo = (ArrayList<Analytics>) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!!!");
        } catch (IOException e) {
            System.err.println("File is corrupted");
        } catch (ClassNotFoundException e) {
            System.err.println("Class does not match");
        }

    }

    public ArrayList<Analytics> getAnalytics() {
        return analytics_demo;
    }

    public void addAnalytics(Analytics x) {
        analytics_demo.add(x);
        writeAnalytics();
    }


    public Analytics getAnalyticsbyUsername(String username) {
        for (Analytics i : analytics_demo)
            if(i.getUsername().equals(username))
                return (Analytics) i;

        return null;
    }
    public void updateAnalytics() {
        writeAnalytics();
    }

    public ArrayList<Analytics> getbyUsername(String username, ArrayList<Analytics> current_analytics){
        ArrayList<Analytics> filters = new ArrayList<>();

        for (Analytics i : current_analytics)
            if(i.getUsername().equals(username))
                filters.add(i);
        return filters;
    }

    public ArrayList<Analytics> getbyType(String username, ArrayList<Analytics> current_analytics){
        ArrayList<Analytics> filters = new ArrayList<>();

        for (Analytics i : current_analytics)
            if(i.getVoucher_name().equals(username))
                filters.add(i);
        return filters;

    }
    public ArrayList<Analytics> getFromDate(Date data1, ArrayList<Analytics> current_analytics){
        ArrayList<Analytics> filters = new ArrayList<>();

        for (Analytics i : current_analytics)
            if(i.getDate_of_action().after(data1))
                filters.add(i);
        return filters;

    }
    public ArrayList<Analytics> getToDate(Date date2, ArrayList<Analytics> current_analytics){
        ArrayList<Analytics> filters = new ArrayList<>();

        for (Analytics i : current_analytics)
            if(i.getDate_of_action().before(date2))
                filters.add(i);
        return filters;

    }
}
