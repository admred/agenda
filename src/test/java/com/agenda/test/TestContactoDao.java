package com.agenda.test;

import java.util.Arrays;
import java.util.List;

import com.agenda.dao.impl.ContactoDAOMysqlImpl;
import com.agenda.dao.IContactoDao;
import com.agenda.domain.Contacto;


public class TestContactoDao {
	static Contacto[] contactos=new Contacto[] {
			new Contacto(0l,"Ramon Jorge","Veliz","(255) 2334456","bolo322@example.com"),
			new Contacto(0l,"Roberto","Contado Fino","  ","rcf4324@example.com"),
			new Contacto(0l,"Maria Erica","Lopez","34-334123","danielaeuge@example.com"),
			new Contacto(0l,"Roxana Eugenia","Gomez Rangel","",null),
			new Contacto(0l,"Liliana","Caloventor","2000",null)
	};
	static IContactoDao contactoDao= new ContactoDAOMysqlImpl();
	
	public static void main(String[] args) throws Exception {
		
		testCreate();
		testUpdate();
		testSearch();
		testFindAll();
		testGetById();
		testDelete();
		testCreate();
		testSearchEmpty();
	}
	
	
	public static void testCreate() throws Exception{
		for(Contacto c:contactos) {
			contactoDao.create(c);
		}
	}
	public static void testFindAll() throws Exception {
		List<Contacto> listado=contactoDao.findAll();
		System.out.println("Cantidad de Registros : "+listado.size());
	}
	
	public static void testUpdate() throws Exception {
		List<Contacto> listado=contactoDao.findAll();
		
		for(Contacto c:listado) {
			String nombre=c.getNombre();
			nombre=nombre.toUpperCase();
			c.setNombre(nombre);
			contactoDao.update(c);
		}
	}
	public static void testGetById() throws Exception {
		List<Contacto> listado=contactoDao.findAll();
		List<Long> ids=listado.stream().map(Contacto::getId).toList();
		for(Long id :ids) {
			Contacto c1=contactoDao.getById(id);
			System.out.println(""+c1);
		}
	}
	
	public static void testDelete() throws Exception {
		List<Contacto> listado=contactoDao.findAll();
		for(Contacto c:listado) {
			Long id=c.getId();
			contactoDao.delete(id);
			Contacto checked=contactoDao.getById(id);
			assert(checked == null);
		}
	}
	
	public static void testSearch() throws Exception {
		List<Contacto> encontrados;
		List<String> keywords= Arrays.stream(contactos).map(Contacto::getNombre).toList();
		
		for(String key:keywords) {
			encontrados=contactoDao.search(key);
			System.out.println("Encontrados ("+key+"): "+encontrados.size());
		}
	}
	public static void testSearchEmpty() throws Exception {
		contactoDao.search("");
		
	}
}
