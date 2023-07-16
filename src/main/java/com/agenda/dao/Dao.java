package com.agenda.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<E> {
	
	public List<E> findAll() throws SQLException;
	
	public E getById(Long id) throws SQLException;
	
	public Long create(E obj) throws SQLException;
	
	public Long update(E obj) throws SQLException;
	
	public void delete(Long id) throws SQLException;
}
