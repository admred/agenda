<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<%
	String cantidadContactos=(String)request.getAttribute("cantidadContactos");
	String cantidadGrupos=(String)request.getAttribute("cantidadGrupos");
%>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://unpkg.com/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	<title>Inicio</title>
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
							<a class="nav-link active" href="<%=request.getContextPath()%>">Inicio</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="<%=request.getContextPath()%>/ListarContacto">Contactos</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="<%=request.getContextPath()%>/ListarGrupo">Grupos</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	</div>

	<main>
		<div class="container mt-3">
			<div class="row justify-content-evenly">
				<div class="col-sm-3 ">
						<div class="card mt-3" style="">
							<div class="card-header">Contactos</div>
							<p class="text-center fs-1 mt-5"><%=cantidadContactos%></p>
							<div class="card-body">
		 							<p class="card-text">Datos de sobres contactos, el nombre, apellido, telefono y email.</p>
		 							<a href="<%=request.getContextPath()%>/ListarContacto" class="btn btn-primary">Listado</a>
		 							<a href="<%=request.getContextPath()%>/CrearContacto" class="btn btn-success">Crear</a>
							</div>
						</div>
				</div>
				<div class="col-sm-3 ">
						<div class="card mt-3" style="">
							<div class="card-header">Grupos</div>
							<p class="text-center fs-1 mt-5"><%=cantidadGrupos%></p>
							<div class="card-body">
		 							<p class="card-text">Agrupamiento de contactos, ej: familia, estudio, trabajo, etc</p>
									<a href="<%=request.getContextPath()%>/ListarGrupo" class="btn btn-primary">Listado</a>
									<a href="<%=request.getContextPath()%>/CrearGrupo" class="btn btn-success">Crear</a>
							</div>
						</div>
				</div>
			</div>
		</div>
	</main>
</body>
<script src="https://unpkg.com/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</html>
