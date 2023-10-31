<%@page import="com.umariana.biblioteca.PersistenciaArchivo"%>
<%@page import="com.umariana.biblioteca.Biblioteca"%>
<%@page import="com.umariana.biblioteca.Metodos"%>
<%@include file= "templates/header.jsp" %>
<%@include file= "templates/lateral.jsp" %>
<%@include file= "templates/lateral.jsp" %>
<nav class="navbar navbar-expand-lg">
    <div class="container-fluid">

        <button class="btn icono" data-bs-toggle="offcanvas" data-bs-target="#intro"><i class="fa-solid fa-address-book"></i></button>

        <a href="login.jsp"><h1 class="navbar-brand" >Biblioteca online</h1></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
         <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
          <li class="nav-item">
          <a class="nav-link " href="biblioteca.jsp" role="button" >
            Libros
          </a>

        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Ver
          </a>
          <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="SvBuscarOrdenar?ordenar=alquilados&cedula=<%=session.getAttribute("cedula")%>">Mis libros alquilados</a></li>
            <li><a class="dropdown-item" href="SvBuscarOrdenar?ordenar=disponibles">Libros disponibles</a></li>
          </ul>

        </li>
        <li class="nav-item dropdown">

          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Ordenar
          </a>
          <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="SvBuscarOrdenar?ordenar=antiguos">Libros mas antiguos</a></li>
            <li><a class="dropdown-item" href="SvBuscarOrdenar?ordenar=recientes">Libros mas recientes</a></li>
          </ul>
        </li>
      
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Generos
          </a>
          <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="SvBuscarOrdenar?genero=Aventuras">Aventuras</a></li>
              <li><a class="dropdown-item" href="SvBuscarOrdenar?genero=Ciencia Ficcion">Ciencia Ficción</a></li>
              <li><a class="dropdown-item" href="SvBuscarOrdenar?genero=Romance">Romance</a></li>
              <li><a class="dropdown-item" href="SvBuscarOrdenar?genero=Fantasia">Fantasia</a></li>
              <li><a class="dropdown-item" href="SvBuscarOrdenar?genero=Humor">Humor</a></li>
              <li><a class="dropdown-item" href="SvBuscarOrdenar?genero=Poesia">Poesia</a></li>
              <li><a class="dropdown-item" href="SvBuscarOrdenar?genero=Mitologia">Mitologia</a></li>
              <li><a class="dropdown-item" href="SvBuscarOrdenar?genero=Teatro">Teatro</a></li>
              <li><a class="dropdown-item" href="SvBuscarOrdenar?genero=Infantil">Infantil</a></li>
              <li><a class="dropdown-item" href="SvBuscarOrdenar?genero=Escolar">Escolar</a></li>       
          </ul>

        </li>
        </ul>
    </div>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <form class="d-flex" action="SvBuscarOrdenar" method="POST">
                <input class="form-control me-2" name="buscarGeneros" type="search" placeholder="Busca tu libro" aria-label="Search">
                <input class="form-control me-2" name="genero" value="<%=request.getParameter("genero")%>" hidden>
                <button class="btn btn-outline-success" type="submit">Buscar</button>
            </form>
        </div>


    </div>
</nav>
<header><%@include file= "styles/stylelogin.jsp" %></header>

<%
    ServletContext context = getServletContext();
   
    String tabla = "";
 
    String genero = request.getParameter("genero");

 
     Biblioteca libros= new Biblioteca();
        libros=PersistenciaArchivo.deserializarBiblioteca(context);
        if(libros==null){
            libros= new Biblioteca();
        }
       tabla=  libros.librosGenero(genero);
        String search = request.getParameter("buscar");

        if(search!=null){
         tabla=libros.librosGeneroBuscar(genero, search);
        }

    
%>

<div class="container p-4">
   <div class="row row-cols-1 row-cols-md-3 g-4">
   <%=tabla%>

    </div>
</div>

<%@include file= "templates/footer.jsp" %> 
