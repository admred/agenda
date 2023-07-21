package com.agenda.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.agenda.domain.Contacto;
import com.agenda.dao.ContactoDao;
import com.agenda.dao.impl.ContactoDAOMysqlImpl;


@WebServlet("/ListarContacto")
public class ListarContacto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ContactoDao contactoDao=new ContactoDAOMysqlImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Contacto> listado;
			List<String> mensajes=new ArrayList<String>();
			try {
				listado=contactoDao.findAll();
				request.setAttribute("listado", listado);
			}catch(Exception ex) {
				mensajes.add(ex.getMessage());
				request.setAttribute("mesajes", mensajes);
			}
			getServletContext().getRequestDispatcher("/WEB-INF/views/listarContacto.jsp").forward(request, response);
		}	
	}