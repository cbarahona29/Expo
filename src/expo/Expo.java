/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package expo;

import Serializable.Clientes;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author soporte.tecnico
 */
public class Expo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Clientes usuario1=new Clientes("Cesar","Garcia", 31857261,5,25.99);
        Clientes usuario2=new Clientes("Jumana","Larach", 88533213,2,19.99);
        Clientes usuario3=new Clientes("Maria","Rodriguez",39282823,4,39.99);
        
            try(ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream("misusuarios.dat"))){
            oos.writeObject(usuario1);
            oos.writeObject(usuario2);
            oos.writeObject(usuario3);
        }catch(IOException e){
        }
        
        
        try(ObjectInputStream ois=new ObjectInputStream (new FileInputStream("misusuarios.dat"))){
           while(true){
           Clientes aux=(Clientes)ois.readObject();
             
               System.out.println(aux.getUserId());
     
               System.out.println("");
           
           }
        
        }catch(ClassNotFoundException e){
        }catch(EOFException e){
        }catch(IOException e){
        }
    }

}
  
    
    

