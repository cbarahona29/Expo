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
public class Menu extends JFrame implements ActionListener {
    
    private JButton registrarBtn, buscarBtn, verTodosBtn, eliminarBtn, cerrarSesionBtn;
    private JLabel titleLbl;
    
    public Menu() {
        this.setTitle("Sistema de Gestión de Clientes");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(0, 0, 0));
        
        titleLbl = new JLabel("Menú Principal");
        titleLbl.setBounds(150, 20, 200, 40);
        titleLbl.setForeground(Color.WHITE);
        titleLbl.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(titleLbl);
        
        registrarBtn = new JButton("Registrar Cliente");
        registrarBtn.setBounds(150, 80, 200, 40);
        registrarBtn.addActionListener(this);
        this.add(registrarBtn);
        
        buscarBtn = new JButton("Buscar Cliente");
        buscarBtn.setBounds(150, 130, 200, 40);
        buscarBtn.addActionListener(this);
        this.add(buscarBtn);
        
        verTodosBtn = new JButton("Ver Todos los Clientes");
        verTodosBtn.setBounds(150, 180, 200, 40);
        verTodosBtn.addActionListener(this);
        this.add(verTodosBtn);
        
        eliminarBtn = new JButton("Eliminar Cliente");
        eliminarBtn.setBounds(150, 230, 200, 40);
        eliminarBtn.addActionListener(this);
        this.add(eliminarBtn);
        
        cerrarSesionBtn = new JButton("Cerrar Sesión");
        cerrarSesionBtn.setBounds(150, 280, 200, 40);
        cerrarSesionBtn.addActionListener(this);
        this.add(cerrarSesionBtn);
        
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registrarBtn) {
            new RegistroUser();
            this.dispose();
        } else if (e.getSource() == buscarBtn) {
            JOptionPane.showMessageDialog(this, "Función de Buscar Cliente", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == verTodosBtn) {
            JOptionPane.showMessageDialog(this, "Función de Ver Todos los Clientes", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == eliminarBtn) {
            JOptionPane.showMessageDialog(this, "Función de Eliminar Cliente", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == cerrarSesionBtn) {
            int option = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                this.dispose();
                new Login();
            }
        }
    }
   
}