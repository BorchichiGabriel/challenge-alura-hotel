package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
