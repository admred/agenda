package com.agenda.dao;

import java.util.List;

import com.agenda.domain.Contacto;

public interface ContactoDao {

	public List<Contacto> findAll();
	
	public Contacto getById(Long id);
	
	public Contacto create(Contacto contacto);
	
	public void update(Contacto contacto);
	
	public void delete(Long id);
	
	public List<Contacto> search(String keyword);
}
