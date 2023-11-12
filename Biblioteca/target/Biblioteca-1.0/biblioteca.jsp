<%@page import="com.umariana.biblioteca.PersistenciaArchivo"%>
<%@page import="com.umariana.biblioteca.Metodos"%>
<%@page import="com.umariana.biblioteca.Biblioteca"%>
<%@include file= "templates/header.jsp" %>
<%@include file= "templates/lateral.jsp" %>
<%@include file= "templates/navbar.jsp" %>
<%@include file= "styles/stylelogin.jsp" %>
<%
    ServletContext context = getServletContext();
   
    String tabla = "";
    String ordenarr = request.getParameter("ordenar");
    String buscar= request.getParameter("buscar");
    int cedula=0;
    String ced = request.getParameter("cedula");
    String mensaje1="TODOS LOS LIBROS";
    String mensaje2="¡Prueba el sistema de alquiler de libros! No olvides regresar tus libros a tiempo.";
    tabla = Metodos.listar(context, ordenarr,ced, buscar);
    if(ordenarr!=null && ordenarr.equals("alquilados")){
     mensaje1="LIBROS ALQUILADOS";  
     mensaje2="";
    } else if(ordenarr!=null && ordenarr.equals("disponibles")){
        mensaje1="LIBROS DISPONIBLES";  
     mensaje2="";
    }else if(ordenarr!=null && ordenarr.equals("antiguos")){
        mensaje1="LIBROS MAS ANTIGUOS";  
     mensaje2="";
    }else if(ordenarr!=null && ordenarr.equals("recientes")){
        mensaje1="LIBROS MÁS NUEVOS";  
     mensaje2="";
    }

    String alert = request.getParameter("alert");
 
    if (alert != null && alert.equals("prestado")) {
%>
<script>
    $(document).ready(function () {
        prestado();
    });

</script>

<%
    } else if(alert != null && alert.equals("devolver")){ 
%>
<script>
    $(document).ready(function () {
        devolver();
    });

</script>

<%
    }
%>



<main style="  width: 100%;

  
  background-color: #e8c382;
  padding: 2rem;

  align-items: center;
  justify-content: center;">

<div class="box" style=" 
  width: 100%;
  height: 200px;
  background-color: #f8f1e9;
  border-radius: 3.3rem;
  box-shadow: 0 40px 40px -30px #615145; ">
    <br>
    <center>
    <h1 style="font-family: 'bold', sans-serif; font-size: 5rem;  letter-spacing: 7px;"><%=mensaje1%></h1>
    <h2 style="font-family: 'Regular', sans-serif;font-size:2rem;"> <%=mensaje2%></h2>
    </center>
    

</div>

    </main>

<div class="container p-4">
   <div class="row row-cols-1 row-cols-md-4 g-4">
   <%=tabla%>

    </div>
</div>
   <!-- modal alquilar -->
<div class="modal fade" id="alquilarModal" tabindex="-1" aria-labelledby="alquilarModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">¿Deseas alquilar el libro? </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="SvGestiones" method="POST">
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Tiempo</span>
                        <select class="form-select" name="tiempo" aria-label="Default select example" required>
                            <option selected > ¿Cuanto tiempo?</option>
                            <option value="7 dias">7 dias</option>
                            <option value="15 dias">15 dias</option>
                            <option value="1 mes">1 mes</option>
                        </select>
                       
                    </div>
                <div id="alquilar-details">
                </div>
                     <input name="cedula" value="<%=session.getAttribute("cedula")%>" hidden>            
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Volver</button>
                <button type="submit" class="btn btn-success" data-bs-dismiss="modal">Alquilar</button>
            </div>
                       </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>
<script src="scripts/script_login.js"></script>
<script>
    $(document).ready(function () {
        $('[data-bs-toggle="alquilar"]').on('click', function () {
            var alq = $(this).data('nombre');

            $.ajax({
                url: 'SvGestiones?id=' + alq,
                method: 'GET',
                success: function (data) {
                    $('#alquilar-details').html(data);
                    $('#alquilarModal').modal('show'); // Muestra el modal una vez que se han cargado los detalles del libro
                },
                error: function () {
                    console.log('Error al cargar los detalles del libro.');
                }
            });
        });
    });
    
    document.querySelectorAll(".devolverButton").forEach(function (button) {
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
            title: '¿Deseas regresarlo?',
            text: 'Si no lo has terminado, alguien lo puede obtener!',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Sí, regresarlo',
            cancelButtonText: 'No, cancelar',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                // Redirige al servlet con el título como parámetro en la URL
                window.location.href = "SvDevolverPrestar?id=" + encodeURIComponent(titulo);
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire(
                        'Cancelado',
                        'No olvides regresarlo a tiempo :)',
                        'error'
                );
            }
        });
    });
});

    function prestado() {
        Swal.fire({
            icon: 'success',
            title: 'Libro prestado!',
            text: 'Disfrutalo ;)',
            showConfirmButton: false,
            timer: 1500
        })
    }
    function devolver() {
        Swal.fire({
            icon: 'success',
            title: 'Regresaste el libro!',
            text: 'Esperamos lo hayas disfrutado ;)',
            showConfirmButton: false,
            timer: 1500
        })
    }

</script>

<%@include file= "templates/footer.jsp" %> 

