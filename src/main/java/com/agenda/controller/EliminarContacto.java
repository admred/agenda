package com.agenda.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.agenda.dao.ContactoDao;
import com.agenda.dao.impl.ContactoDAOMysqlImpl;

@WebServlet("/EliminarContacto")
public class EliminarContacto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ContactoDao contactoDao=new ContactoDAOMysqlImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id=null;
		
		try {
			id=Long.parseLong(request.getParameter("id"));
			contactoDao.delete(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("ListarContacto");
	}
}
