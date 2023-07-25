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

@WebServlet("/ActualizarGrupo")
public class ActualizarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GrupoDao grupoDao=new GrupoDAOMysqlImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final Long id;
		Grupo grupo=null;
		
		try {	
			id=Long.parseLong(request.getParameter("id"));
		} catch (Exception e) {
			request.setAttribute("messages","id no valido");
			getServletContext().getRequestDispatcher("/WEB-INF/views/listarGrupo.jsp").forward(request, response);
			return;
		}
		
		grupo=grupoDao.getById(id);
		request.setAttribute("grupo", grupo);
		
		getServletContext().getRequestDispatcher("/WEB-INF/views/actualizarGrupo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id;
		try {	
			id=Long.parseLong(request.getParameter("id"));
		} catch (Exception e) {
			request.setAttribute("messages","id no valido");
			getServletContext().getRequestDispatcher("/WEB-INF/views/listarGrupo.jsp").forward(request, response);
			return;
		}
		
		String nombre=request.getParameter("nombre");
		String descr=request.getParameter("descripcion");
		
		if(nombre.isBlank()) {
			request.setAttribute("messages","nombre no puede ser vacio");
			getServletContext().getRequestDispatcher("/WEB-INF/views/listarGrupo.jsp").forward(request, response);
			return;
		}
		grupoDao.update(new Grupo(id,nombre,descr));
		
		response.sendRedirect("ListarGrupo");
	}
}
