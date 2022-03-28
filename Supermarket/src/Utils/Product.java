package Utils;

import Model.ProducT;

import java.io.*;
import java.util.ArrayList;

public class Product {
    private ArrayList<ProducT> product;
    private static final String file = "products.bin";
    private File fi3;

    public Product() {
        product = new ArrayList<>();
        fi3 = new File(file);
        if (fi3.exists()) {
            readUsers();
        } else {
            createFile();
        }

        //createFile();
    }

    private void createFile() {
    }
    public ArrayList<ProducT> getBarcode() {
        return product;
    }

    private void writeUsers() {
        try {
            FileOutputStream fos = new FileOutputStream(fi3);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(product);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            System.err.println("File cannot be written!!!");
        } catch (IOException e) {
            System.err.println("Problem with writing object");
        }
    }

    private void readUsers() {
        try {
            FileInputStream fis = new FileInputStream(fi3);
            ObjectInputStream ois = new ObjectInputStream(fis);
            product = (ArrayList<ProducT>) ois.readObject();
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

    public ArrayList<ProducT> getProducts() {
        return product;
    }

    public void add(ProducT x) {
        product.add(x);
        writeUsers();
    }

    public ArrayList<ProducT> getProductsBySupplier(String supplier_name) {
        ArrayList<ProducT> products = new ArrayList<>();

        for(ProducT p : product) {
            if(p.getSupplier().equals(supplier_name))
                products.add(p);
        }

        return products;
    }

    public ProducT getbybarcode(String username) {
        for (ProducT i : product)
            if(i.getBarcode().equals(username))
                return (ProducT) i;

        return null;
    }
}

