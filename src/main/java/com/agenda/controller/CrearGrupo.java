package com.agenda.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.agenda.dao.GrupoDao;
import com.agenda.dao.impl.GrupoDAOMysqlImpl;
import com.agenda.domain.Grupo;

@WebServlet("/CrearGrupo")
public class CrearGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GrupoDao grupoDao=new GrupoDAOMysqlImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		getServletContext().getRequestDispatcher("/WEB-INF/views/crearGrupo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre=request.getParameter("nombre");
		String descripcion=request.getParameter("descripcion");
		
		if(nombre.isBlank()) {
			throw new ServletException("Nombre es necesario");
		}
			
		try {
			grupoDao.create(new Grupo(0l,nombre,descripcion));
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("ListarGrupo");
	}
}
