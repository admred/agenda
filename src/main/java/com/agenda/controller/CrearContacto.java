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

@WebServlet("/CrearContacto")
public class CrearContacto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GrupoDao grupoDao=new GrupoDAOMysqlImpl();
	
	private ContactoDao contactoDao=new ContactoDAOMysqlImpl();
	
	private GrupoContactoDao grupoContactoDao=new GrupoContactoDAOMysqlImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("grupos",grupoDao.findAll());
		getServletContext().getRequestDispatcher("/WEB-INF/views/crearContacto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		String email=request.getParameter("email");
		String telefono=request.getParameter("telefono");
		String[] grupos=request.getParameterValues("grupo[]");
		

		if(nombre.isBlank()) {
			request.setAttribute("message","Nombre es necesario");
			getServletContext().getRequestDispatcher("/WEB-INF/views/crearContacto.jsp").forward(request, response);
			return;
		}
		if(apellido.isBlank()) {
			request.setAttribute("message","Apellido es necesario");
			getServletContext().getRequestDispatcher("/WEB-INF/views/crearContacto.jsp").forward(request, response);
			return;
		}
		if(!email.isBlank() && !email.contains("@")) {
			request.setAttribute("message","Email debe tener un @");
			getServletContext().getRequestDispatcher("/WEB-INF/views/crearContacto.jsp").forward(request, response);
			return;
		}
		
		Contacto contacto=new Contacto(nombre,apellido,telefono,email);
		contactoDao.create(contacto);
		
		List<Grupo> listado;
		try {
			listado=Arrays.asList(grupos).stream()
				.map(Long::parseLong)
				.map(grupoDao::getById).toList();
		}catch(NullPointerException e) {
			listado=new ArrayList<Grupo>();
		}
		if(grupos != null && grupos.length > 0 ) {
			grupoContactoDao.updateGroups(contacto,listado);
		}
	
		response.sendRedirect("ListarContacto");
	}
}
