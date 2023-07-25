package com.agenda.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.agenda.dao.*;
import com.agenda.dao.impl.*;
import com.agenda.domain.*;

@WebServlet("/ActualizarContacto")
public class ActualizarContacto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ContactoDao contactoDao=new ContactoDAOMysqlImpl();
	
	private GrupoDao grupoDao=new GrupoDAOMysqlImpl();
	
	private GrupoContactoDao grupoContactoDao=new GrupoContactoDAOMysqlImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id=0l;
		Contacto contacto=null;
		List<Grupo> grupos=null;
		List<Grupo> checked=null;
		
		try {
			id=Long.valueOf(request.getParameter("id"));
		}catch(NumberFormatException e) {
			request.setAttribute("messages","id no valido");
			getServletContext().getRequestDispatcher("/WEB-INF/views/listarContacto.jsp").forward(request, response);
			return;
		}
		
		grupos=grupoDao.findAll();
		request.setAttribute("grupos",grupos);
		
		checked=grupoContactoDao.hasGroup(contactoDao.getById(id));
		request.setAttribute("checked",checked);
		
		contacto=contactoDao.getById(id);
		if(contacto == null) {
			request.setAttribute("messages","Contacto no encontrado");
			getServletContext().getRequestDispatcher("/WEB-INF/views/listarContacto.jsp").forward(request, response);
			return;
		}
		request.setAttribute("contacto", contacto);
			
		getServletContext().getRequestDispatcher("/WEB-INF/views/actualizarContacto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id=null;
		Contacto contacto=null;
		String[] grupos=request.getParameterValues("grupo[]");
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		String email=request.getParameter("email");
		String telefono=request.getParameter("telefono");

		
		if(nombre.isBlank()) {
			request.setAttribute("message","Nombre es necesario");
			getServletContext().getRequestDispatcher("/WEB-INF/views/actualizarContacto.jsp").forward(request, response);
			return;
		}
		if(apellido.isBlank()) {
			request.setAttribute("message","Apellido es necesario");
			getServletContext().getRequestDispatcher("/WEB-INF/views/actualizarContacto.jsp").forward(request, response);
			return;
		}
		if(!email.isBlank() && !email.contains("@")) {
			request.setAttribute("message","Email debe tener un @");
			getServletContext().getRequestDispatcher("/WEB-INF/views/actualizarContacto.jsp").forward(request, response);
			return;
		}
		
		try {	
			id=Long.parseLong(request.getParameter("id"));
			contacto=new Contacto(id,nombre,apellido,telefono,email);
			contactoDao.update(contacto);
		} catch (Exception e) {
			request.setAttribute("messages","id no valido");
			getServletContext().getRequestDispatcher("/WEB-INF/views/listarContacto.jsp").forward(request, response);
			return;
		}
		List<Grupo> listado;
		try {
			listado=Arrays.asList(grupos).stream()
					.map(Long::parseLong)
					.map(grupoDao::getById).toList();
		}catch(NullPointerException e) {
			listado=new ArrayList<Grupo>();
		}
		
		grupoContactoDao.updateGroups(contacto,listado);
		
		response.sendRedirect("ListarContacto");
	}
}
