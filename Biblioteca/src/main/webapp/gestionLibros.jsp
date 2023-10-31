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
    tabla = libros.tabla();
    String añadido=request.getParameter("alert");
    if (añadido != null && añadido.equals("anadido")) {
%>
<script>
    $(document).ready(function () {
        añadido();
    });

</script>

<%
    }
%>



<div class="container p-4">

            <div class="card">
                <div class="card-header">

                </div>
                <div class="card-body">
                    <form action="SvAnadirVer" method="POST" enctype="multipart/form-data">

                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Titulo</span>
                            <input type="text" name="titulo" class="form-control" placeholder="Titulo" aria-label="Username" aria-describedby="basic-addon1" required>
                        </div>

                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Autor</span>
                            <input type="text" name="autor" class="form-control" placeholder="Autor" aria-label="Username" aria-describedby="basic-addon1" required>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Año</span>
                            <input type="number" name="anio" class="form-control" placeholder="Año" aria-describedby="basic-addon1" required>
                        </div>
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
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="fotoPortada">Portada:</label>
                            <input type="file" name="fotoPortada" class="form-control" id="fotoPortada" accept="image/*" required>
                        </div>
                        <center>
                            <button class="btn submit-acciones" type="submit">Submit form</button>
                        </center>
                    </form>
                </div>
            </div>

   
</div>  







<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>
<script src="scripts/script_login.js"></script>


<script>

    function añadido() {
        Swal.fire({
            icon: 'success',
            title: 'Añadido exitosamente!',
            text: '¡Puedes verlo en la pagina de gestionar!',
            showConfirmButton: false,
            timer: 1500
        })
    }

</script>

<%@include file= "templates/footer.jsp" %> 

