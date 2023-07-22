package com.agenda.dao;

import java.sql.SQLException;
import java.util.List;

import com.agenda.domain.Grupo;

public interface GrupoDao {

	public List<Grupo> findAll() throws SQLException;
	
	public Grupo getById(Long id) throws SQLException;
	
	public void create(Grupo grupo) throws SQLException;
	
	public void update(Grupo grupo) throws SQLException;
	
	public void delete(Long id) throws SQLException;
	
	public List<Grupo> search(String keyword) throws SQLException;
}
