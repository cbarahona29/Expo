/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Serializable;

import java.io.Serializable;

/**
 *
 * @author soporte.tecnico
 */
public class Clientes implements Serializable{
    
    protected String name;
    protected String lastName;
    protected int cellPhoneNumber;
    protected int userId;
    protected double price;
    protected boolean isActive;
    
    public String getName(){
    return name;
    }
    
    public String getLastName(){
    return lastName;
    }
    
    public int getcellPhoneNumer(){
    return cellPhoneNumber;
    }
    
    public int getUserId(){
    return userId;
    
    }
    public double getPrice(){
    return price;
    }
    public boolean isActivo(){
    return true;
    }
    
    public void setName(){
    this.name=name;
    }
    
    public void setLastName(){
    this.lastName=lastName;
    }
    
    public void setPrice(){
    this.price=price;
    }
    
    
    public void setCellPhoneNumber(){
    this.cellPhoneNumber=cellPhoneNumber;
    }
    public void setUserId(){
    this.userId=userId;
    }
    public void setIsActivo(){
    this.isActive=isActive;
    }
    public Clientes(String name, String lastName, int cellPhoneNumber, int userId, double price){
    this.name=name;
    this.lastName=lastName;
    this.cellPhoneNumber=cellPhoneNumber;
    this.userId=userId;
    this.price=price;
    this.isActive=isActive;
    }
   }


