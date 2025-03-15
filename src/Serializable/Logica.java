/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Serializable;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author soporte.tecnico
 */
public class Logica {
    
    private static final String FILENAME = "miscleinte.dat";
    
    public void saveUserToFile(Clientes newUser) {
        ArrayList<Clientes> usuarios = new ArrayList<>();
        
        try {
            File file = new File(FILENAME);
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    while (true) {
                        try {
                            Clientes user = (Clientes) ois.readObject();
                            usuarios.add(user);
                        } catch (EOFException e) {
                            break; 
                        }
                    }
                } catch (ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage(), 
                                                 "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el archivo: " + e.getMessage(), 
                                         "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        usuarios.add(newUser);
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            for (Clientes user : usuarios) {
                oos.writeObject(user);
            }
            JOptionPane.showMessageDialog(null, "Cliente registrado con éxito", 
                                         "Información", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage(), 
                                         "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
