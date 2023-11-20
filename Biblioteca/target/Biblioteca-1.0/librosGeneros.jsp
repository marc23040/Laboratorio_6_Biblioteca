<%@page import="com.umariana.biblioteca.PersistenciaArchivo"%>
<%@page import="com.umariana.biblioteca.Biblioteca"%>
<%@page import="com.umariana.biblioteca.Metodos"%>
<%@include file= "templates/header.jsp" %>
<%@include file= "templates/lateral.jsp" %>
<%@include file= "templates/navbar.jsp" %>
<style>
    /* Contenedor de la imagen con estilo de posición relativa y fondo */
    .image-container {
      position: relative;
      background-color: #f8f1e9; border-radius: 3.3rem; height: 400px; width: 100%; align-items: center;
    }
    /* Estilo de la imagen dentro del contenedor */
    .image-container img {
      width: 100%;     
      height: 50%;
      background-color: #f8f1e9; border-radius: 3.3rem; height: 400px; width: 100%;
    }
    /* Capa superpuesta para el efecto de superposición */
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
    /* Texto superpuesto con estilo de posición absoluta y centrado */
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
    // Obtener el contexto del servlet
    ServletContext context = getServletContext();
    // Inicializar la cadena de la tabla
    String tabla = "";
    // Obtener el parámetro "genero"
    String genero = request.getParameter("genero");

    // Crear una instancia de la clase Biblioteca
     Biblioteca libros= new Biblioteca();
    // Deserializar la biblioteca desde el contexto del servlet
        libros=PersistenciaArchivo.deserializarBiblioteca(context);
        // Verificar si la biblioteca es nula y crear una nueva si es necesario
        if(libros==null){
            libros= new Biblioteca();
        }
        // Obtener la tabla de libros para el género especificado
       tabla=  libros.librosGenero(genero);
        // Obtener el parámetro de búsqueda "buscar"
        String search = request.getParameter("buscar");
        
        // Verificar si hay un parámetro de búsqueda y actualizar la tabla con la búsqueda
        if(search!=null){
         tabla=libros.librosGeneroBuscar(genero, search);
        }

    
%>
<br>


  
<div class="container" style=" padding-bottom: 81px; min-height: calc(100% - 81px); position: relative;">
     <!-- Contenedor de la imagen del género con superposición y texto -->
    <div class="image-container">
        <!-- Imagen del género dinámicamente determinada por el parámetro "genero" -->
       <img src="img/<%=genero%>.jpg">
       <!-- Capa de superposición con estilo -->
    <div class="overlay"></div>
    <!-- Texto superpuesto con el nombre del género -->
    <h1 class="overlay-text"><%=genero%></h1>
</div>

        <br>
     <%@include file= "templates/Generos.jsp" %> 
     <!-- Formulario para buscar y ordenar libros por género -->
    <form class="d-flex" action="SvBuscarOrdenar" method="POST" style="margin-top: 1%;">
        <div class="input-group">   
            <!-- Campo de búsqueda -->
            <input class="form-control me-2" name="buscarGeneros" type="search" placeholder="  Busca tu libro" aria-label="Search"style="  
                   -moz-border-radius: 10px;-webkit-border-radius: 40px;border-radius: 40px;border: 1px solid #000000;height: 60px;padding: 0 4px 0 4px;">
              <!-- Campo oculto con el valor del género -->
            <input class="form-control me-2" name="genero" value="<%=request.getParameter("genero")%>" hidden>
        </div>
        
        <!-- Botón de búsqueda -->
        <button class="btn btn-outline-success" type="submit" style=" background-color: #5e3824;-moz-border-radius: 10px; border-radius: 
                50%; -webkit-border-radius: 60px;margin-left: 10px;"><i class="fa-solid fa-arrow-right" style="color: #ffffff;"></i></button>
    </form>
        <br>
   <!-- Filas de tarjetas de libros -->

   <%=tabla%>
<br>

</div>
<%@include file= "templates/footer2.jsp" %> 
<%@include file= "templates/footer.jsp" %> 
