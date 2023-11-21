<%@page import="com.umariana.biblioteca.PersistenciaArchivo"%>
<%@page import="com.umariana.biblioteca.Metodos"%>
<%@page import="com.umariana.biblioteca.Biblioteca"%>
<%@include file= "templates/header.jsp" %> <!-- Incluir header -->
<%@include file= "templates/lateral.jsp" %> <!-- Incluir lateral -->
<%@include file= "templates/navbar.jsp" %> <!-- Incluir navbar-->

<!-- Incluir los styles -->
<header><%@include file= "styles/stylelogin.jsp" %></header>

<%
    // Obtiene el contexto del servlet
    ServletContext context = getServletContext();
    // Crea una instancia de la clase Biblioteca
    Biblioteca libros = new Biblioteca();
    // Deserializa la Biblioteca desde el contexto del servlet
    libros = PersistenciaArchivo.deserializarBiblioteca(context);
    // Si la deserialización no fue exitosa (no hay datos guardados), crea una nueva instancia de Biblioteca        
    if (libros == null) {
        libros = new Biblioteca();
    }
    // Inicializa una cadena vacía para la tabla
    String tabla = "";
    // Obtiene la representación de la tabla llamando al método 'tabla()' de la instancia de Biblioteca
    tabla = libros.tabla();
    // Obtiene el parámetro 'alert' de la solicitud HTTP
    String añadido = request.getParameter("alert");
    
    // Si el parámetro 'alert' es igual a "anadido"
    if (añadido != null && añadido.equals("anadido")) {
%>
<script>
    // Cuando el documento esté listo (evento ready)
    $(document).ready(function () {
        // Llama a la función 'añadido()'
        añadido();
    });

</script>

<%
    }
%>

<div class="container p-4">
    <!-- Cuadro de estilo para la sección "AÑADIR LIBROS" -->
    <div class="box">
        <center>
            <!-- Encabezado "AÑADIR LIBROS" -->
            <br>
            <h1 style="font-size: 4rem; font-weight: bold;"> AÑADIR LIBROS</h1>
            <br>
        </center>
    </div>
    <!-- Contenedor para el formulario de añadir libros -->
    <br>
    <div style="justify-content: center; align-items: center; display: flex; height: 400px;">
        <div class="card" style="width: 70%; height: 350px; ">

            <div class="card-body">
                <!-- Formulario para añadir libros -->
                <form action="SvAnadirVer" method="POST" enctype="multipart/form-data">
                    <!-- Campo de entrada para el título -->
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Titulo</span>
                        <input type="text"   name="titulo" class="form-control" placeholder="Titulo" aria-label="Username" aria-describedby="basic-addon1" required>
                    </div>

                    <!-- Campo de entrada para el autor -->
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Autor</span>
                        <input type="text" name="autor" class="form-control" placeholder="Autor" aria-label="Username" aria-describedby="basic-addon1" required>
                    </div>
                    <!-- Campo de entrada para el año -->
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Año</span>
                        <input type="number" name="anio" class="form-control" placeholder="Año" aria-describedby="basic-addon1" required>
                    </div>
                    <!-- Campo de selección para el género -->
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Genero</span>
                        <select class="form-select" name="genero" aria-label="Default select example" required>
                            <option selected></option>
                            <option value="Aventuras">Aventuras</option>
                            <option value="Ciencia Ficcion">Ciencia Ficción</option>
                            <option value="Romance">Romance</option>
                            <option value="Fantasia">Fantasia</option>
                            <option value="Humor">Humor</option>
                            <option value="Poesia">Poesia</option>
                            <option value="Mitologia">Mitologia</option>
                            <option value="Teatro">Teatro</option>
                            <option value="Infantil">Infantil</option>
                            <option value="Escolar">Escolar</option>
                        </select>
                    </div>
                    <!-- Campo de entrada para la portada (archivo) -->
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="fotoPortada">Portada:</label>
                        <input type="file" name="fotoPortada" class="form-control" id="fotoPortada" accept="image/*" required>
                    </div>
                    <!-- Botón para enviar el formulario -->
                    <center>
                        <button class="btn submit-acciones" type="submit" style="background-color: #a86b4c;">Subir libro</button>
                    </center>
                </form>
            </div>
        </div>
    </div>
</div>
<br>    

<!-- Librerias sweet alert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>

<!-- Script necesarios y sweet alert-->
<script src="scripts/script_login.js"></script>

<script>
    // Definición de la función 'añadido()'
    function añadido() {
        // Utiliza la librería Swal para mostrar una notificación de éxito
        Swal.fire({
            icon: 'success', // Icono de éxito
            title: 'Añadido exitosamente!', // Título de la notificación
            text: '¡Puedes verlo en la pagina de gestionar!', // Texto de la notificación
            showConfirmButton: false, // Nos muestra el botón de confirmación
            timer: 1500 // Tiempo de duración de la notificación (en milisegundos)
        })
    }
</script>

<!-- Pie de pagina -->
<%@include file= "templates/footer2.jsp" %> 
<%@include file= "templates/footer.jsp" %> <!-- Incluir footer -->

