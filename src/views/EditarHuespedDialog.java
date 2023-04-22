package views;

import java.sql.Date;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.alura.jdbc.modelo.Huespedes;

public class EditarHuespedDialog extends JDialog implements ActionListener {
    private boolean aceptado = false;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtFechaNacimiento;
    private JTextField txtNacionalidad;
    private JTextField txtTelefono;
    private JButton btnAceptar;
    private JButton btnCancelar;

    public EditarHuespedDialog(JFrame parent, String titulo) {
        super(parent, titulo, true);
        JPanel panel = new JPanel(new GridLayout(0, 2));

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        panel.add(lblNombre);
        panel.add(txtNombre);
        
        JLabel lblApellido = new JLabel("Apellido:");
        txtApellido = new JTextField();
        panel.add(lblApellido);
        panel.add(txtApellido);
        
        JLabel lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");
        txtFechaNacimiento = new JTextField();
        panel.add(lblFechaNacimiento);
        panel.add(txtFechaNacimiento);

        JLabel lblNacionalidad = new JLabel("Nacionalidad:");
        txtNacionalidad = new JTextField();
        panel.add(lblNacionalidad);
        panel.add(txtNacionalidad);
        
        JLabel lblTelefono = new JLabel("Telefono:");
        txtTelefono = new JTextField();
        panel.add(lblTelefono);
        panel.add(txtTelefono);
        
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(this);
        panel.add(btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        panel.add(btnCancelar);

        setContentPane(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public String getFechaNacimiento() {
        return txtFechaNacimiento.getText().trim();
    }

    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public String getApellido() {
        return txtApellido.getText().trim();
    }

    public String getNacionalidad() {
        return txtNacionalidad.getText().trim();
    }
    
    public String getTelefono() {
        return txtTelefono.getText().trim();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAceptar) {
            aceptado = true;
            dispose();
        } else if (e.getSource() == btnCancelar) {
            dispose();
        }
    }
}
