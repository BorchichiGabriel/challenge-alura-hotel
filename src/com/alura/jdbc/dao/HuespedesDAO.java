package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Huespedes;


public class HuespedesDAO {
	final private Connection con;
	public HuespedesDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Huespedes huesped)  {
		ConnectionFactory factory = new ConnectionFactory(); 
    	final Connection con = factory.recuperaConexion();
    	try(con){
    		
    		final PreparedStatement statement = con.prepareStatement("INSERT INTO HUESPEDES("
    				+ "nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva)"
    			+ " VALUES(?, ? , ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
    	
    		try(statement){
	    		
	        	ejecutaRegistro(huesped, statement);
	        	
			}
    		} catch (SQLException e) {
				throw new RuntimeException(e);
			}
    	}
    
	
	private void ejecutaRegistro(Huespedes huesped, PreparedStatement statement)
			throws SQLException {
		
		
		statement.setString(1, huesped.getNombre());
    	statement.setString(2, huesped.getApellido());
    	statement.setDate(3, huesped.getFechaNac());
    	statement.setString(4, huesped.getNacionalidad());
    	statement.setString(5, huesped.getTelefono());
    	statement.setInt(6, huesped.getIdReserva());
    	
    	statement.execute();
    	final ResultSet resultSet = statement.getGeneratedKeys();
    	try(resultSet){
    		while(resultSet.next()) {
    			huesped.setId(resultSet.getInt(1));
        		System.out.println(String.format("Fue registrado el huesped %s",huesped));
        		
        	}	
    	}
    	
    }
	
	public List<Huespedes> listarPorApellido(String apellido) {
		
		List<Huespedes> resultado = new ArrayList<>();
	
		try {
				
				final PreparedStatement statement = con.prepareStatement(
						"SELECT H.ID, H.NOMBRE, H.APELLIDO, H.FECHA_DE_NACIMIENTO, H.NACIONALIDAD, H.TELEFONO, H.ID_RESERVA "
						+ "FROM HUESPEDES H"
						+" WHERE H.APELLIDO = ?");
				
				try(statement){
					statement.setString(1, apellido);
					statement.execute();
					final ResultSet resultSet = statement.getResultSet();
					//final ResultSet resultSet = statement.executeQuery();
				
					try(resultSet){
						while (resultSet.next()) {
							Integer huespedID = resultSet.getInt("H.ID");
							Date fechaNac = resultSet.getDate("H.FECHA_DE_NACIMIENTO");
							String nombre = resultSet.getString("H.NOMBRE");
							String lastName = resultSet.getString("H.APELLIDO");
							String nacionalidad = resultSet.getString("H.NACIONALIDAD");
							String telefono = resultSet.getString("H.TELEFONO");
							Integer reservaID = resultSet.getInt("H.ID_RESERVA");
							
							var huesped = new Huespedes(huespedID, nombre, lastName, fechaNac, nacionalidad, telefono, reservaID);
									
										resultado.add(huesped);
										
									
						}
					};
							
						
				}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
}

	public int modificar(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono, Integer idReserva) {
		try (con){
			final PreparedStatement statement = con.prepareStatement("UPDATE HUESPEDES SET "
						    + " NOMBRE = ?" 
						    + ", APELLIDO = ?" 
						    + ", FECHA_DE_NACIMIENTO = ?"
						    + ", NACIONALIDAD = ?"
						    + ", TELEFONO = ?"
						    + " WHERE ID =?" );
				try(statement){
					statement.setString(1, nombre);
			    	statement.setString(2, apellido);
			    	statement.setDate(3, fechaNacimiento);
			    	statement.setString(4, nacionalidad);
			    	statement.setString(5, telefono);
			    	statement.setInt(6, id);
					
					statement.execute();
					int updateCount=statement.getUpdateCount();
					
					return updateCount;
				}
    	}catch(SQLException e) {
    		throw new RuntimeException(e);
    	}
		
	}

}
