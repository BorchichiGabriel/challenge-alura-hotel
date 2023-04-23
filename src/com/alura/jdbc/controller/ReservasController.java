package com.alura.jdbc.controller;


import java.sql.Date;
import java.util.List;

import com.alura.jdbc.dao.ReservasDAO;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Huespedes;
import com.alura.jdbc.modelo.Reserva;

public class ReservasController {
	private ReservasDAO reservasDao;
	public ReservasController() {
		this.reservasDao = new ReservasDAO(new ConnectionFactory().recuperaConexion());
	}
	public void guardar(Reserva nuevaReserva) {
		reservasDao.guardar(nuevaReserva);
		
	}
	
	public List<Reserva> listar(Integer nroReserva){
		return this.reservasDao.listarReservas(nroReserva);
	}
	public int modificar(Integer id, Date fechaE, Date fechaS, String valor, String formaPago) {
		int result = this.reservasDao.modificar(id, fechaE, fechaS, valor, formaPago);
		return result;
	}
	public int eliminar(Integer id) {
		int result = reservasDao.eliminar(id);
		return result;
	}
	

}
