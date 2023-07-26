package com.agenda.test;


import java.util.ArrayList;
import java.util.List;
import com.agenda.dao.*;
import com.agenda.dao.impl.*;
import com.agenda.domain.*;

public class TestGrupoContactoDao {
	
	static GrupoContactoDao grupoContactoDao=new GrupoContactoDAOMysqlImpl(); 
	static ContactoDao contactoDao= new ContactoDAOMysqlImpl();
	static GrupoDao grupoDao= new GrupoDAOMysqlImpl();
	
	public static void main(String[] args) throws Exception {
		TestContactoDao.testDelete();
		TestContactoDao.testCreate();
		TestGrupoDao.testDelete();
		TestGrupoDao.testCreate();
		
		testCreate();
		testUnsetAll();
		testCreate();
		System.out.println("Paso los tests existosamente!");
	}

	static void testCreate() throws Exception {
		List<Contacto> contactos=contactoDao.findAll();
		List<Grupo> grupos=grupoDao.findAll();
		
		
		for(Contacto c:contactos) {
			List<Grupo> newGroups=new ArrayList<Grupo>();
			for(Grupo g:grupos) {
				if(luckyNumber(10)<4) {
					newGroups.add(g);
				}
			}
			grupoContactoDao.updateGroups(c,newGroups);
		}
		
	}
	static void testUnsetAll() throws Exception {
		List<Contacto> contactos=contactoDao.findAll();
		for(Contacto c:contactos) {
			grupoContactoDao.unsetAll(c);
			List<Grupo> grupos=grupoContactoDao.hasGroup(c);
			assert(grupos.size() == 0);
		}
	}
	
	static int luckyNumber(int size) {
		return (int)(Math.random()*size); 
	}
}
