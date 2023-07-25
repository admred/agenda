package com.agenda.test;

import java.util.Arrays;
import java.util.List;

import com.agenda.dao.impl.GrupoDAOMysqlImpl;
import com.agenda.dao.GrupoDao;
import com.agenda.domain.Grupo;


public class TestGrupoDao {
	static Grupo[] grupos=new Grupo[] {
			new Grupo(0l,"Universidad","Grupo universitario"),
			new Grupo(0l,"Trabajo","Compañeros de trabajo, proveedores, jefes y clientes."),
			new Grupo(0l,"Amistades","Grupo amistades y conocidos"),
			new Grupo(0l,"Familia","Grupo familia y hogar"),
			new Grupo(0l,"Servicio","Servicios varios"),
			new Grupo(0l,"Otro","Otros grupos no catalogados")
		};
	
	static GrupoDao grupoDao= new GrupoDAOMysqlImpl();
	
	public static void main(String[] args) throws Exception {
		
		testCreate();
		testUpdate();
		testSearch();
		testFindAll();
		testGetById();
		testDelete();
		testSearchEmpty();
		System.out.println("Paso los tests existosamente!");
	}
	
	
	public static void testCreate() throws Exception{
		for(Grupo c:grupos) {
			grupoDao.create(c);
		}
	}
	public static void testFindAll() throws Exception {
		List<Grupo> listado=grupoDao.findAll();
		System.out.println("Cantidad de Registros : "+listado.size());
	}
	
	public static void testUpdate() throws Exception {
		List<Grupo> listado=grupoDao.findAll();
		
		for(Grupo c:listado) {
			String nombre=c.getNombre();
			nombre=nombre.toUpperCase();
			c.setNombre(nombre);
			grupoDao.update(c);
		}
	}
	public static void testGetById() throws Exception {
		List<Grupo> listado=grupoDao.findAll();
		List<Long> ids=listado.stream().map(Grupo::getId).toList();
		for(Long id :ids) {
			Grupo c1=grupoDao.getById(id);
			System.out.println(""+c1);
		}
	}
	
	public static void testDelete() throws Exception {
		List<Grupo> listado=grupoDao.findAll();
		for(Grupo c:listado) {
			grupoDao.delete(c);
		}
	}
	
	public static void testSearch() throws Exception {
		List<Grupo> encontrados;
		List<String> keywords= Arrays.stream(grupos).map(Grupo::getNombre).toList();
		
		for(String key:keywords) {
			encontrados=grupoDao.search(key);
			System.out.println("Encontrados ("+key+"): "+encontrados.size());
		}
	}
	public static void testSearchEmpty() throws Exception {
		grupoDao.search("");
		
	}
}
