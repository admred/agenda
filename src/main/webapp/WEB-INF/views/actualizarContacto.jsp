<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.agenda.domain.Contacto" %>

<%
	Contacto contacto=(Contacto)request.getAttribute("contacto");
%>
<!DOCTYPE html>
<html lang="es">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://unpkg.com/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	<title>Actualizar contacto</title>
</head>

<body>
	<div class="container bg-ligth">
	
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">
				<a class="navbar-brand" href="<%=request.getContextPath()%>/home.jsp">Agenda</a>
				<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item">
							<a class="nav-link" href="<%=request.getContextPath()%>/WEB-INF/views/home.html">Inicio</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="<%=request.getContextPath()%>/ListarContacto">Listado</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="<%=request.getContextPath()%>/WEB-INF/views/crearContacto.html">Crear</a>
						</li>

					</ul>
					<div class="d-flex w-75 justify-content-end">
						<form class="d-flex" action="<%=request.getContextPath()%>/BuscarContacto" method="GET">
							<input name="keyword" class="form-control me-2" type="search" placeholder="Buscar Contacto"
								aria-label="Search">
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

				<input type="submit" value="Enviar" class="btn btn-primary">
				<a href="#" onclick="history.back()" role="button" class="btn btn-secondary">Volver</a>
			</form>
		</div>
	</main>
</body>
<script href="https://unpkg.com/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</html>