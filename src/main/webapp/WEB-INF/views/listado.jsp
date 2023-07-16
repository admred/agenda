<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.agenda.domain.Contacto"%>
<!DOCTYPE html>
<html lang="es">
 <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--animated-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <!--logos-->
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    
    <title>Departamentos Listado</title>
  </head>
  <body>
	<!-- ACA VA EL NAVBAR  include file="navbar.jsp" %> -->
	<div class="container bg-ligth">
	 	<!-- aca va el navbar.jsp -->
		<jsp:include page="/navbar.jsp"/>
	</div>
		
	<main>
		<div class="container bg-light mt-5">
<%
	List<Contacto> listado=(List<Contacto>)request.getAttribute("list");
	if( listado != null && listado.size() > 0 ) { 
%>		
			<section>	
				<h1>Listado de Contactos</h1>
				<table class="table">
					<thead class="table">
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
								<a class="btn btn-info" 
						      	   role="button" 
						      	   href="<%=request.getContextPath()%>/contacto?accion=editar&id=<%=contacto.getId()%>">
						      	   Editar
						      	</a> | 
					      		<!-- Button trigger modal -->
								<button type="button" class="btn btn-danger" 
									data-bs-toggle="modal" 
									data-bs-target="#exampleModal" 
									onclick="setContactoId(<%=contacto.getId()%>)">
								  Eliminar
								</button>
							  </td>
						<tr>
					<% } %>
					</tbody>
				</table>
			</section>
<% } else { %>
			<p>No se encontraron resultados.</p>
<% } %>
		</div>
		
	</main>
	<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		    	<form action="<%=request.getContextPath()%>/contacto" method="GET">
		    	  <input type="hidden" name="accion" value="eliminar">
		    	  <input type="hidden" name="id" id="idContacto">
		    	  
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">Eliminar Departamento</h5>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			        ¿Confirma que desea eliminar? 
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
			        <button type="submit" class="btn btn-danger">Eliminar</button>
			      </div>
		    	</form>
		    </div>
		  </div>
		</div>
	</body>	
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
 <script>
	function setContactoId(id) {
		document.getElementById('idContacto').value=id;
	}
 </script>
</html>