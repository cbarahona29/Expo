package Serializable;


import java.io.*;
import javax.swing.*;

/**
 *
 * @author soporte.tecnico
 */ 
public class Logica {
    
    private static final String FILENAME = "miscleinte.dat";
    private static final int MAX_CLIENTES = 100; 
    
    private Clientes[] readAllUsersFromFile() {
        Clientes[] usuarios = new Clientes[MAX_CLIENTES];
        int contador = 0;
        
        try {
            File file = new File(FILENAME);
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    while (contador < MAX_CLIENTES) {
                        try {
                            Clientes user = (Clientes) ois.readObject();
                            usuarios[contador] = user;
                            contador++;
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
        
        return usuarios;
    }
    
    private int countClients(Clientes[] usuarios) {
        int count = 0;
        for (Clientes usuario : usuarios) {
            if (usuario != null) {
                count++;
            }
        }
        return count;
    }
    
    private void writeAllUsersToFile(Clientes[] usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            for (Clientes user : usuarios) {
                if (user != null) {
                    oos.writeObject(user);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage(), 
                                         "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void saveUserToFile(Clientes newUser) {
        Clientes[] usuarios = readAllUsersFromFile();
        int count = countClients(usuarios);
        
        if (count >= MAX_CLIENTES) {
            JOptionPane.showMessageDialog(null, "No se pueden agregar más clientes. Límite alcanzado.", 
                                         "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        for (int i = 0; i < count; i++) {
            if (usuarios[i] != null && usuarios[i].getUserId() == newUser.getUserId()) {
                JOptionPane.showMessageDialog(null, "Ya existe un cliente con ese ID.", 
                                             "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        usuarios[count] = newUser;
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            for (int i = 0; i <= count; i++) {
                if (usuarios[i] != null) {
                    oos.writeObject(usuarios[i]);
                }
            }
            JOptionPane.showMessageDialog(null, "Cliente registrado con éxito", 
                                         "Información", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage(), 
                                         "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    public Clientes[] getAllClients() {
        return readAllUsersFromFile();
    }
    
    
    public Clientes searchClientById(int userId) {
        Clientes[] usuarios = readAllUsersFromFile();
        
        for (Clientes cliente : usuarios) {
            if (cliente != null && cliente.getUserId() == userId) {
                return cliente;
            }
        }
        return null;
    }
    
  
    public Clientes[] searchClientsByName(String searchTerm) {
        Clientes[] usuarios = readAllUsersFromFile();
        Clientes[] resultados = new Clientes[MAX_CLIENTES];
        int resultCount = 0;
        
        for (Clientes cliente : usuarios) {
            if (cliente != null && 
                (cliente.getName().toLowerCase().contains(searchTerm.toLowerCase()) || 
                 cliente.getLastName().toLowerCase().contains(searchTerm.toLowerCase()))) {
                resultados[resultCount] = cliente;
                resultCount++;
            }
        }
        return resultados;
    }
    
    
    public boolean editClient(Clientes updatedClient) {
        Clientes[] usuarios = readAllUsersFromFile();
        boolean clienteEncontrado = false;
        
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] != null && usuarios[i].getUserId() == updatedClient.getUserId()) {
                usuarios[i] = updatedClient;
                clienteEncontrado = true;
                break;
            }
        }
        
        if (clienteEncontrado) {
            writeAllUsersToFile(usuarios);
            JOptionPane.showMessageDialog(null, "Cliente actualizado con éxito", 
                                         "Información", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el cliente con el ID especificado", 
                                         "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    
    public boolean deleteClient(int userId) {
        Clientes[] usuarios = readAllUsersFromFile();
        Clientes[] nuevosUsuarios = new Clientes[MAX_CLIENTES];
        boolean clienteEncontrado = false;
        int nuevoIndice = 0;
        
        for (Clientes usuario : usuarios) {
            if (usuario != null) {
                if (usuario.getUserId() == userId) {
                    clienteEncontrado = true;
                } else {
                    nuevosUsuarios[nuevoIndice] = usuario;
                    nuevoIndice++;
                }
            }
        }
        
        if (clienteEncontrado) {
            writeAllUsersToFile(nuevosUsuarios);
            JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito", 
                                         "Información", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro el cliente con el ID especificado", 
                                         "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

   
}