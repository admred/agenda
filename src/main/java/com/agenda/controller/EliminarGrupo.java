package com.agenda.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.agenda.dao.GrupoDao;
import com.agenda.dao.impl.GrupoDAOMysqlImpl;

@WebServlet("/EliminarGrupo")
public class EliminarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private GrupoDao grupoDao=new GrupoDAOMysqlImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id=null;
		
		try {
			id=Long.parseLong(request.getParameter("id"));
			grupoDao.delete(grupoDao.getById(id));
		}catch(Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("ListarGrupo");
	}
}
