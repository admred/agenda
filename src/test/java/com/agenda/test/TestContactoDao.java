package com.agenda.test;

import java.util.Arrays;
import java.util.List;

import com.agenda.dao.impl.ContactoDAOMysqlImpl;
import com.agenda.dao.ContactoDao;
import com.agenda.domain.Contacto;


public class TestContactoDao {
	static Contacto[] contactos=new Contacto[] {
			new Contacto(0l,"Ruth","Quiroga","1781289039","codo965792002@example.com"),
			new Contacto(0l,"Tamara","Rogani","1368158572","codo1374591471@example.com"),
			new Contacto(0l,"AlÍ","Pedernera","1589760862","codo854268527@example.com"),
			new Contacto(0l,"Yanet","Ahumada","1370294641","codo356529191@example.com"),
			new Contacto(0l,"Isabel","Rodriguez","1514006841","codo2065787249@example.com"),
			new Contacto(0l,"Laura","Andrada","1757979712","codo1593133406@example.com"),
			new Contacto(0l,"Melisa","Silva","1786080070","codo1740719330@example.com"),
			new Contacto(0l,"RamÓn","Silva","404766829","codo140661936@example.com"),
			new Contacto(0l,"LucÍa","Miranda","117909939","codo38572220@example.com"),
			new Contacto(0l,"Carolina","Rolón","496406851","codo1486068511@example.com")
		};
	
	static ContactoDao contactoDao= new ContactoDAOMysqlImpl();
	
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
