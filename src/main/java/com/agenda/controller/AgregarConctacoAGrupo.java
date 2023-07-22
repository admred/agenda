package com.agenda.controller;

import com.agenda.dao.GrupoDao;
import com.agenda.dao.impl.GrupoDAOMysqlImpl;

import java.io.IOException;

import com.agenda.dao.ContactoDao;
import com.agenda.dao.impl.ContactoDAOMysqlImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AgregarConctacoAGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GrupoDao grupoDao=new GrupoDAOMysqlImpl();
	private ContactoDao conctactoDao=new ContactoDAOMysqlImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
