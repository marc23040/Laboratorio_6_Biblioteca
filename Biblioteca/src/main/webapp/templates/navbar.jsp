<!-- Barra de Navegación -->
<nav class="navbar navbar-expand-lg">

    <!-- Contenedor para el contenido de la barra de navegación -->
    <div class="container-fluid">

        <!-- Botón para activar/desactivar el offcanvas -->
        <button class="btn icono" data-bs-toggle="offcanvas" data-bs-target="#intro">
            <i class="fa-solid fa-list"></i>
        </button>

        <!-- Marca/logo vinculado a login.jsp -->
        <a href="login.jsp">
            <h1 class="navbar-brand">Biblioteca online</h1>
        </a>

        <!-- Botón de alternancia de la barra de navegación para pantallas más pequeñas -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Alternar navegación">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Enlaces de la barra de navegación -->
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <!-- Enlace de Libros -->
                <li class="nav-item">
                    <a class="nav-link" href="biblioteca.jsp" role="button">
                        Libros
                    </a>
                </li>

                <!-- Menú desplegable para "Ver" -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Ver
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="SvBuscarOrdenar?ordenar=alquilados&cedula=<%=session.getAttribute("cedula")%>">Mis libros alquilados</a></li>
                        <li><a class="dropdown-item" href="SvBuscarOrdenar?ordenar=disponibles">Libros disponibles</a></li>
                    </ul>
                </li>

                <!-- Menú desplegable para "Ordenar" -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Ordenar
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="SvBuscarOrdenar?ordenar=antiguos">Libros más antiguos</a></li>
                        <li><a class="dropdown-item" href="SvBuscarOrdenar?ordenar=recientes">Libros más recientes</a></li>
                    </ul>
                </li>

                <!-- Menú desplegable para "Géneros" -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Géneros
                    </a>
                    <ul class="dropdown-menu">
                        <!-- Lista de opciones de géneros -->
                        <!-- Agregar más géneros según sea necesario -->
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

        <!-- Formulario de búsqueda en el lado derecho -->
        <div class="ms-auto" id="navbarSupportedContent">
            <form class="d-flex" action="SvBuscarOrdenar" method="POST">
                <input class="form-control me-2" name="buscar" type="search" placeholder="  Busca tu libro" aria-label="Buscar" style="-moz-border-radius: 10px;-webkit-border-radius: 40px;border-radius: 40px;border: 1px solid #000000;padding: 0 4px 0 4px;">
                <button class="btn btn-outline-success" type="submit" style="-moz-border-radius: 10px;-webkit-border-radius: 40px;border-radius: 20px;">Buscar</button>
            </form>
        </div>

    </div>
</nav>
