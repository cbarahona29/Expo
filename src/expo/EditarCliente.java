package expo;

import Serializable.*;
import java.awt.event.*;
import javax.swing.*;

public class EditarCliente extends JDialog {
    private JTextField nombreTxf, apellidoTxf, telefonoTxf, precioTxf;
    private JLabel nombreLbl, apellidoLbl, telefonoLbl, precioLbl;
    private JCheckBox activoCb;
    private JButton guardarBtn, cancelarBtn;
    private Clientes clienteEncontrado;
    private Logica logica;
    private BuscarCliente parent;

    public EditarCliente(BuscarCliente parent, Clientes clienteEncontrado, Logica logica) {
        super(parent, "Editar Cliente", true);
        this.parent = parent;
        this.clienteEncontrado = clienteEncontrado;
        this.logica = logica;
        
        Edit();
        loadClientData();
    }
    
    private void Edit() {
        setLayout(null);
        setSize(500, 350);
        setLocationRelativeTo(parent);
        
        nombreLbl = new JLabel("Nombre:");
        nombreLbl.setBounds(50, 30, 100, 25);
        add(nombreLbl);
        
        nombreTxf = new JTextField();
        nombreTxf.setBounds(150, 30, 280, 25);
        add(nombreTxf);
        
        apellidoLbl = new JLabel("Apellido:");
        apellidoLbl.setBounds(50, 70, 100, 25);
        add(apellidoLbl);
        
        apellidoTxf = new JTextField();
        apellidoTxf.setBounds(150, 70, 280, 25);
        add(apellidoTxf);
        
        telefonoLbl = new JLabel("Teléfono:");
        telefonoLbl.setBounds(50, 110, 100, 25);
        add(telefonoLbl);
        
        telefonoTxf = new JTextField();
        telefonoTxf.setBounds(150, 110, 280, 25);
        add(telefonoTxf);
        
        precioLbl = new JLabel("Precio:");
        precioLbl.setBounds(50, 150, 100, 25);
        add(precioLbl);
        
        precioTxf = new JTextField();
        precioTxf.setBounds(150, 150, 280, 25);
        add(precioTxf);  
        
        guardarBtn = new JButton("Guardar");
        guardarBtn.setBounds(120, 240, 100, 30);
        guardarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });
        add(guardarBtn);
        
        cancelarBtn = new JButton("Cancelar");
        cancelarBtn.setBounds(250, 240, 100, 30);
        cancelarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(cancelarBtn);
    }
    
    private void loadClientData() {
        nombreTxf.setText(clienteEncontrado.getName());
        apellidoTxf.setText(clienteEncontrado.getLastName());
        telefonoTxf.setText(String.valueOf(clienteEncontrado.getcellPhoneNumer()));
        precioTxf.setText(String.format("", clienteEncontrado.getPrice()));
    }
    
    private void guardarCambios() {
        try {
            String nombre = nombreTxf.getText();
            String apellido = apellidoTxf.getText();
            int telefono = Integer.parseInt(telefonoTxf.getText());
            double precio = Double.parseDouble(precioTxf.getText());
            
            if (nombre.isEmpty() || apellido.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "El nombre y apellido son obligatorios", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Clientes clienteActualizado = new Clientes(
                nombre, apellido, telefono, clienteEncontrado.getUserId(), precio);            
            if (logica.editClient(clienteActualizado)) {
                parent.clienteActualizado(clienteActualizado);
                dispose();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese valores numéricos válidos para Teléfono y Precio", 
                "Error de formato", JOptionPane.ERROR_MESSAGE);
        }
    }
}