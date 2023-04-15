package com.alura.jdbc.controller;


import com.alura.jdbc.dao.ReservasDAO;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Reserva;

public class ReservasController {
	private ReservasDAO reservasDao;
	public ReservasController() {
		this.reservasDao = new ReservasDAO(new ConnectionFactory().recuperaConexion());
	}
	public void guardar(Reserva nuevaReserva) {
		reservasDao.guardar(nuevaReserva);
		
	}

}
