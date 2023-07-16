<%@page import="java.util.List" %>
<% List<String> messages=(List<String>)request.getAttribute("messages"); %>
<% if( messages != null && messages.size() > 0 ) { %>
	<% for(String m:messages) {%>
		<p bgcolor="#FF0000">${m}</p>
	<% } %>
<% } %>