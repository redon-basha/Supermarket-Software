package Utils;

import Model.ItemS;

import Model.ProducT;

import java.io.*;
import java.util.ArrayList;

public class Items {
    private ArrayList<ItemS> item;
    private static final String file = "items.bin";
    private File fi4;

    public Items() {
        item = new ArrayList<>();
        fi4 = new File(file);
        if (fi4.exists()) {
            readItems();
        }
    }

    public ArrayList<ItemS> getBarcode() {
        return item;
    }

    private void writeItems() {
        try {
            FileOutputStream fos = new FileOutputStream(fi4);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(item);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            System.err.println("File cannot be written!!!");
        } catch (IOException e) {
            System.err.println("Problem with writing object");
        }
    }

    private void readItems() {
        try {
            FileInputStream fis = new FileInputStream(fi4);
            ObjectInputStream ois = new ObjectInputStream(fis);
            item = (ArrayList<ItemS>) ois.readObject();
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

    public ArrayList<ItemS> getItems() {
        return item;
    }

    public void addItems(ItemS x) {
        item.add(x);
        writeItems();
    }


    public ItemS getItemsbybarcode(String username) {
        for (ItemS i : item)
            if(i.getBarcode().equals(username))
                return (ItemS) i;

        return null;
    }
    public void updateIteams() {
        writeItems();
    }
}
