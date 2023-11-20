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
                <a class="nav-link" href="gestionLibros.jsp">Añadir libro</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="listarLibros.jsp">Gestionar libros</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Tu cuenta
                </a>
                <ul class="dropdown-menu">
                    <li><button type="button"  class="dropdown-item"  data-bs-toggle="modal" data-bs-target="#cambiarModal">Cambiar contraseña</button></li>
                    <li><a class="dropdown-item" href="index.jsp?alert=salida">Cerrar</a></li>

                </ul>               
            </li>
            <li class="nav-item">
                <a class="nav-link" href="Penalizaciones.jsp">Sistema penalizaciones</a>
            </li>
            <p class="d-inline-flex gap-1">
            <a class="nav-link" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
             Tus penalizaciones
            </a>

          </p>
    <div class="collapse" id="collapseExample">
      <div class="card card-body">
       Tienes <%=session.getAttribute("penalizacion")%> penalizaciones.
      </div>
    </div>
            <a href="index.jsp?alert=salida" class="btn btn-outline-salir" >Cerrar sesion</a>
            
        </ul>
    </div>

</div>
