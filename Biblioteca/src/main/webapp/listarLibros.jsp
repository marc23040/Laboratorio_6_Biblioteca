<%@page import="com.umariana.biblioteca.PersistenciaArchivo"%>
<%@page import="com.umariana.biblioteca.Metodos"%>
<%@page import="com.umariana.biblioteca.Biblioteca"%>
<%@include file= "templates/header.jsp" %>
<%@include file= "templates/lateral.jsp" %>
<%@include file= "templates/navbar.jsp" %>
<header><%@include file= "styles/stylelogin.jsp" %></header>

<%
    // Obtener el contexto del servlet
    ServletContext context = getServletContext();
    // Crear una instancia de la clase Biblioteca
    Biblioteca libros = new Biblioteca();
    // Deserializar la biblioteca desde el contexto del servlet
    libros=PersistenciaArchivo.deserializarBiblioteca(context);
    // Verificar si la biblioteca es nula y crear una nueva si es necesario
    if(libros==null){
    libros = new Biblioteca();
    }
    // Inicializar la cadena de la tabla
    String tabla = "";
    // Obtener el parámetro "alert"
    String alert = request.getParameter("alert");
    // Obtener el parámetro de búsqueda "buscar"
    String search = request.getParameter("buscar");
    // Obtener la tabla de libros   
       tabla = libros.tabla();
    // Verificar si hay un parámetro de búsqueda y actualizar la tabla con la búsqueda
    if(search!=null){
     tabla=libros.tablaBusqueda(search);
    }
    // Verificar el parámetro "alert" y mostrar scripts JavaScript correspondientes
    if (alert != null && alert.equals("eliminar")) {
%>
<script>
    $(document).ready(function () {
        eliminado();
    });

</script>

<%
    } else if (alert != null && alert.equals("editar")) {
%>
<script>
    $(document).ready(function () {
        editado();
    });

</script>

<%
    }

%>


<div class="container p-4" style=" height: 560px; padding-bottom: 81px; min-height: calc(100% - 81px); position: relative;">
<div class="box" style=" 
 
  background-color: #f8f1e9;
  border-radius: 3.3rem;
">
   <center>
       <br>
        <h1 style="font-size: 4rem; font-weight: bold;"> GESTIONAR LIBROS</h1>
        <br>
        
     </center>
   </div>
    <br>
     <form class="d-flex" action="SvBuscarOrdenar" method="POST">
         <!-- Formulario de búsqueda -->
                <input class="form-control me-2" name="buscarTabla" type="search" placeholder="  Busca tu libro" aria-label="Search"style="  
                       -moz-border-radius: 10px;
        -webkit-border-radius: 40px;
        border-radius: 40px;
        border: 1px solid #000000;
        height: 40px;
        padding: 0 4px 0 4px;">
                <button class="btn btn-outline-success" type="submit"  style=" background-color: #5e3824;-moz-border-radius: 
                        10px; border-radius: 50%; 
                        -webkit-border-radius: 60px;margin-left: 10px;">Buscar</button>
            </form>
    <br>
     <!-- Tarjeta que contiene la tabla de libros -->
    <div class="card">
        <div class="card-body">
            <!-- Tabla de libros -->
        <table class="table table-bordered ">
                        <thead >
                            <tr>
                                <th>Titulo</th>
                                <th>Autor</th>
                                <th>Año</th>
                                <th>Genero</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%=tabla%><!-- Aquí se inserta la tabla generada dinámicamente desde el servidor -->
                        </tbody>
                    </table>                  
        </div>                               
</div>  
                        </div>  


<!-- modal ver -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <!-- Modal de contenedor principal -->
    <div class="modal-dialog">
        <!-- Modal Dialog: Contiene el contenido modal -->
        <div class="modal-content">
            <!-- Modal Content: Contenido del modal -->
            <div class="modal-header">
                 <!-- Encabezado del modal -->
                <h5 class="modal-title" id="exampleModalLabel">Detalles del Libro</h5>
                <!-- Título del modal -->
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                 <!-- Botón para cerrar el modal -->
            </div>
            <div class="modal-body">
                 <!-- Cuerpo del modal -->
                <div id="libro-details">
                    <!-- Contenido dinámico: Aquí se mostrarán los detalles del libro -->
                </div>
            </div>
            <div class="modal-footer">
                <!-- Pie del modal -->
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Volver</button>
                 <!-- Botón para cerrar el modal -->
            </div>
        </div>
    </div>
</div>


<!-- modal ver -->
<div class="modal fade" id="editarModal" tabindex="-1" aria-labelledby="editarModalLabel"aria-hidden="true">
      <!-- Modal Dialog: Contiene el contenido modal -->
    <div class="modal-dialog">
        <!-- Modal Content: Contenido del modal -->
        <div class="modal-content">
             <!-- Encabezado del modal -->
            <div class="modal-header">
                <!-- Título del modal -->
                <h5 class="modal-title" id="exampleModalLabel">Editar</h5>
                <!-- Botón para cerrar el modal -->
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
             
            <div class="modal-body">
                <!-- Cuerpo del modal -->
                <form action="SvEliminarEditar" method="POST" enctype="multipart/form-data">
                     <!-- Formulario para editar -->
                    <div id="editar-details">
                    <!-- Contenido dinámico: Aquí se mostrarán los detalles a editar -->
                     </div>

                    <center>
                        <button class="btn btn-primary" type="submit">Agregar</button>
                        <!-- Botón para realizar la acción de editar/agregar -->

                    </center>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>
<script src="scripts/script_login.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>


<script>
    $(document).ready(function () {
        // Al hacer clic en un elemento con el atributo data-bs-toggle="ver"
        $('[data-bs-toggle="ver"]').on('click', function () {
            // Obtener el título del libro desde el atributo data-nombre
            var titulo = $(this).data('nombre');
         // Realizar una solicitud AJAX para obtener detalles del libro por su título
            $.ajax({
                url: 'SvAnadirVer?id=' + titulo, // URL del servlet o recurso que maneja la solicitud
                method: 'GET',
                success: function (data) {
                    // Éxito: Colocar los detalles del libro en el contenedor #libro-details
                    $('#libro-details').html(data);
                   // Mostrar el modal (exampleModal) una vez que se han cargado los detalles del libro
                    $('#exampleModal').modal('show'); 
                },
                error: function () {
                    console.log('Error al cargar los detalles del libro.');
                }
            });
        });
    });
    // Repetir el mismo proceso para el botón de editar
        $(document).ready(function () {
        $('[data-bs-toggle="editar"]').on('click', function () {
            var titulo = $(this).data('nombre');
            $.ajax({
                url: 'SvGestiones2?id=' + titulo,
                method: 'GET',
                success: function (data) {
                    $('#editar-details').html(data);
                    $('#editarModal').modal('show'); // Muestra el modal una vez que se han cargado los detalles del libro
                },
                error: function () {
                    console.log('Error al cargar los detalles del libro.');
                }
            });
        });
    });

    // Seleccionar todos los elementos con la clase "deleteButton" y agregar un event listener a cada uno
    document.querySelectorAll(".deleteButton").forEach(function (button) {
    button.addEventListener("click", function () {
        // Obtener el título del libro desde el atributo "data-titulo"
        const titulo = this.getAttribute("data-titulo");

         // Crear un diálogo de confirmación personalizado con SweetAlert2
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        });
        // Mostrar el diálogo de confirmación
        swalWithBootstrapButtons.fire({
            title: '¿Estás seguro?',
            text: '¡No podrás revertir esto!',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Sí, borrarlo',
            cancelButtonText: 'No, cancelar ',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                // Redirige al servlet con el título como parámetro en la URL
                window.location.href = "SvEliminarEditar?id=" + encodeURIComponent(titulo);
                // Mostrar un mensaje de cancelación si el usuario decide no eliminar
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire(
                        'Cancelado',
                        'Tu libro imaginario está a salvo :)',
                        'error'
                );
            }
        });
    });
});
    // Función para mostrar un mensaje de éxito cuando se ha eliminado un libro
    function eliminado() {
        Swal.fire({
            icon: 'success',
            title: 'Eliminado exitosamente!',
            showConfirmButton: false,
            timer: 1500
        })
    }
    // Función para mostrar un mensaje de éxito cuando se ha editado un libro
    function editado() {
        Swal.fire({
            icon: 'success',
            title: 'Editado exitosamente!',
            showConfirmButton: false,
            timer: 1500
        })
    }



</script>

<%@include file= "templates/footer2.jsp" %>  
<%@include file= "templates/footer.jsp" %> 

