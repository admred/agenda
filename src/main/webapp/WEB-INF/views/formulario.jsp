<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.agenda.domain.Contacto" %>
<%
	List<String> messages=(List<String>)request.getAttribute("messages");
	Contacto contacto=(Contacto)request.getAttribute("contacto");
	String action=(String)request.getAttribute("action");
	String title=action.toUpperCase();
%>
<!DOCTYPE html>
<html>
	<head>
		 <!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>${title}</title>
		 <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">  
	</head>
	<body>
	   <div class="container bg-light">
	   		<jsp:include page="/navbar.jsp"/>
	   </div>
		
		
		<main class="container mt-5">
			<h3>${title}</h3>
			
			<div class="row mt-5">
				<div class="col-12">
					<form class="row g-3 needs-validation" action="<%=request.getContextPath()%>/contacto" method="POST">
						
<% if(action.equals("editar")) {%>
					 	<div class="col-md-0">
					    	<input type="hidden" name="id" value="${contacto.id}">
					  	</div>
<% } %>
						<input type="hidden" name="accion" value="${action}">

					  	<div class="mb-3">
					    	<label for="validationCustom02" class="form-label">Nombre</label>
					    	<input type="text" name="nombre" class="form-control" id="validationCustom02" value="${contacto.nombre}" required>
					    	<div class="valid-feedback">
					  			Looks good!
					    	</div>
					  	</div>
					  	<div class="mb-3">
					    	<label for="validationCustom02" class="form-label">Apellido</label>
					    	<input type="text" name="apellido" class="form-control" id="validationCustom02" value="${contacto.apellido}" required>
					    	<div class="valid-feedback">
					      		Looks good!
					    	</div>	
					  	</div>
					  	<div class="mb-3">
					    	<label for="validationCustom02" class="form-label">Teléfono</label>
					    	<input type="text" name="telefono" class="form-control" id="validationCustom02" value="${contacto.telefono}">
					    	<div class="valid-feedback">
					      		Looks good!
					    	</div>	
					  	</div>
					  	<div class="mb-3">
					    	<label for="validationCustom02" class="form-label">Email</label>
					    	<input type="email" name="email" class="form-control" id="validationCustom02" value="${contacto.email}">
					    	<div class="valid-feedback">
					      		Looks good!
					    	</div>	
					  	</div>
					 
					  	<div class="mb-3">
					    	<button class="btn btn-primary" type="submit">Aceptar</button>
					    	<a href="<%=request.getContextPath()%>/contacto?accion=listado" role="button" class="btn btn-secondary">Cancelar</a>
					  	</div>
					</form>
				</div>
			</div>
		</main>
	</body>
</html>
</html>