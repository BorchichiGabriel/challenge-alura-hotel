package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Reserva;

public class ReservasDAO {
	final private Connection con;
	public ReservasDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Reserva reserva)  {
		ConnectionFactory factory = new ConnectionFactory(); 
    	final Connection con = factory.recuperaConexion();
    	try(con){
    		
    		final PreparedStatement statement = con.prepareStatement("INSERT INTO RESERVAS("
    				+ "fecha_entrada,fecha_salida,valor,forma_de_pago)"
    			+ " VALUES(?, ? ,?, ?)",Statement.RETURN_GENERATED_KEYS);
    	
    		try(statement){
	    		
	        	ejecutaRegistro(reserva, statement);
	        	
			}
    		} catch (SQLException e) {
				throw new RuntimeException(e);
			}
    	}
    
	
	private void ejecutaRegistro(Reserva reserva, PreparedStatement statement)
			throws SQLException {
		
		
		statement.setDate(1, reserva.getFechaE());
    	statement.setDate(2, reserva.getFechaS());
    	statement.setDouble(3, Double.parseDouble(reserva.getValor()));
    	statement.setString(4, reserva.getFormaPago());
    	
    	
    	statement.execute();
    	final ResultSet resultSet = statement.getGeneratedKeys();
    	try(resultSet){
    		while(resultSet.next()) {
    			reserva.setId(resultSet.getInt(1));
        		System.out.println(String.format("Fue insertado el producto %s",reserva));
        		
        	}	
    	}
    	
    	
    	
	}

}
