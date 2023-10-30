<%@page import="com.umariana.biblioteca.Metodos"%>
<%@page import="com.umariana.biblioteca.Biblioteca"%>
<%@include file= "templates/header.jsp" %>
<header><%@include file= "styles/stylelogin.jsp" %></header>

<%
    ServletContext context = getServletContext();
    Biblioteca libros = new Biblioteca();

    String tabla = "";
    String eliminado = request.getParameter("delete");
    String editar = request.getParameter("editado");
    String search = request.getParameter("search");
    tabla = Metodos.listar(context, search);
    if (eliminado != null && eliminado.equals("si")) {
%>
<script>
    $(document).ready(function () {
        eliminado();
    });

</script>

<%
    }
    if (editar != null && editar.equals("a")) {
%>
<script>
    $(document).ready(function () {
        editado();
    });

</script>

<%
    }

%>



<div class="offcanvas offcanvas-start" id="intro">
    <div class="offcanvas-header">
        <div class="offcanvas-tittle">
            <button class="btn btn-outline-success " data-bs-dismiss="offcanvas">Volver</button>
        </div>
    </div>

    <div class="offcanvas-body">
        <h1 class="titulo-opciones">Opciones</h1>
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="#">Organizar</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Organizar2</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Organizar3
                </a>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">Organizar4</a></li>
                    <li><a class="dropdown-item" href="#">Organizar 5</a></li>

                </ul>               
            </li>
            <button href="" class="btn btn-outline-salir" >Cerrar sesion</button>
            
        </ul>
    </div>

</div>

<nav class="navbar navbar-expand-lg">
    <div class="container-fluid">

        <button class="btn icono" data-bs-toggle="offcanvas" data-bs-target="#intro"><i class="fa-solid fa-address-book"></i></button>

        <h1 class="navbar-brand" >Biblioteca online</h1>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <form class="d-flex" action="SvBuscarOrdenar" method="GET">
                <input class="form-control me-2" name="buscar" type="search" placeholder="Busca tu libro" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Buscar</button>
            </form>
        </div>


    </div>
</nav>
<div class="container p-4">
    <div class="card">
        <div class="card-body">
            <p>Bienvenid@, <%=session.getAttribute("usuario")%>! </p>

        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-md-4">
            <div class="card">
                <div class="card-header">

                </div>
                <div class="card-body">
                    <form action="SvAnadirVer" method="POST" enctype="multipart/form-data">

                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Titulo</span>
                            <input type="text" name="titulo" class="form-control" placeholder="Titulo" aria-label="Username" aria-describedby="basic-addon1">
                        </div>

                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Autor</span>
                            <input type="text" name="autor" class="form-control" placeholder="Autor" aria-label="Username" aria-describedby="basic-addon1">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Año</span>
                            <input type="number" name="anio" class="form-control" placeholder="Año" aria-describedby="basic-addon1" required>
                        </div>

                        <div class="input-group mb-3">
                            <label class="input-group-text" for="fotoPortada">Portada:</label>
                            <input type="file" name="fotoPortada" class="form-control" id="fotoPortada" accept="fotoPortada/*">
                        </div>

                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" name="prestamo" value="true" id="flexCheckDefault">
                            <label class="form-check-label" for="flexCheckDefault">
                                ¿El libro fue prestado?
                            </label>
                        </div>
                        <center>
                            <button class="btn submit-acciones" type="submit">Submit form</button>
                        </center>
                    </form>
                </div>
            </div>

        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">

                </div>
                <div class="card-body">
                    <table class="table table-bordered ">
                        <thead >
                            <tr>
                                <th>Titulo</th>
                                <th>Autor</th>
                                <th>Año</th>
                                <th>Prestado</th>
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
<div class="modal fade" id="editarModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Editar</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="SvEliminarEditar" method="POST">
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Titulo</span>
                        <input type="text" name="titulo" id="titulo" class="form-control" aria-describedby="basic-addon1">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Autor</span>
                        <input type="text" id="autor" name="autor" class="form-control" aria-describedby="basic-addon1">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Año</span>
                        <input type="text" id="anio" name="anio" class="form-control" aria-describedby="basic-addon1">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Portada</span>
                        <input type="text" id="portada" name="portada" class="form-control" aria-describedby="basic-addon1">
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="prestamo" value="true" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            ¿El libro fue prestado?
                        </label>
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
                url: 'SvAnadirVer?titulo=' + titulo,
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
    document.getElementById("deleteButton").addEventListener("click", function () {
        const titulo = this.getAttribute("data-titulo"); // Obtiene el título del atributo data-titulo

        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        })

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

                window.location.href = "SvEliminarEditar?titulo=" + encodeURIComponent(titulo);

            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire(
                        'Cancelado',
                        'Tu libro imaginario está a salvo :)',
                        'error'
                        )
            }
        })
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
    function abrirModal(boton) {
        // Obtiene el valor del atributo data-nombre del botón que se ha hecho clic
        var nombreLibro = boton.getAttribute("data-nombre");

        // Asigna el valor a un campo de entrada en la modal
        document.getElementById("titulo").value = nombreLibro;

        var autor = boton.getAttribute("data-autor");

        document.getElementById("autor").value = autor;

        var autor = boton.getAttribute("data-anio");

        document.getElementById("anio").value = anio;

        var portada = boton.getAttribute("data-portada");

        document.getElementById("portada").value = portada;



        // Abre la modal
        $("#editarModal").modal("show");
    }


</script>




<%@include file= "templates/footer.jsp" %> 
