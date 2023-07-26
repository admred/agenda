package com.agenda.dao;

import java.util.List;
import com.agenda.domain.*;

public interface GrupoContactoDao {

	public List<Grupo> hasGroup(Contacto contacto) ;
	
	public List<Contacto> hasContact(Grupo grupo) ;

	void unsetAll(Contacto contacto);

	void updateGroups(Contacto contacto, List<Grupo> grupos) ;

}
