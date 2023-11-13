<%@page import="com.umariana.biblioteca.PersistenciaArchivo"%>
<%@page import="com.umariana.biblioteca.Metodos"%>
<%@page import="com.umariana.biblioteca.Biblioteca"%>
<%@include file= "templates/header.jsp" %>
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
                <input class="form-control me-2" name="buscarTabla" type="search" placeholder="Busca tu libro" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Buscar</button>
            </form>
        </div>


    </div>
</nav>
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
        <h1 style="font-family: 'bold', sans-serif; font-size: 4rem;  letter-spacing: 10px;"> GESTIONAR LIBROS</h1>

     </center>
   </div>
    <br>
    <div class="row">
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

