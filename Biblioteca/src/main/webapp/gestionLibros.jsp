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
    // Si la deserializaci�n no fue exitosa (no hay datos guardados), crea una nueva instancia de Biblioteca        
    if (libros == null) {
        libros = new Biblioteca();
    }
    // Inicializa una cadena vac�a para la tabla
    String tabla = "";
    // Obtiene la representaci�n de la tabla llamando al m�todo 'tabla()' de la instancia de Biblioteca
    tabla = libros.tabla();
    // Obtiene el par�metro 'alert' de la solicitud HTTP
    String a�adido = request.getParameter("alert");
    
    // Si el par�metro 'alert' es igual a "anadido"
    if (a�adido != null && a�adido.equals("anadido")) {
%>
<script>
    // Cuando el documento est� listo (evento ready)
    $(document).ready(function () {
        // Llama a la funci�n 'a�adido()'
        a�adido();
    });

</script>

<%
    }
%>

<div class="container p-4">
    <!-- Cuadro de estilo para la secci�n "A�ADIR LIBROS" -->
    <div class="box">
        <center>
            <!-- Encabezado "A�ADIR LIBROS" -->
            <br>
            <h1 style="font-size: 4rem; font-weight: bold;"> A�ADIR LIBROS</h1>
            <br>
        </center>
    </div>
    <!-- Contenedor para el formulario de a�adir libros -->
    <br>
    <div style="justify-content: center; align-items: center; display: flex; height: 400px;">
        <div class="card" style="width: 70%; height: 350px; ">

            <div class="card-body">
                <!-- Formulario para a�adir libros -->
                <form action="SvAnadirVer" method="POST" enctype="multipart/form-data">
                    <!-- Campo de entrada para el t�tulo -->
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Titulo</span>
                        <input type="text"   name="titulo" class="form-control" placeholder="Titulo" aria-label="Username" aria-describedby="basic-addon1" required>
                    </div>

                    <!-- Campo de entrada para el autor -->
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Autor</span>
                        <input type="text" name="autor" class="form-control" placeholder="Autor" aria-label="Username" aria-describedby="basic-addon1" required>
                    </div>
                    <!-- Campo de entrada para el a�o -->
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">A�o</span>
                        <input type="number" name="anio" class="form-control" placeholder="A�o" aria-describedby="basic-addon1" required>
                    </div>
                    <!-- Campo de selecci�n para el g�nero -->
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Genero</span>
                        <select class="form-select" name="genero" aria-label="Default select example" required>
                            <option selected></option>
                            <option value="Aventuras">Aventuras</option>
                            <option value="Ciencia Ficcion">Ciencia Ficci�n</option>
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
                    <!-- Bot�n para enviar el formulario -->
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
    // Definici�n de la funci�n 'a�adido()'
    function a�adido() {
        // Utiliza la librer�a Swal para mostrar una notificaci�n de �xito
        Swal.fire({
            icon: 'success', // Icono de �xito
            title: 'A�adido exitosamente!', // T�tulo de la notificaci�n
            text: '�Puedes verlo en la pagina de gestionar!', // Texto de la notificaci�n
            showConfirmButton: false, // Nos muestra el bot�n de confirmaci�n
            timer: 1500 // Tiempo de duraci�n de la notificaci�n (en milisegundos)
        })
    }
</script>

<!-- Pie de pagina -->
<%@include file= "templates/footer2.jsp" %> 
<%@include file= "templates/footer.jsp" %> <!-- Incluir footer -->

