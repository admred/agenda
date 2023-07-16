<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.agenda.domain.Contacto" %>
<%
	List<String> messages=(List<String>)request.getAttribute("messages");
	String title=(String)request.getAttribute("title");
	Contacto contacto=(Contacto)request.getAttribute("contacto");
	String action=(String)request.getAttribute("action");
	String url=action;
	if(action.equals("editar")){
		url="editar&id="+contacto.getId();	
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${accion}</title>
</head>
<body>
<h4>${title}</h4>
	<form method="POST" action="${url}">
		<table cellspacing="5">
			<tr><th>Nombre</th><td><input type="text" name="nombre" value="${contacto.nombre}" required></td></tr>
			<tr><th>Apellido</th><td><input type="text" name="apellido" value="${contacto.apellido}" required></td></tr>
			<tr><th>Telefono</th><td><input type="text" name="telefono" value="${contacto.telefono}"></td></tr>
			<tr><th>Email</th><td><input type="email" name="email" value="${contacto.email}"></td></tr>
			<tr><th></th><td>
				<input type="submit" value="Acceptar">
				<a href="/agenda/contacto?accion=listado">Cancelar</a>
				<% if(action.equals("editar")) { %>
					<a href="/agenda/contacto?accion=eliminar&id=${contacto.id}">Eliminar</a>
				<% } %>
				</td>
			</tr>
		</table>
	</form>
	
	<% if( messages != null && messages.size() > 0 ) { %>
		<% for(String m:messages) {%>
		<p bgcolor="#FF0000">${m}</p>
		<% } %>
	<% } %>
</body>
</html>