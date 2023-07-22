package com.agenda.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.agenda.domain.Grupo;
import com.agenda.dao.GrupoDao;
import com.agenda.dao.impl.GrupoDAOMysqlImpl;


@WebServlet("/ListarGrupo")
public class ListarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GrupoDao grupoDao=new GrupoDAOMysqlImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Grupo> listado;
			
			try {
				listado=grupoDao.findAll();
				request.setAttribute("listado", listado);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/WEB-INF/views/listarGrupo.jsp").forward(request, response);
		}	
	}