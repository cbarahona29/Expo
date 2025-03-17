package Serializable;

import java.io.Serializable;
import java.io.*;

/**
 *
 * @author soporte.tecnico
 */
public class Clientes implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
     String name;
     String lastName;
     int cellPhoneNumber;
     int userId;
     double price;
     boolean isActive;
    
    private static final String COUNTER_FILE = "id.dat";
    
    public static int getNextId() {
        int nextId = 1;
        
        try {
            File file = new File(COUNTER_FILE);
            if (file.exists() && file.length() > 0) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                nextId = (Integer) ois.readObject();
                ois.close();
                fis.close();
            } else {
                nextId = newId() + 1;
            }
        } catch (Exception e) {
            nextId = newId() + 1;
        }
        
        return nextId;
    }
    
    private static int newId() {
        int highestId = 0;
        try {
            Logica logica = new Logica();
            Clientes[] clientes = logica.getAllClients();
            
            if (clientes != null) {
                for (Clientes cliente : clientes) {
                    if (cliente != null && cliente.getUserId() > highestId) {
                        highestId = cliente.getUserId();
                    }
                }
            }
        } catch (Exception e) {
            highestId = 0;
        }
        
        return highestId;
    }
    
    public static int NewID() {
        int currentId = getNextId();
        
        try {
            FileOutputStream fos = new FileOutputStream(COUNTER_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(currentId + 1); 
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return currentId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public int getcellPhoneNumer() {
        return cellPhoneNumber;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setCellPhoneNumber(int cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public void setIsActive() {
        this.isActive = !this.isActive;
    }
    
    public boolean isActive() {
        return this.isActive;
    }
    
    public Clientes(String name, String lastName, int cellPhoneNumber, int userId, double price) {
        this.name = name;
        this.lastName = lastName;
        this.cellPhoneNumber = cellPhoneNumber;
        this.userId = userId;
        this.price = price;
        this.isActive = true;
    }
    
    public Clientes(String name, String lastName, int cellPhoneNumber, double price) {
        this.name = name;
        this.lastName = lastName;
        this.cellPhoneNumber = cellPhoneNumber;
        this.price = price;
        this.isActive = true;
        
        this.userId = NewID();
    }
}