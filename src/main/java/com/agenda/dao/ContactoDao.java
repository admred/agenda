package com.agenda.dao;

import java.sql.SQLException;
import java.util.List;

import com.agenda.domain.Contacto;

public interface ContactoDao {

	public List<Contacto> findAll() throws SQLException;
	
	public Contacto getById(Long id) throws SQLException;
	
	public void create(Contacto contacto) throws SQLException;
	
	public void update(Contacto contacto) throws SQLException;
	
	public void delete(Long id) throws SQLException;
	
	public List<Contacto> search(String keyword) throws SQLException;
}
