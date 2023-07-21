<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item" aria-current="Home">Home</li>
 <% if(accion != null) { %>
    <li class="breadcrumb-item" aria-current="action"><%=action%></li>
 <% if(id != null) { %>
   	<li class="breadcrumb-item active" aria-current="id">#<%=id%></li>
 <% }} %>
  </ol>
</nav>
