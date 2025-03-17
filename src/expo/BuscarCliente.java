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
 * @author danilos
 */
public class BuscarCliente extends JFrame implements ActionListener {
    private JTextField buscarTxf;
    private JLabel titlelbl, buscarLbl, resultadoLbl;
    private JButton buscarBtn, regresarBtn, editarBtn, eliminarBtn;
    private JRadioButton buscarPorIdRb, buscarPorNombreRb;
    private ButtonGroup tipoBusquedaGroup;
    private JTextArea resultadoTa;
    private JScrollPane scrollPane;
    private Logica logica;
    private Clientes clienteEncontrado;
    
    public BuscarCliente() {
        this.setTitle("Buscar Cliente");
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(16, 112, 136));
        
        logica = new Logica();
        
        titlelbl = new JLabel("Busqueda de Clientes");
        titlelbl.setBounds(200, 20, 250, 30);
        titlelbl.setForeground(Color.WHITE);
        titlelbl.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titlelbl);
        
        buscarPorIdRb = new JRadioButton("Buscar por ID");
        buscarPorIdRb.setBounds(100, 70, 150, 25);
        buscarPorIdRb.setForeground(Color.WHITE);
        buscarPorIdRb.setBackground(new Color(16, 112, 136));
        buscarPorIdRb.setSelected(true);
        
        buscarPorNombreRb = new JRadioButton("Buscar por Nombre/Apellido");
        buscarPorNombreRb.setBounds(250, 70, 200, 25);
        buscarPorNombreRb.setForeground(Color.WHITE);
        buscarPorNombreRb.setBackground(new Color(16, 112, 136));
        
        tipoBusquedaGroup = new ButtonGroup();
        tipoBusquedaGroup.add(buscarPorIdRb);
        tipoBusquedaGroup.add(buscarPorNombreRb);
        
        this.add(buscarPorIdRb);
        this.add(buscarPorNombreRb);
        
        buscarLbl = new JLabel("Busqueda:");
        buscarLbl.setBounds(50, 110, 100, 25);
        buscarLbl.setForeground(Color.WHITE);
        this.add(buscarLbl);
        
        buscarTxf = new JTextField();
        buscarTxf.setBounds(150, 110, 280, 25);
        this.add(buscarTxf);
        
        buscarBtn = new JButton("Buscar");
        buscarBtn.setBounds(450, 110, 100, 25);
        buscarBtn.addActionListener(this);
        this.add(buscarBtn);
        
        resultadoLbl = new JLabel("Resultados:");
        resultadoLbl.setBounds(50, 150, 100, 25);
        resultadoLbl.setForeground(Color.WHITE);
        this.add(resultadoLbl);
        
        resultadoTa = new JTextArea();
        resultadoTa.setEditable(false);
        resultadoTa.setLineWrap(true);
        resultadoTa.setWrapStyleWord(true);
        
        scrollPane = new JScrollPane(resultadoTa);
        scrollPane.setBounds(50, 180, 500, 200);
        this.add(scrollPane);
        
        editarBtn = new JButton("Editar");
        editarBtn.setBounds(100, 400, 100, 30);
        editarBtn.addActionListener(this);
        editarBtn.setEnabled(false);
        this.add(editarBtn);
        
        eliminarBtn = new JButton("Eliminar");
        eliminarBtn.setBounds(250, 400, 100, 30);
        eliminarBtn.addActionListener(this);
        eliminarBtn.setEnabled(false);
        this.add(eliminarBtn);
        
        regresarBtn = new JButton("Regresar");
        regresarBtn.setBounds(400, 400, 100, 30);
        regresarBtn.addActionListener(this);
        this.add(regresarBtn);
        
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buscarBtn) {
            buscar();
        } else if (e.getSource() == editarBtn) {
            if (clienteEncontrado != null) {
                editarCliente();
            }
        } else if (e.getSource() == eliminarBtn) {
            if (clienteEncontrado != null) {
                eliminarCliente();
            }
        } else if (e.getSource() == regresarBtn) {
            this.dispose();
            try {
                new Menu();
            } catch (Exception ex) {
              
         JOptionPane.showMessageDialog(this, 
                    "Error al abrir el menu principal: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }
    
    private void buscar() {
        String textoBusqueda = buscarTxf.getText().trim();
        
        if (textoBusqueda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor para buscar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        resultadoTa.setText("");
        clienteEncontrado = null;
        editarBtn.setEnabled(false);
        eliminarBtn.setEnabled(false);
        
        try {
            if (buscarPorIdRb.isSelected()) {
                try {
                    int userId = Integer.parseInt(textoBusqueda);
                    clienteEncontrado = logica.searchClientById(userId);
                    
                    if (clienteEncontrado != null) {
                        mostrarCliente(clienteEncontrado);
                        editarBtn.setEnabled(true);
                        eliminarBtn.setEnabled(true);
                    } else {
                        resultadoTa.setText("No se encontró ningún cliente con el ID: " + userId);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El ID debe ser un número entero", "Error de formato", JOptionPane.ERROR_MESSAGE);
                }
            } else { 
                Clientes[] clientesEncontrados = logica.searchClientsByName(textoBusqueda);
                
                if (clientesEncontrados != null) {
                    StringBuilder sb = new StringBuilder();
                    int contadorClientes = 0;
                    Clientes primerCliente = null;
                    
                    for (Clientes cliente : clientesEncontrados) {
                        if (cliente != null) {
                            if (contadorClientes == 0) {
                                primerCliente = cliente;
                            }
                            contadorClientes++;
                            
                            sb.append("ID: ").append(cliente.getUserId())
                              .append("\nNombre: ").append(cliente.getName())
                              .append("\nApellido: ").append(cliente.getLastName())
                              .append("\nTeléfono: ").append(cliente.getcellPhoneNumer())
                              .append("\nPrecio: $").append(String.format("%.2f", cliente.getPrice()))
                              .append("\n\n");
                        }
                    }
                    
                    if (contadorClientes > 0) {
                        resultadoTa.setText(sb.toString());
                        
                        if (contadorClientes == 1) {
                            clienteEncontrado = primerCliente;
                            editarBtn.setEnabled(true);
                            eliminarBtn.setEnabled(true);
                        } else {
                            JOptionPane.showMessageDialog(this, 
                                "Se encontraron múltiples clientes. Para editar o eliminar, busque por ID específico.", 
                                "Múltiples resultados", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        resultadoTa.setText("No se encontraron clientes que coincidan con: " + textoBusqueda);
                    }
                } else {
                    resultadoTa.setText("No se encontraron clientes que coincidan con: " + textoBusqueda);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al buscar: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void mostrarCliente(Clientes cliente) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(cliente.getUserId())
          .append("\nNombre: ").append(cliente.getName())
          .append("\nApellido: ").append(cliente.getLastName())
          .append("\nTeléfono: ").append(cliente.getcellPhoneNumer())
          .append("\nPrecio: $").append(String.format("%.2f", cliente.getPrice()));
        
        resultadoTa.setText(sb.toString());
    }
    
    private void editarCliente() {
        EditarCliente editarDialog = new EditarCliente(this, clienteEncontrado, logica);
        editarDialog.setVisible(true);
    }
    
    public void clienteActualizado(Clientes cliente) {
        this.clienteEncontrado = cliente;
        mostrarCliente(clienteEncontrado);
    }
    
    private void eliminarCliente() {
        int opcion = JOptionPane.showConfirmDialog(this, 
                                "¿Está seguro que desea eliminar el cliente ID: " + clienteEncontrado.getUserId() + "?", 
                                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            try {
                if (logica.deleteClient(clienteEncontrado.getUserId())) {
                    resultadoTa.setText("Cliente eliminado con éxito.");
                    clienteEncontrado = null;
                    editarBtn.setEnabled(false);
                    eliminarBtn.setEnabled(false);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error al eliminar: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}