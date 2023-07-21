<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.agenda.utils.Field, com.agenda.utils.Form" %>

<%
	List<String> messages=(List<String>)request.getAttribute("messages");
	String accion=(String)request.getAttribute("action");
	Form form=(Form)request.getAttribute("form"); 
	List<Field> fields=form.fields();
	
%>

<%@ include file="/WEB-INF/views/start_html.html"%>			
				
				<div class="col-12">

					<jsp:include page="/WEB-INF/views/breadcrum.jsp"/>

					<form action="<%=form.action()%>" method="<%=form.method()%>">
						<% if(id != null) { %>  
						<input type="hidden" name="id" value="<%=id%>">
						<% } %>
						<% for(Field f : fields ) { %>
						<div class="mb-3">
							<label class="form-label" for="<%=f.name()%>""><%=f.title()%></label>
							<input type="<%=f.type()%>" name="<%=f.name()%>" id="<%=f.name()%>" value="<%=f.value()%>" class="form-control" required="<%=f.required()%>">
						</div>
						<% } %>
						<input type="submit" value="Enviar" class="btn btn-primary">
						<a href="#" onclick="history.back()" role="button" class="btn btn-secondary">Volver</a>
					</form>
				</div>
				
<%@ include file="/WEB-INF/views/end_html.html"%>
			