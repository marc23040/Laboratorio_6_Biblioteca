<%@page import="com.umariana.biblioteca.PersistenciaArchivo"%>
<%@page import="com.umariana.biblioteca.Metodos"%>
<%@page import="com.umariana.biblioteca.Biblioteca"%>
<%@include file= "templates/header.jsp" %> <!-- Incluir header -->
<%@include file= "templates/lateral.jsp" %> <!-- Incluir lateral -->
<%@include file= "templates/navbar.jsp" %> <!-- Incluir navbar-->

<!-- Incluir los styles -->
<header><%@include file= "styles/stylelogin.jsp" %></header>

<!-- Sección de scripts que controlan las notificaciones -->
<%
    ServletContext context = getServletContext();

// Parámetros de la solicitud HTTP
    String ordenarr = request.getParameter("ordenar"); //Parametro para ordenar/filtrar (Genero y tiempo)
    String buscar = request.getParameter("buscar");//Parametro para el termino de busqueda
    String penalizaciones = request.getParameter("penalizacion");//Parametro para obtener penalizacion para alert
    String alert = request.getParameter("alert");//Parametro de alerta
    String ced = request.getParameter("cedula");//Parametro de cedula para libros personales
    String cambio = request.getParameter("cambio");//Parametro para cambio de contraseña

    // Construcción de la tabla mediante el metodo listar
    String tabla = "";
    tabla = Metodos.listar(context, ordenarr, ced, buscar);

    //Mensajes que va a cambiar segun el parametro de busqueda
    String mensaje1 = "TODOS LOS LIBROS";
    String mensaje2 = "que desees";

    // Condicionales para asignar mensajes
    if (ordenarr != null && ordenarr.equals("alquilados")) { //Caso filtrar libros alquilados
        mensaje1 = "LIBROS PRESTADOS";
        mensaje2 = "no olvides regresarlos.";
    } else if (ordenarr != null && ordenarr.equals("disponibles")) { //Caso filtrar libros disponibles
        mensaje1 = "LIBROS DISPONIBLES";
        mensaje2 = "tomalos antes que alguien más lo haga.";
    } else if (ordenarr != null && ordenarr.equals("antiguos")) { //Caso filtrar libros mas antiguos
        mensaje1 = "LIBROS MAS ANTIGUOS";
        mensaje2 = "seccion de libros antiguos.";
    } else if (ordenarr != null && ordenarr.equals("recientes")) { //Caso filtrar libros mas nuevos
        mensaje1 = "LIBROS MÁS NUEVOS";
        mensaje2 = "seccion de libros recientes.";
    }

    /**
     * Manejo de alertas con condicionales
     */
    if (alert != null && alert.equals("prestado")) { //Prestamo exitoso
%>
<script>
    $(document).ready(function () {
        prestado();
    });
</script>
<%  } else if (alert != null && alert.equals("noPrestado")) { //Prestamo no exitoso
%>
<script>
    $(document).ready(function () {
        noPrestado();
    });
</script>
<% } else if (alert != null && alert.equals("devolver")) { //Regreso (Se analiza el caso de penalizacion)

    int penalizacion = Integer.parseInt(request.getParameter("penalizacion"));
    if (penalizacion < 1) { //Caso penalizacion menor de 1
%>
<script>
    $(document).ready(function () {
        devolver();
    });
</script>
<%  } else if (penalizacion > 0 && penalizacion < 3) {//Caso penalizacion  %>
<script>
    $(document).ready(function () {
        penalizacion();
    });
</script>
<%  } else if (penalizacion >= 3) { //Caso penalizacion completa            %>
<script>
    $(document).ready(function () {
        penalizacionCompleta();
    });
</script>
<%    }
    }
    //Analizar el cambio de contraseña
    if (cambio != null && cambio.equals("no")) { //No cambio exitoso
%>
<script>
    $(document).ready(function () {
        noCambio();
    });
</script>
<%   } else if (cambio != null && cambio.equals("si")) { //Cambio exitoso    %>
<script>
    $(document).ready(function () {
        cambio();
    });
</script>
<% }                                                                          %>

<!-- Contenedor principal -->
<div class="container" style=" padding-bottom: 81px; min-height: calc(100% - 81px); position: relative;" >
    <br>
    <!-- Box para titulo -->
    <div class="box">
        <br>
        <center>
            <h1 style="font-family: 'Poppins', sans-serif; font-size: 5rem;font-weight: bold; "> <%=mensaje1%></h1> <!-- Mensaje personalizado -->
            <h2 style="font-family: 'Poppins', sans-serif;font-size:3rem; margin-top: -20px;"> <%=mensaje2%></h2>
        </center>
        <br>
    </div>
    <br>
    <!-- Sección de tarjetas -->

    <div class="row">
        <!-- Primera Columna - "Tus libros alquilados" -->
        <div class="col">
            <!-- Enlace a la página biblioteca.jsp con parámetros de ordenar y cedula -->
            <a href="biblioteca.jsp?ordenar=alquilados&cedula=<%=session.getAttribute("cedula")%>">
                <!-- Tarjeta con estilo y bordes redondeados -->
                <div class="card" style="margin-top:10px;margin-bottom:30px;margin-right:30px; border-radius: 20px;">
                    <div class="card-body">
                        <!-- Fila dentro de la tarjeta -->
                        <div class="row">
                            <div class="col-4" style="display: flex;align-items: center;justify-content: center;">
                                <img src="https://i.pinimg.com/564x/c9/1f/fb/c91ffb578ea1b307d871b5a5294fa390.jpg" width="100%">
                            </div>
                            <!-- Columna con título y descripción -->
                            <div class="col">
                                <h3>Tus libros</h3>
                                <p>Revisa tus libros alquilados. </p>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
        </div>
                
        <!-- Segunda Columna - "Libros Disponibles" -->
        <div class="col">
            <!-- Enlace a la página biblioteca.jsp con parámetros de ordenar y cedula (cedula=null) -->
            <a href="biblioteca.jsp?ordenar=disponibles&cedula=null">
                <!-- Tarjeta con estilo y bordes redondeados -->
                <div class="card" style="margin-top:10px;margin-bottom:30px;margin-right:30px; border-radius: 20px;">
                    <div class="card-body">
                        <!-- Fila dentro de la tarjeta -->
                        <div class="row">
                            <div class="col-4" style="display: flex;align-items: center;justify-content: center;">
                                <img src="https://i.pinimg.com/564x/1e/31/6a/1e316a841d19d6b98b1b1621d53701d1.jpg" width="100%">
                            </div>
                            <!-- Columna con título y descripción -->
                            <div class="col">
                                <h3>Disponibles</h3>
                                <p>Encuentra los disponibles. </p>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
        </div>
        
        <!-- Tercera Columna - "Libros Más Nuevos" -->
        <div class="col">
            <!-- Enlace a la página biblioteca.jsp con parámetros de ordenar y cedula (cedula=null) -->                        
            <a href="biblioteca.jsp?ordenar=recientes&cedula=null">

                <div class="card" style="margin-top:10px;margin-bottom:30px;margin-right:30px; border-radius: 20px;">
                    <div class="card-body">

                        <!-- Fila dentro de la tarjeta -->
                        <div class="row">
                            <div class="col-4" style="display: flex;align-items: center;justify-content: center;">
                                <img src="https://i.pinimg.com/564x/58/96/a6/5896a6b9923a0c0ae5a2b6fec8698db0.jpg" width="100%">
                            </div>
                            <div class="col">
                                <h3>Mas nuevos</h3>
                                <p>Encuentra los recientes. </p>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
        </div>
    </div>
                
    <!-- Incluir seccion generos -->
    <br>
    <%@include file= "templates/Generos.jsp" %> 
    <br>
    
    <!-- Generar tabla -->
    <%=tabla%>
    <br>
</div>
    
<!-- Librerias sweet alert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>

<!-- Script necesarios y sweet alert-->
<script src="scripts/script_login.js"></script>

<script>
      // Funciones de notificación existentes
    function prestado() {
        Swal.fire({
            icon: 'success',
            title: 'Libro prestado!',
            text: 'Disfrutalo ;)',
            showConfirmButton: false,
            timer: 1500
        });
    }
    function noPrestado() {
        Swal.fire({
            icon: 'error',
            title: 'Libro no prestado!',
            text: 'Tienes más de 3 penalizaciones :(',
            showConfirmButton: false,
            timer: 1500
        });
    }
    function devolver() {
        Swal.fire({
            icon: 'success',
            title: 'Regresaste el libro!',
            text: 'Esperamos lo hayas disfrutado ;)',
            showConfirmButton: false,
            timer: 1500
        });
    }
    function penalizacion() {
        Swal.fire({
            icon: "warning",
            title: "Oops...",
            text: "Regresaste el libro, pero... Tienes <%=penalizaciones%> penalizaciones !",
            footer: '<a href="Penalizaciones.jsp">Como solucionar?</a>'
        });
    }
    function penalizacionCompleta() {
        Swal.fire({
            icon: "warning",
            title: "Oops...",
            text: "Has devuelto el libro, pero...Tienes <%=penalizaciones%> penalizaciones ! No puedes alquilar más libros :(",
            footer: '<a href="Penalizaciones.jsp">Se trata de un error? Consultalo!</a>'
        });
    }
    function noCambio() {
        Swal.fire({
            icon: "error",
            title: "Oops...",
            showConfirmButton: false,
            text: "No hemos podido cambiar la contraseña. Revisa que ingresaste la correcta :(",
            timer: 1500
        });
    }
    function cambio() {
        Swal.fire({
            icon: 'success',
            title: 'Cambiaste la contraseña!',
            showConfirmButton: false,
            timer: 1500
        });
    }
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

