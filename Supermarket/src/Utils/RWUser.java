package Utils;

import Model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class RWUser  {
    private ArrayList<Users> users;
    private static final String file = "users.bin";
    private File fi;

    public RWUser() {
        users = new ArrayList<>();
        fi = new File(file);
        if (fi.exists()) {
            readUsers();
        } else {
            createFile();
        }

        //createFile();
    }

    private void createFile() {
        users.add(new Admin("Redon","Basha","500","0697424266","Admin","Admin",new Date(),"Admin"));
        writeUsers();

    }

    private void writeUsers() {
        try {
            FileOutputStream fos = new FileOutputStream(fi);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
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
            FileInputStream fis = new FileInputStream(fi);
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (ArrayList<Users>) ois.readObject();
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

    public ArrayList<Users> getUsers() {
        return users;
    }

    public void add(Users x) {
        users.add(x);
        writeUsers();
    }

    public void delete(Users x) {
        users.remove(x);
        writeUsers();
    }

    public Cashier getCashierByUsername(String username) {
        for (Users i : users)
            if(i.getUsename().equals(username))
                return (Cashier) i;

        return null;
    }
    public Admin getAdminByUsername(String username) {
        for (Users i : users)
            if(i.getUsename().equals(username))
                return (Admin) i;

        return null;
    }
    public Economist getEconomistByUsername(String username) {
        for (Users i : users)
            if(i.getUsename().equals(username))
                return (Economist) i;

        return null;
    }

    public Users checkLogin(String user, String pass) {
        for (Users i : users)
            if (((Checker) i).checkUser(user, pass))
                return i;
        return null;
    }

    public void update() {
        writeUsers();
    }

    public Users getUsername1(String username) {
        for (Users i : users)
            if(i.getUsename().equals(username))
                return (Users) i;

        return null;
    }
}

