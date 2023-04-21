package com.alura.jdbc.controller;


import java.util.List;

import com.alura.jdbc.dao.HuespedesDAO;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Huespedes;


public class HuespedesController {
	private HuespedesDAO huespedesDao;
	
	public HuespedesController() {
		this.huespedesDao = new HuespedesDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public void guardar(Huespedes nuevoHuesped) {
		this.huespedesDao.guardar(nuevoHuesped);
		
	}
	
	public List<Huespedes> listarReservasApellido(String apellido){
		return this.huespedesDao.listarPorApellido(apellido);
	}
}
