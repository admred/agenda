package com.agenda.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.agenda.dao.ContactoDao;
import com.agenda.dao.impl.ContactoDAOMysqlImpl;
import com.agenda.dao.GrupoDao;
import com.agenda.dao.impl.GrupoDAOMysqlImpl;
import com.agenda.domain.Contacto;
import com.agenda.domain.Grupo;

@WebServlet("/CrearContacto")
public class CrearContacto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ContactoDao contactoDao=new ContactoDAOMysqlImpl();
	private GrupoDao grupoDao=new GrupoDAOMysqlImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Grupo> grupos=null;
		try {
			grupos=grupoDao.findAll();
		} catch (SQLException e) {
			grupos=new ArrayList<Grupo>();
		}
		request.setAttribute("grupos",grupos);
		getServletContext().getRequestDispatcher("/WEB-INF/views/crearContacto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		String email=request.getParameter("email");
		String telefono=request.getParameter("telefono");
		
		if(nombre.isBlank()) {
			throw new ServletException("Nombre es necesario");
		}
		if(apellido.isBlank()) {
			throw new ServletException("Apellido es necesario");
		}
		if(!email.isBlank() && !email.contains("@")) {
			throw new ServletException("Email debe tener un @");
		}
			
		try {
			contactoDao.create(new Contacto(0l,nombre,apellido,telefono,email));
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("ListarContacto");
	}
}
