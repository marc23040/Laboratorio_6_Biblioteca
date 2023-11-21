
<!-- Offcanvas para opciones -->
<div class="offcanvas offcanvas-start" id="intro">
    <div class="offcanvas-header">
        <!-- Encabezado del offcanvas con bot�n de retorno -->
        <div class="offcanvas-tittle">
            <button class="btn btn-outline-success" data-bs-dismiss="offcanvas">Volver</button>
        </div>
    </div>

    <div class="offcanvas-body">
        <!-- Cuerpo del offcanvas con opciones -->
        <h1 class="titulo-opciones">Opciones</h1>
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <!-- Enlace para a�adir libro -->
            <li class="nav-item">
                <a class="nav-link" href="gestionLibros.jsp">A�adir libro</a>
            </li>
            <!-- Enlace para gestionar libros -->
            <li class="nav-item">
                <a class="nav-link" href="listarLibros.jsp">Gestionar libros</a>
            </li>
            <!-- Men� desplegable para "Tu cuenta" -->
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Tu cuenta
                </a>
                <!-- Opciones dentro del men� desplegable -->
                <ul class="dropdown-menu">
                    <!-- Bot�n para cambiar contrase�a (abre un modal) -->
                    <li><button type="button" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#cambiarModal">Cambiar contrase�a</button></li>
                    <!-- Enlace para cerrar sesi�n -->
                    <li><a class="dropdown-item" href="index.jsp?alert=salida">Cerrar</a></li>
                </ul>
            </li>
            <!-- Enlace para el sistema de penalizaciones -->
            <li class="nav-item">
                <a class="nav-link" href="Penalizaciones.jsp">Sistema penalizaciones</a>
            </li>
            <!-- Secci�n colapsable para mostrar las penalizaciones del usuario -->
            <p class="d-inline-flex gap-1">
                <a class="nav-link" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                    Tus penalizaciones
                </a>
            </p>
            <!-- Contenido colapsable con el n�mero de penalizaciones del usuario -->
            <div class="collapse" id="collapseExample">
                <div class="card card-body">
                    Tienes <%=session.getAttribute("penalizacion")%> penalizaciones.
                </div>
            </div>
            <!-- Enlace para cerrar sesi�n -->
            <a href="index.jsp?alert=salida" class="btn btn-outline-salir">Cerrar sesi�n</a>
        </ul>
    </div>
</div>
