package com.agenda.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.agenda.domain.Contacto;
import com.agenda.dao.ContactoDao;
import com.agenda.dao.impl.ContactoDAOMysqlImpl;


@WebServlet("/BuscarContacto")
public class BuscarContacto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ContactoDao contactoDao=new ContactoDAOMysqlImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Contacto> listado;
			String keyword=(String)request.getParameter("keyword");
			
			try {
				listado=contactoDao.search(keyword);
				request.setAttribute("listado", listado);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/WEB-INF/views/listarContacto.jsp").forward(request, response);
		}	
	}