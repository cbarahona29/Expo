/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author soporte.tecnico
 */
public class Login extends JFrame implements ActionListener{
    private JTextField user;
    private JPasswordField contra;
    private JLabel userlbl, contralbl, titlelbl;
    private JButton loginbtn, salirbtn;
    
    public Login(){
        this.setTitle("Login");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(0, 0, 0));
        
        titlelbl = new JLabel("Sistema de Administracion "
                + "\nde"
                + "\n Clientes");
        titlelbl.setBounds(125, 20, 170, 30);
        titlelbl.setForeground(Color.WHITE);
        titlelbl.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titlelbl);
        
        userlbl = new JLabel("Usuario:");
        userlbl.setBounds(50, 70, 80, 25);
        userlbl.setForeground(Color.WHITE);
        this.add(userlbl);
        
        user = new JTextField();
        user.setBounds(150, 70, 200, 25);
        user.addActionListener(this);
        this.add(user);
        
        // Password Label
        contralbl = new JLabel("Contraseña:");
        contralbl.setBounds(50, 110, 80, 25);
        contralbl.setForeground(Color.WHITE);
        this.add(contralbl);
        
        contra = new JPasswordField();
        contra.setBounds(150, 110, 200, 25);
        contra.addActionListener(this);
        this.add(contra);
        
        loginbtn = new JButton("Ingresar");
        loginbtn.setBounds(100, 170, 100, 30);
        loginbtn.addActionListener(this);
        this.add(loginbtn);
        
        salirbtn = new JButton("Salir");
        salirbtn.setBounds(220, 170, 100, 30);
        salirbtn.addActionListener(this);
        this.add(salirbtn);
        
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginbtn) {
            String username = user.getText();
            String password = new String(contra.getPassword());
            
            if(username.toLowerCase().equals("admin") && password.equals("admin")) {
                JOptionPane.showMessageDialog(this, "Login exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
                new Menu();
               this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if(e.getSource() == salirbtn) {
            System.exit(0);
        }
    }
    
}