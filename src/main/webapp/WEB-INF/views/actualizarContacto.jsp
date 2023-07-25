<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.agenda.domain.Contacto, com.agenda.domain.Grupo" %>
<!DOCTYPE html>
<%
	Contacto contacto=(Contacto)request.getAttribute("contacto");
	List<Grupo> grupos=(List<Grupo>)request.getAttribute("grupos");
	List<Grupo> checked=(List<Grupo>)request.getAttribute("checked");
%>

<html lang="es">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://unpkg.com/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	<title>Actualizar contacto</title>
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
							<a class="nav-link" href="<%=request.getContextPath()%>/ListarContacto">Listado</a>
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
			<h3>Actualizar contacto</h3>
			<form action="<%=request.getContextPath()%>/ActualizarContacto" method="POST">
				<input type="hidden" name="id" value="<%=contacto.getId()%>">
				<div class="mb-3">
					<label class="form-label" for="nombre">Nombre</label>
					<input type="text" name="nombre" class="form-control" value="<%=contacto.getNombre()%>" required>
				</div>

				<div class="mb-3">
					<label class="form-label" for="apellido">Apellido</label>
					<input type="text" name="apellido" class="form-control" value="<%=contacto.getApellido()%>" required>
				</div>

				<div class="mb-3">
					<label class="form-label" for="telefono">Telefono</label>
					<input type="text" name="telefono" class="form-control" value="<%=contacto.getTelefono()%>">
				</div>

				<div class="mb-3">
					<label class="form-label" for="email">Email</label>
					<input type="email" name="email" class="form-control" value="<%=contacto.getEmail()%>">
				</div>
				
				<div class="mb-3">			
					<label class="form-label">Grupo</label>
							
					<div class="container-fluid gx-3">
					<% 
						String check="";
						for(Grupo g: grupos) {
							if(checked.contains(g)) {
								check="checked";
							} else {
								check="";
							}
					%>
						<div class="form-check form-check-inline">	
							<input type="checkbox" name="grupo[]" value="<%=g.getId()%>" class="form-check-input" id="<%=g.getNombre()%>" <%=check%>>
							<label class="form-check-label" for="<%=g.getNombre()%>"><%=g.getNombre()%></label>
						</div>
					<% } %>
					</div>
				</div>
				<div class="input-group justify-content-center mt-3">
					<a href="#" onclick="history.back()" role="button" class="btn btn-secondary">Volver</a>
					<input type="submit" value="Enviar" class="btn btn-primary">
				</div>
			</form>
		</div>
	</main>
</body>
<script src="https://unpkg.com/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</html>