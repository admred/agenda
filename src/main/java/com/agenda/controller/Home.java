package com.agenda.controller;

import java.io.IOException;

import com.agenda.dao.ContactoDao;
import com.agenda.dao.GrupoDao;
import com.agenda.dao.impl.ContactoDAOMysqlImpl;
import com.agenda.dao.impl.GrupoDAOMysqlImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GrupoDao grupoDao=new GrupoDAOMysqlImpl();
	
	private ContactoDao contactoDao=new ContactoDAOMysqlImpl();
	

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("cantidadContactos", ""+contactoDao.findAll().size());
		request.setAttribute("cantidadGrupos", ""+grupoDao.findAll().size());
		
		getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request,response);
		
	}
}
