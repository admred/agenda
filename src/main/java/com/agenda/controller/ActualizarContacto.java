package com.agenda.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import com.agenda.dao.ContactoDao;
import com.agenda.dao.impl.ContactoDAOMysqlImpl;
import com.agenda.domain.Contacto;

@WebServlet("/ActualizarContacto")
public class ActualizarContacto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ContactoDao contactoDao=new ContactoDAOMysqlImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final Long id=Long.parseLong(request.getParameter("id"));
		Contacto contacto=null;
		
		try {
			contacto=contactoDao.getById(id);
			request.setAttribute("contacto", contacto);
		}catch(Exception ex) {
			ex.printStackTrace();
			getServletContext().getRequestDispatcher("/WEB-INF/views/error404.html").forward(request, response);
			return;
		}	
		getServletContext().getRequestDispatcher("/WEB-INF/views/actualizarContacto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id=null;
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
			id=Long.parseLong((String)request.getAttribute("id"));
			contactoDao.update(new Contacto(id,nombre,apellido,telefono,email));	
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("ListarContacto");
	}
}
