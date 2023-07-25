package com.agenda.dao;

import java.util.List;

import com.agenda.domain.Grupo;

public interface GrupoDao {

	public List<Grupo> findAll();
	
	public Grupo getById(Long id);
	
	public Grupo create(Grupo grupo);
	
	public void update(Grupo grupo);
	
	public void delete(Grupo grupo);
	
	public List<Grupo> search(String keyword);

}
