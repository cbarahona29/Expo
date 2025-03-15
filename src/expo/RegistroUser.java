/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expo;

import Serializable.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author soporte.tecnico
 */
public class RegistroUser extends JFrame implements ActionListener{
    private JTextField usertxf, userIdtxf, cellPhoneNumbertxf, lastNametxf, pricetxf;
    private JLabel userlbl, useridlbl, celllbl, lastnamelbl, pricelbl, titlelbl;
    private JButton registarbtn, regresarbtn;
    
    public RegistroUser(){
        
        this.setTitle("Registro de Clientes");
        this.setSize(500, 460);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(0, 0, 0));
        
        titlelbl = new JLabel("Registro de Nuevo Cliente");
        titlelbl.setBounds(140, 20, 250, 30);
        titlelbl.setForeground(Color.WHITE);
        titlelbl.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titlelbl);
        
        userlbl = new JLabel("Nombre:");
        userlbl.setBounds(50, 70, 100, 25);
        userlbl.setForeground(Color.WHITE);
        this.add(userlbl);
        
        usertxf = new JTextField();
        usertxf.setBounds(150, 70, 280, 25);
        usertxf.addActionListener(this);
        this.add(usertxf);
        
        lastnamelbl = new JLabel("Apellido:");
        lastnamelbl.setBounds(50, 110, 100, 25);
        lastnamelbl.setForeground(Color.WHITE);
        this.add(lastnamelbl);
        
        lastNametxf = new JTextField();
        lastNametxf.setBounds(150, 110, 280, 25);
        lastNametxf.addActionListener(this);
        this.add(lastNametxf);
        
        useridlbl = new JLabel("ID Cliente:");
        useridlbl.setBounds(50, 150, 100, 25);
        useridlbl.setForeground(Color.WHITE);
        this.add(useridlbl);
        
        userIdtxf = new JTextField();
        userIdtxf.setBounds(150, 150, 280, 25);
        userIdtxf.addActionListener(this);
        this.add(userIdtxf);
        
        celllbl = new JLabel("Teléfono:");
        celllbl.setBounds(50, 190, 100, 25);
        celllbl.setForeground(Color.WHITE);
        this.add(celllbl);
        
        cellPhoneNumbertxf = new JTextField();
        cellPhoneNumbertxf.setBounds(150, 190, 280, 25);
        cellPhoneNumbertxf.addActionListener(this);
        this.add(cellPhoneNumbertxf);
        
        pricelbl = new JLabel("Precio del Servicio:");
        pricelbl.setBounds(50, 230, 100, 25); 
        pricelbl.setForeground(Color.WHITE);
        this.add(pricelbl);
        
        pricetxf = new JTextField();
        pricetxf.setBounds(150, 230, 280, 25); 
        pricetxf.addActionListener(this);
        this.add(pricetxf);
        
        registarbtn = new JButton("Registrar");
        registarbtn.setBounds(120, 320, 120, 30); 
        registarbtn.addActionListener(this);
        this.add(registarbtn);
        
        regresarbtn = new JButton("Regresar");
        regresarbtn.setBounds(260, 320, 120, 30);
        regresarbtn.addActionListener(this);
        this.add(regresarbtn);
        
        this.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registarbtn) {
            if (usertxf.getText().isEmpty() || lastNametxf.getText().isEmpty() || 
                userIdtxf.getText().isEmpty() || cellPhoneNumbertxf.getText().isEmpty() || 
                pricetxf.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                String nombre = usertxf.getText();
                String apellido = lastNametxf.getText();
                int userId = Integer.parseInt(userIdtxf.getText());
                int telefono = Integer.parseInt(cellPhoneNumbertxf.getText());
                double precio = Double.parseDouble(pricetxf.getText()); 
                
                Clientes usuario = new Clientes(nombre, apellido, telefono, userId, precio);
                Logica log=new Logica();
                log.saveUserToFile(usuario);
                usertxf.setText("");
                lastNametxf.setText("");
                userIdtxf.setText("");
                cellPhoneNumbertxf.setText("");
                pricetxf.setText("");
               
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor ingrese valores numéricos válidos para ID, Teléfono y Precio", 
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == regresarbtn) {
            this.dispose();
            new Menu();
        }
    }
    
}