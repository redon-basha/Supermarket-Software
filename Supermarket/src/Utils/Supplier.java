package Utils;

import Model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class Supplier {
    private ArrayList<String> suppliers = null;
    private static final String file = "suppliers.bin";
    private File fi2;

    public Supplier() {
        suppliers = new ArrayList<>();
        fi2 = new File(file);
        if (fi2.exists()) {
            readUsers();
        }
    }

    public void readUsers() {
        try {
            FileInputStream fis = new FileInputStream(fi2);
            ObjectInputStream ois = new ObjectInputStream(fis);
            suppliers = (ArrayList<String>) ois.readObject();
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
    private void writeUsers() {
        try {
            FileOutputStream fos = new FileOutputStream(fi2);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(suppliers);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            System.err.println("File cannot be written!!!");
        } catch (IOException e) {
            System.err.println("Problem with writing object");
        }

    }

    public ArrayList<String> getSuppliers() {
        return suppliers;
    }

    public void add(String x) {
        suppliers.add(x);
        writeUsers();
    }
}