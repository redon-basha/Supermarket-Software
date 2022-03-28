package Utils;

import Model.Bills_Model;
import Model.ItemS;

import java.io.*;
import java.util.ArrayList;

public class Bills_Utils  {
    private ArrayList<Bills_Model> bills_modelArrayList;
    private static final String file = "Bills.txt";
    private File fi5;

    public Bills_Utils() {
        bills_modelArrayList = new ArrayList<>();
        fi5 = new File(file);
        if (fi5.exists()) {
            readBills();
        }
    }

    public ArrayList<Bills_Model> getBarcode() {
        return bills_modelArrayList;
    }

    private void writeBills() {
        try {
            FileOutputStream fos = new FileOutputStream(fi5);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bills_modelArrayList);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            System.err.println("File cannot be written!!!");
        } catch (IOException e) {
            System.err.println("Problem with writing object");
        }
    }

    private void readBills() {
        try {
            FileInputStream fis = new FileInputStream(fi5);
            ObjectInputStream ois = new ObjectInputStream(fis);
            bills_modelArrayList = (ArrayList<Bills_Model>) ois.readObject();
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

    public ArrayList<Bills_Model> getBills() {
        return bills_modelArrayList;
    }

    public void addBills(Bills_Model x) {
        bills_modelArrayList.add(x);
        writeBills();
    }


    public Bills_Model getBillsbyUsername(String username) {
        for (Bills_Model i : bills_modelArrayList)
            if(i.getUsername().equals(username))
                return (Bills_Model) i;

        return null;
    }
    public void updateBills() {
        writeBills();
    }
    public ArrayList<Bills_Model> getThat(String username, ArrayList<Bills_Model> current_analytics){
        ArrayList<Bills_Model> filters = new ArrayList<>();

        for (Bills_Model i : current_analytics)
            if(i.getUsername().equals(username))
                filters.add(i);
        return filters;

    }
}

