<%@page import="com.umariana.biblioteca.PersistenciaArchivo"%>
<%@page import="com.umariana.biblioteca.Biblioteca"%>
<%@page import="com.umariana.biblioteca.Metodos"%>
<%@include file= "templates/header.jsp" %>
<%@include file= "templates/lateral.jsp" %>
<%@include file= "templates/navbar.jsp" %>
<style>
    /* Contenedor de la imagen con estilo de posici�n relativa y fondo */
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
    /* Capa superpuesta para el efecto de superposici�n */
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
    /* Texto superpuesto con estilo de posici�n absoluta y centrado */
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
    // Obtener el par�metro "genero"
    String genero = request.getParameter("genero");

    // Crear una instancia de la clase Biblioteca
     Biblioteca libros= new Biblioteca();
    // Deserializar la biblioteca desde el contexto del servlet
        libros=PersistenciaArchivo.deserializarBiblioteca(context);
        // Verificar si la biblioteca es nula y crear una nueva si es necesario
        if(libros==null){
            libros= new Biblioteca();
        }
        // Obtener la tabla de libros para el g�nero especificado
       tabla=  libros.librosGenero(genero);
        // Obtener el par�metro de b�squeda "buscar"
        String search = request.getParameter("buscar");
        
        // Verificar si hay un par�metro de b�squeda y actualizar la tabla con la b�squeda
        if(search!=null){
         tabla=libros.librosGeneroBuscar(genero, search);
        }

    
%>
<br>


  
<div class="container" style=" padding-bottom: 81px; min-height: calc(100% - 81px); position: relative;">
     <!-- Contenedor de la imagen del g�nero con superposici�n y texto -->
    <div class="image-container">
        <!-- Imagen del g�nero din�micamente determinada por el par�metro "genero" -->
       <img src="img/<%=genero%>.jpg">
       <!-- Capa de superposici�n con estilo -->
    <div class="overlay"></div>
    <!-- Texto superpuesto con el nombre del g�nero -->
    <h1 class="overlay-text"><%=genero%></h1>
</div>

        <br>
     <%@include file= "templates/Generos.jsp" %> 
     <!-- Formulario para buscar y ordenar libros por g�nero -->
    <form class="d-flex" action="SvBuscarOrdenar" method="POST" style="margin-top: 1%;">
        <div class="input-group">   
            <!-- Campo de b�squeda -->
            <input class="form-control me-2" name="buscarGeneros" type="search" placeholder="  Busca tu libro" aria-label="Search"style="  
                   -moz-border-radius: 10px;-webkit-border-radius: 40px;border-radius: 40px;border: 1px solid #000000;height: 60px;padding: 0 4px 0 4px;">
              <!-- Campo oculto con el valor del g�nero -->
            <input class="form-control me-2" name="genero" value="<%=request.getParameter("genero")%>" hidden>
        </div>
        
        <!-- Bot�n de b�squeda -->
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
