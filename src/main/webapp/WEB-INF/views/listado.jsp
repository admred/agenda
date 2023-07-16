<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.agenda.domain.Contacto"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8">
<title>Agenda</title>
</head>
<body>
<nav>
	<a href="/agenda/contacto?accion=crear">Crear</a>
	<a href="/agenda/home.html">Home</a>	
</nav>
<%
	List<Contacto> listado=(List<Contacto>)request.getAttribute("list");
	if( listado != null && listado.size() > 0 ) { 
%>
	<table cellspacing="1" border="1" cellpadding="1">
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Telefono</th>
			<th>Email</th>
			<th>Accion</th>
		</tr>
<%	for(Contacto contacto: listado ) { %>
		<tr>
			<td><%=contacto.getId()%></td>
			<td><%=contacto.getNombre()%></td>
			<td><%=contacto.getApellido()%></td>
			<td><%=contacto.getTelefono()%></td>
			<td><%=contacto.getEmail()%></td>
			<td>
				<a href="/agenda/contacto?accion=eliminar&id=<%=contacto.getId()%>">Eliminar</a>
				<a href="/agenda/contacto?accion=editar&id=<%=contacto.getId()%>">Editar</a>
			</td>
		<tr>
		<% } %>
	</table>
<% } else { %>
	<p>No hay resultados. Pruebe con <a href="/agenda/contacto?accion=crear">crear</a> algunos registros.</p>
<% } %>
<jsp:include page="/WEB-INF/views/errors.jsp"/>

</body>
</html>