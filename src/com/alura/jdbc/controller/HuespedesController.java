package com.alura.jdbc.controller;


import java.sql.Date;
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

	public int modificarHuesped(Integer id, String nombre, String apellido, Date fechaNacimiento,
			String nacionalidad, String telefono, Integer idReserva) {
		int result = huespedesDao.modificar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
		return result;
	}

	public int eliminar(Integer id) {
		int result = this.huespedesDao.eliminar(id);
		return result;
	}
}
