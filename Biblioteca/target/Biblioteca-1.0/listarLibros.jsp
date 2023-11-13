<%@page import="com.umariana.biblioteca.PersistenciaArchivo"%>
<%@page import="com.umariana.biblioteca.Metodos"%>
<%@page import="com.umariana.biblioteca.Biblioteca"%>
<%@include file= "templates/header.jsp" %>
<%@include file= "templates/lateral.jsp" %>
<%@include file= "templates/navbar.jsp" %>
<header><%@include file= "styles/stylelogin.jsp" %></header>

<%
    ServletContext context = getServletContext();
    Biblioteca libros = new Biblioteca();
    libros=PersistenciaArchivo.deserializarBiblioteca(context);
    if(libros==null){
    libros = new Biblioteca();
    }
    String tabla = "";
    String alert = request.getParameter("alert");
    String search = request.getParameter("buscar");
       tabla = libros.tabla();
    if(search!=null){
     tabla=libros.tablaBusqueda(search);
    }
 
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


<div class="container p-4" style=" height: 560px;">
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
                <input class="form-control me-2" name="buscarTabla" type="search" placeholder="  Busca tu libro" aria-label="Search"style="  -moz-border-radius: 10px;
        -webkit-border-radius: 40px;
        border-radius: 40px;
        border: 1px solid #000000;
        height: 40px;
        padding: 0 4px 0 4px;">
                <button class="btn btn-outline-success" type="submit"  style=" background-color: #5e3824;-moz-border-radius: 10px; border-radius: 50%; -webkit-border-radius: 60px;margin-left: 10px;">Buscar</button>
            </form>
    <br>
    <div class="card">
        <div class="card-body">
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
                            <%=tabla%>
                        </tbody>
                    </table>
                   
   
        </div>               
                  
</div>  
                        </div>  


<!-- modal ver -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Detalles del Libro</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div id="libro-details">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Volver</button>
            </div>
        </div>
    </div>
</div>


<!-- modal ver -->
<div class="modal fade" id="editarModal" tabindex="-1" aria-labelledby="editarModalLabel"aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Editar</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="SvEliminarEditar" method="POST" enctype="multipart/form-data">
                    
                    <div id="editar-details">
                     </div>

                    <center>
                        <button class="btn btn-primary" type="submit">Agregar</button>
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
        $('[data-bs-toggle="ver"]').on('click', function () {
            var titulo = $(this).data('nombre');
            $.ajax({
                url: 'SvAnadirVer?id=' + titulo,
                method: 'GET',
                success: function (data) {
                    $('#libro-details').html(data);
                    $('#exampleModal').modal('show'); // Muestra el modal una vez que se han cargado los detalles del libro
                },
                error: function () {
                    console.log('Error al cargar los detalles del libro.');
                }
            });
        });
    });
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

    document.querySelectorAll(".deleteButton").forEach(function (button) {
    button.addEventListener("click", function () {
        const titulo = this.getAttribute("data-titulo");

        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        });

        swalWithBootstrapButtons.fire({
            title: '¿Estás seguro?',
            text: '¡No podrás revertir esto!',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Sí, borrarlo',
            cancelButtonText: 'No, cancelar',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                // Redirige al servlet con el título como parámetro en la URL
                window.location.href = "SvEliminarEditar?id=" + encodeURIComponent(titulo);
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

    function eliminado() {
        Swal.fire({
            icon: 'success',
            title: 'Eliminado exitosamente!',
            showConfirmButton: false,
            timer: 1500
        })
    }

    function editado() {
        Swal.fire({
            icon: 'success',
            title: 'Editado exitosamente!',
            showConfirmButton: false,
            timer: 1500
        })
    }



</script>

 
<%@include file= "templates/footer.jsp" %> 

