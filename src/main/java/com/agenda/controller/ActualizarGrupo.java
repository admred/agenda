package com.agenda.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import com.agenda.dao.GrupoDao;
import com.agenda.dao.impl.GrupoDAOMysqlImpl;
import com.agenda.domain.Grupo;

@WebServlet("/ActualizarGrupo")
public class ActualizarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GrupoDao grupoDao=new GrupoDAOMysqlImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final Long id=Long.parseLong(request.getParameter("id"));
		Grupo grupo=null;
		
		try {
			grupo=grupoDao.getById(id);
			request.setAttribute("grupo", grupo);
		}catch(Exception ex) {
			ex.printStackTrace();
			getServletContext().getRequestDispatcher("/WEB-INF/views/error404.html").forward(request, response);
			return;
		}	
		getServletContext().getRequestDispatcher("/WEB-INF/views/actualizarGrupo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			grupoDao.update(new Grupo(
					Long.parseLong(request.getParameter("id")),
					(String) request.getParameter("nombre"),
					(String) request.getParameter("descripcion")
				));
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("ListarGrupo");
	}
}
