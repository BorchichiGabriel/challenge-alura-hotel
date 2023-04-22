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
public List<Reserva> listarReservas(Integer nroReserva) {
		
		List<Reserva> resultado = new ArrayList<>();
		
		try {
				
				final PreparedStatement statement = con.prepareStatement(
						"SELECT R.ID, R.FECHA_ENTRADA, R.FECHA_SALIDA, R.VALOR, R.FORMA_DE_PAGO "
						+ "FROM RESERVAS R"
						+" WHERE R.ID = ?"/*+nroReserva*/);
				
				try(statement){
					statement.setInt(1, nroReserva);
					statement.execute();
					final ResultSet resultSet = statement.getResultSet();
					//final ResultSet resultSet = statement.executeQuery();
				
					try(resultSet){
						while (resultSet.next()) {
							Integer reservaID = resultSet.getInt("R.ID");
							Date fechaE = resultSet.getDate("R.FECHA_ENTRADA");
							Date fechaS = resultSet.getDate("R.FECHA_SALIDA");
							String precio = String.valueOf(resultSet.getDouble("R.VALOR"));
							String fdePago = resultSet.getString("R.FORMA_DE_PAGO");
							
							var reserva = new Reserva(reservaID,fechaE,fechaS,precio,fdePago);
									
										resultado.add(reserva);
										
									
						}
					};
							
						
				}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}

	public int modificar(Integer id, Date fechaE, Date fechaS, String valor, String formaPago) {
		
			try (con){
				final PreparedStatement statement = con.prepareStatement("UPDATE RESERVAS SET "
							    + " FECHA_ENTRADA = ?" 
							    + ", FECHA_SALIDA = ?" 
							    + ", VALOR = ?"
							    + ", FORMA_DE_PAGO = ?"
							    + " WHERE ID =?" );
					try(statement){
						statement.setDate(1, fechaE);
				    	statement.setDate(2, fechaS);
				    	statement.setDouble(3, Double.parseDouble(valor));
				    	statement.setString(4, formaPago);
				    	statement.setInt(5, id);
						
						statement.execute();
						int updateCount=statement.getUpdateCount();
						
						return updateCount;
					}
	    	}catch(SQLException e) {
	    		throw new RuntimeException(e);
	    	}
			
	
	}
	
}
