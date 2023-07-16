package com.agenda.controller;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import java.util.ArrayList;

import com.agenda.dao.impl.ContactoDAOMysqlImpl;
import com.agenda.domain.Contacto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/contacto")
public class ContactoController extends HttpServlet {
	
	private static final long serialVersionUID = -4259681776649694947L;
	
	private ContactoDAOMysqlImpl contactoDao;
	
	public ContactoController() {
		contactoDao=new ContactoDAOMysqlImpl();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			routeAction(req,resp);
		}catch(Exception e) {
			/* mysterious fuckery */
			e.printStackTrace();
		}
	} 
		
	private void routeAction(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		final String accion=(String) req.getParameter("accion");
		
		if(accion != null) {
			req.setAttribute("action", accion);
		}
		
		switch(accion) {
		case "editar":
			editar(req,resp);
			break;
		case "listado":
			list(req,resp);
			break;
		case "crear":
			create(req,resp);
			break;
		case "eliminar":
			eliminar(req,resp);
			break;
		case "buscar":
			buscar(req,resp);
			break;
		default:
			resp.sendRedirect("/agenda/home.jsp");
		}
	}	 
	
	private void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		List<Contacto> list;
		List<String> messages=new ArrayList<String>();
		try {
			list=contactoDao.findAll();
			req.setAttribute("list", list);
		}catch(Exception ex) {
			messages.add(ex.getMessage());
			req.setAttribute("messages", messages);
		}
		getServletContext().getRequestDispatcher("/WEB-INF/views/listado.jsp").forward(req, resp);
	}
	
	private void create(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		final String method=req.getMethod();
		List<String> messages=new ArrayList<String>();
		boolean error=false;
		
		if(method.equals("GET")) {
			// Formulario estatico
			//resp.sendRedirect("/agenda/create.html");	
			
			// Formulario multiproposito
			//req.setAttribute("contacto",new Contacto());
			getServletContext().getRequestDispatcher("/WEB-INF/views/formulario.jsp").forward(req, resp);
			return;
		}
		
		String nombre=(String) req.getParameter("nombre");
		String apellido=(String) req.getParameter("apellido");
		String telefono=(String) req.getParameter("telefono");
		String email=(String) req.getParameter("email");
		if(nombre.isBlank()) {
			error=true;
			messages.add("Nombre no puede ser vacío.");
		}
		if(apellido.isBlank()) {
			error=true;
			messages.add("Apellido no puede ser vacío.");
		}
		if( !email.isBlank() && !email.contains("@")) {
			error=true;
			messages.add("El email no es válido.");
		}
		
		if(error) {
			req.setAttribute("messages",messages);
			getServletContext().getRequestDispatcher("/WEB-INF/views/formulario.jsp").forward(req, resp);
			return;
		}
		try {
			Contacto contacto=new Contacto(0l,nombre,apellido,telefono,email);
			contactoDao.create(contacto);
			messages.add(String.format("Contacto creado con éxito : "+contacto.getApellido()));
			req.setAttribute("messages", messages);
		}catch(Exception ex) {
			// handle error
			messages.add("No se pudo crear registro : "+ex.getMessage());
			req.setAttribute("messages", messages);
			getServletContext().getRequestDispatcher("/WEB-INF/views/formulario.jsp").forward(req, resp);
		}
		// redireccion al listado si hay exito
		resp.sendRedirect("/agenda/contacto?accion=listado");
	}
	
	private void editar(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		final String method=req.getMethod();
		List<String> messages=new ArrayList<String>();
		final Long id=Long.parseLong( req.getParameter("id"));
		Contacto contacto=null;
		String jsp="/WEB-INF/views/formulario.jsp";
		boolean error=false;
		
		try {
			contacto=contactoDao.getById(id);
			if(contacto == null) {
				throw new Exception();
			}
			req.setAttribute("contacto", contacto);
		}catch(Exception ex) {
			// handle error
			ex.printStackTrace();
			messages.add("Contacto no encontrado id="+id);
			req.setAttribute("messages", messages);
			jsp="/WEB-INF/views/listado.jsp";
			error=true;
		}
		if(error == false && method.equals("POST")) {
			contacto.update(new Contacto(
					0l,
					(String) req.getParameter("nombre"),
					(String) req.getParameter("apellido"),
					(String) req.getParameter("telefono"),
					(String) req.getParameter("email")
				));
			contactoDao.update(contacto);
			resp.sendRedirect("/agenda/contacto?accion=listado");
			return;
		}
			
		getServletContext().getRequestDispatcher(jsp).forward(req, resp);
	}
	public void eliminar(HttpServletRequest req, HttpServletResponse resp) throws Exception { 
		List<String> messages=new ArrayList<String>();
		Contacto contacto=null;
		
		final Long id=Long.parseLong( req.getParameter("id"));
		try {
			contacto=contactoDao.getById(id);
			if(contacto == null) {
				throw new Exception();
			}
			contactoDao.delete(id);
		}catch(Exception ex) {
			// handle error
			ex.printStackTrace();
			messages.add("Contacto no encontrado id="+id);
			req.setAttribute("messages", messages);
		}
		resp.sendRedirect("/agenda/contacto?accion=listado");
	}
	
	
	public void buscar(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		List<Contacto> list;
		List<String> messages=new ArrayList<String>();
		String keyword=req.getParameter("keyword");
		try {
			list=contactoDao.search(keyword);
			req.setAttribute("list", list);
		}catch(Exception ex) {
			messages.add(ex.getMessage());
			req.setAttribute("messages", messages);
		}
		getServletContext().getRequestDispatcher("/WEB-INF/views/listado.jsp").forward(req, resp);
	}
}
