<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.agenda.domain.Contacto"%>

<%
	List<Contacto> listado=(List<Contacto>)request.getAttribute("listado");
%>

<!DOCTYPE html>
<html lang="es">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://unpkg.com/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	<title>Listado de contactos</title>
</head>

<body>
	<div class="container bg-ligth">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container-fluid">
				<a class="navbar-brand" href="<%=request.getContextPath()%>">Agenda</a>
				<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item">
							<a class="nav-link" href="<%=request.getContextPath()%>">Inicio</a>
						</li>
						<li class="nav-item">
							<a class="nav-link active" href="<%=request.getContextPath()%>/ListarContacto">Listado</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="<%=request.getContextPath()%>/CrearContacto">Crear</a>
						</li>

					</ul>
					<div class="d-flex w-75 justify-content-end">
						<form class="d-flex" action="<%=request.getContextPath()%>/BuscarContacto" method="GET">
							<input name="keyword" class="form-control me-2" type="search" placeholder="Buscar Contacto"
								autocomplete="off">
							<button class="btn btn-outline-success" type="submit">Buscar</button>
						</form>
					</div>
				</div>
			</div>
		</nav>
	</div>

	<main>
		<div class="container bg-light mt-5">
			<h3 class="text-center">Listado de contactos</h3>
<%
	if( listado != null && listado.size() > 0 ) { 
%>		
				<table class="table">
					<thead>
						<tr>
					  		<th>Id</th>
							<th>Nombre</th>
							<th>Apellido</th>
							<th>Telefono</th>
							<th>Email</th>
							<th>Accion</th>
					   </tr>
					</thead>
					<tbody>
					<% for(Contacto contacto: listado ) { %>
						<tr>
							<th scope="row"><%=contacto.getId()%></th>
							<td><%=contacto.getNombre()%></td>
							<td><%=contacto.getApellido()%></td>
							<td><%=contacto.getTelefono()%></td>
							<td><%=contacto.getEmail()%></td>
							<td>								
								<div class="btn-group btn-group-sm" role="group" aria-label="Acciones">
									<a class="btn btn-info" 
							      	   role="button" 
							      	   href="<%=request.getContextPath()%>/ActualizarContacto?id=<%=contacto.getId()%>">
							      	   Editar
							      	</a>
									<a
										role="button" 
										class="btn btn-danger" 
										href="<%=request.getContextPath()%>/EliminarContacto?id=<%=contacto.getId()%>"
										onclick="return confirm('Esta seguro que desea eliminar Id=<%=contacto.getId()%> ?');">											
								  		Eliminar
									</a>
								</div>
							</td>
						<tr>
					<% } %>
					</tbody>
				</table>
<% } else { %>
			<p>No se encontraron resultados.</p>
<% } %>
		</div>
	</main>
	</body>	
 <script src="https://unpkg.com/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</html>