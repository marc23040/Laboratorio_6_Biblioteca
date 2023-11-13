<%@page import="com.umariana.biblioteca.PersistenciaArchivo"%>
<%@page import="com.umariana.biblioteca.Biblioteca"%>
<%@page import="com.umariana.biblioteca.Metodos"%>
<%@include file= "templates/header.jsp" %>
<%@include file= "templates/lateral.jsp" %>
<%@include file= "templates/navbar.jsp" %>
<style>
    
    .image-container {
      position: relative;
      background-color: #f8f1e9; border-radius: 3.3rem; height: 400px; width: 100%; align-items: center;
    }

    .image-container img {
      width: 100%;     
      height: 50%;
      background-color: #f8f1e9; border-radius: 3.3rem; height: 400px; width: 100%;
    }

    .overlay {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0, 0, 0, 0.3);
      pointer-events: none; 
      border-radius: 3.3rem;
    }

    .overlay-text {
      position: absolute;
      top: 50%; 
      left: 50%; 
      transform: translate(-50%, -50%); 
      color: white; 
    }
</style>

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
<br>


  
<div class="container p-4">
    
    <div class="image-container">
       <img src="img/<%=genero%>.jpg">
    <div class="overlay"></div>
    <h1 class="overlay-text"><%=genero%></h1>
</div>

        <br>
     <%@include file= "templates/Generos.jsp" %> 
    <form class="d-flex" action="SvBuscarOrdenar" method="POST" style="margin-top: 1%;">
        <div class="input-group">    
            <input class="form-control me-2" name="buscarGeneros" type="search" placeholder="  Busca tu libro" aria-label="Search"style="  -moz-border-radius: 10px;-webkit-border-radius: 40px;border-radius: 40px;border: 1px solid #000000;height: 60px;padding: 0 4px 0 4px;">
            <input class="form-control me-2" name="genero" value="<%=request.getParameter("genero")%>" hidden>
        </div>
        <button class="btn btn-outline-success" type="submit" style=" background-color: #5e3824;-moz-border-radius: 10px; border-radius: 50%; -webkit-border-radius: 60px;margin-left: 10px;"><i class="fa-solid fa-arrow-right" style="color: #ffffff;"></i></button>
    </form>
        <br>
   <div class="row row-cols-1 row-cols-md-3 g-4">
   <%=tabla%>

    </div>
</div>

<%@include file= "templates/footer.jsp" %> 
