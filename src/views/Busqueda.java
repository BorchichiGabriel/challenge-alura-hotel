package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alura.jdbc.controller.HuespedesController;
import com.alura.jdbc.controller.ReservasController;
import com.alura.jdbc.modelo.Huespedes;
import com.alura.jdbc.modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservasController reservasController;
	private HuespedesController huespedesController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		
		this.reservasController = new ReservasController();
		this.huespedesController = new HuespedesController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modelo.setRowCount(0);
				modeloHuesped.setRowCount(0);
				if(txtBuscar.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Por favor rellene el cuadro de busqueda");
				}else if(tbReservas.isEnabled() && esInteger(txtBuscar.getText())) {
					cargaReporte();
				}else {
					cargaReporteApellidos();
				}
			}
		});
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modificar();
			}
		});
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
	private void cargaReporte() {
		
        var contenido = reservasController.listar(Integer.valueOf(txtBuscar.getText()));
        
        
        contenido.forEach(reserva -> {
        	modelo.addRow(new Object[] { reserva.getId()
        			, reserva.getFechaE()
        			, reserva.getFechaS()
        			, reserva.getValor()
        			, reserva.getFormaPago()});
        });
    }
	
	private void cargaReporteApellidos() {
		
        var contenido = huespedesController.listarReservasApellido(txtBuscar.getText());
        
        
        contenido.forEach(huesped -> {
        	modeloHuesped.addRow(new Object[] { huesped.getId()
        			, huesped.getNombre()
        			, huesped.getApellido()
        			, huesped.getFechaNac()
        			, huesped.getNacionalidad()
        			, huesped.getTelefono()
        			, huesped.getIdReserva()});
        });
    }
	
	/* private void modificar() {
    	if (tieneFilaElegida()) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }	
    
    		
    		
			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
	        .ifPresentOrElse(fila -> {
	            Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
	            Date fechaE = (Date) modelo.getValueAt(tbReservas.getSelectedRow(), 1);
	            Date fechaS = (Date) modelo.getValueAt(tbReservas.getSelectedRow(), 2);
	            String valor = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
	            String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
	            var filasModificadas = this.reservasController.modificar(id, fechaE, fechaS, valor, formaPago);
	
	            JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
	        }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	
    	}*/
	/*private void modificar() {
	    // Verificar si hay filas seleccionadas
	    if (tbReservas.getSelectedRowCount() == 0) {
	        JOptionPane.showMessageDialog(this, "Por favor, elije al menos un item");
	        return;
	    }
	    
	    // Obtener las filas seleccionadas
	    int[] filasSeleccionadas = tbReservas.getSelectedRows();
	    int itemsModificados=0;
	    
	    // Iterar sobre las filas seleccionadas
	    for (int fila : filasSeleccionadas) {
	        Integer id = Integer.valueOf(modelo.getValueAt(fila, 0).toString());
	        Date fechaE = (Date) modelo.getValueAt(fila, 1);
	        Date fechaS = (Date) modelo.getValueAt(fila, 2);
	        String valor = (String) modelo.getValueAt(fila, 3);
	        String formaPago = (String) modelo.getValueAt(fila, 4);
	        
	        // Mostrar un diálogo para editar los valores de las celdas seleccionadas
	        // Aquí deberías implementar la lógica para modificar los valores de las celdas
	        // en función de la acción del usuario
	        // Podrías utilizar JOptionPane.showInputDialog para mostrar un cuadro de diálogo
	        // en el que el usuario pueda ingresar los nuevos valores
	        // Por ejemplo:
	        String nuevoFechaE = JOptionPane.showInputDialog(this, "Nuevo valor:", fechaE);
	        if (nuevoFechaE != null) {
	            fechaE = Date.valueOf(nuevoFechaE);
	            itemsModificados++;
	        }
	        String nuevoFechaS = JOptionPane.showInputDialog(this, "Nuevo valor:", fechaS);
	        if (nuevoFechaS != null) {
	            fechaS = Date.valueOf(nuevoFechaS);
	            itemsModificados++;
	        }
	        String nuevoValor = JOptionPane.showInputDialog(this, "Nuevo valor:", valor);
	        if (nuevoValor != null) {
	            valor = nuevoValor;
	            itemsModificados++;
	        }
	        String nuevoFormaPago = JOptionPane.showInputDialog(this, "Nuevo valor:", formaPago);
	        if (nuevoFormaPago != null) {
	            formaPago = nuevoFormaPago;
	            itemsModificados++;
	        }
	        
	        
	        // Llamar al método modificar() de la clase ReservasController
	        var filasModificadas = this.reservasController.modificar(id, fechaE, fechaS, valor, formaPago);
	
	        // Verificar si se modificó alguna fila y actualizar la tabla
	        if (filasModificadas > 0) {
	            modelo.setValueAt(fechaE, fila, 1);
	            modelo.setValueAt(fechaS, fila, 2);
	            modelo.setValueAt(valor, fila, 3);
	            modelo.setValueAt(formaPago, fila, 4);
	        }
	    }
	    
	    // Mostrar un mensaje de éxito
	    JOptionPane.showMessageDialog(this, String.format("%d item(s) modificado(s) con éxito!", itemsModificados));
	}*/
	
	private void modificar() {
	    // Verificar si hay filas seleccionadas
	    if (tbReservas.getSelectedRowCount() == 0) {
	        JOptionPane.showMessageDialog(this, "Por favor, elije al menos un item");
	        return;
	    }
	    
	    // Obtener las filas seleccionadas
	    int[] filasSeleccionadas = tbReservas.getSelectedRows();
	    int itemsModificados=0;
	    
	    // Iterar sobre las filas seleccionadas
	    for (int fila : filasSeleccionadas) {
	        Integer id = Integer.valueOf(modelo.getValueAt(fila, 0).toString());
	        Date fechaE = (Date) modelo.getValueAt(fila, 1);
	        Date fechaS = (Date) modelo.getValueAt(fila, 2);
	        String valor = (String) modelo.getValueAt(fila, 3);
	        String formaPago = (String) modelo.getValueAt(fila, 4);
	        
	        // Crear un diálogo de edición personalizado
	        EditarReservaDialog editarReservaDialog = new EditarReservaDialog(this,"Editar");
	        editarReservaDialog.setVisible(true);
	        
	        // Obtener los valores editados del diálogo
	        if (editarReservaDialog.isAceptado()) {
	            fechaE = Date.valueOf(editarReservaDialog.getFechaEntrada());
	            fechaS = Date.valueOf(editarReservaDialog.getFechaSalida());
	            valor = editarReservaDialog.getValor();
	            formaPago = editarReservaDialog.getFormaPago();
	            itemsModificados++;
	        }
	        
	        // Llamar al método modificar() de la clase ReservasController
	        var filasModificadas = this.reservasController.modificar(id, fechaE, fechaS, valor, formaPago);

	        // Verificar si se modificó alguna fila y actualizar la tabla
	        if (filasModificadas > 0) {
	            modelo.setValueAt(fechaE, fila, 1);
	            modelo.setValueAt(fechaS, fila, 2);
	            modelo.setValueAt(valor, fila, 3);
	            modelo.setValueAt(formaPago, fila, 4);
	        }
	    }
	    
	    // Mostrar un mensaje de éxito
	    JOptionPane.showMessageDialog(this, String.format("%d item(s) modificado(s) con éxito!", itemsModificados));
	}

	
	
	private boolean tieneFilaElegida() {
        return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0 ;
        //|| tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0 
    } 
	
	

	
	
	//Retorna true si el texto recibido es parseable a int
	public boolean esInteger(String texto){
		boolean resultado;
		try {
			Integer.parseInt(texto);
			resultado = true;
		}catch(NumberFormatException e) {
			resultado = false;
		}
		return resultado;
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
