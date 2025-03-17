package expo;

import Serializable.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class verClientes extends JFrame implements ActionListener {
    
    private JLabel titleLbl, resultadoLbl;
    private JButton actualizarBtn, regresarBtn;
    private JTextArea resultadoTa;
    private JScrollPane scrollPane;
    private Logica logica;
    
    public verClientes() {
        this.setTitle("Ver Clientes");
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(16, 112, 136));
        
        logica = new Logica();
        
        titleLbl = new JLabel("Todos los Clientes");
        titleLbl.setBounds(200, 20, 250, 30);
        titleLbl.setFont(new Font("Arial", Font.BOLD, 24));
        titleLbl.setForeground(Color.WHITE);
        this.add(titleLbl);
        
        resultadoLbl = new JLabel("Clientes registrados:");
        resultadoLbl.setBounds(50, 70, 200, 25);
        resultadoLbl.setForeground(Color.WHITE);
        this.add(resultadoLbl);
        
        resultadoTa = new JTextArea();
        resultadoTa.setEditable(false);
        resultadoTa.setLineWrap(true);
        resultadoTa.setWrapStyleWord(true);
        
        scrollPane = new JScrollPane(resultadoTa);
        scrollPane.setBounds(50, 100, 500, 280);
        this.add(scrollPane);
        
        actualizarBtn = new JButton("Actualizar");
        actualizarBtn.setBounds(150, 400, 120, 30);
        actualizarBtn.addActionListener(this);
        this.add(actualizarBtn);
        
        regresarBtn = new JButton("Regresar");
        regresarBtn.setBounds(330, 400, 120, 30);
        regresarBtn.addActionListener(this);
        this.add(regresarBtn);
        
        cargarClientes();
        
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == actualizarBtn) {
            cargarClientes();
        } else if (e.getSource() == regresarBtn) {
            this.dispose();
            try {
                new Menu();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error al abrir el menu principal: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void cargarClientes() {
        resultadoTa.setText("");
        
        Clientes[] clientes = logica.getAllClients();
        
        if (clientes == null || clientes.length == 0 || clientes[0] == null) {
            resultadoTa.setText("No hay clientes registrados.");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        int contador = 0;
        
        for (Clientes cliente : clientes) {
            if (cliente != null) {
                contador++;
                
                sb.append("ID: ").append(cliente.getUserId())
                  .append("\nNombre: ").append(cliente.getName())
                  .append("\nApellido: ").append(cliente.getLastName())
                  .append("\nTeléfono: ").append(cliente.getcellPhoneNumer())
                  .append("\nPrecio: $").append(cliente.getPrice())
                  .append("\n-------------------------\n");
            }
        }
        
        if (contador > 0) {
            resultadoTa.setText(sb.toString());
        } else {
            resultadoTa.setText("No hay clientes registrados.");
        }
    }
  
    
}