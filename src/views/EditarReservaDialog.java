package views;

import java.sql.Date;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.alura.jdbc.modelo.Reserva;

public class EditarReservaDialog extends JDialog implements ActionListener {
    private boolean aceptado = false;
    private JTextField txtFechaEntrada;
    private JTextField txtFechaSalida;
    private JTextField txtValor;
    private JTextField txtFormaPago;
    private JButton btnAceptar;
    private JButton btnCancelar;

    public EditarReservaDialog(JFrame parent, String titulo) {
        super(parent, titulo, true);
        JPanel panel = new JPanel(new GridLayout(0, 2));

        JLabel lblFechaEntrada = new JLabel("Fecha Entrada:");
        txtFechaEntrada = new JTextField();
        panel.add(lblFechaEntrada);
        panel.add(txtFechaEntrada);

        JLabel lblFechaSalida = new JLabel("Fecha Salida:");
        txtFechaSalida = new JTextField();
        panel.add(lblFechaSalida);
        panel.add(txtFechaSalida);

        JLabel lblValor = new JLabel("Valor:");
        txtValor = new JTextField();
        panel.add(lblValor);
        panel.add(txtValor);

        JLabel lblFormaPago = new JLabel("Forma de Pago:");
        txtFormaPago = new JTextField();
        panel.add(lblFormaPago);
        panel.add(txtFormaPago);

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

    public String getFechaEntrada() {
        return txtFechaEntrada.getText().trim();
    }

    public String getFechaSalida() {
        return txtFechaSalida.getText().trim();
    }

    public String getValor() {
        return txtValor.getText().trim();
    }

    public String getFormaPago() {
        return txtFormaPago.getText().trim();
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
