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
    String mensaje2="que desees";
    tabla = Metodos.listar(context, ordenarr,ced, buscar);
    String a="ban";
    if(ordenarr!=null && ordenarr.equals("alquilados")){
     mensaje1="LIBROS PRESTADOS";  
     mensaje2="no olvides regresarlos.";
     a="";
    } else if(ordenarr!=null && ordenarr.equals("disponibles")){
        mensaje1="LIBROS DISPONIBLES";  
        mensaje2="tomalos antes que alguien más lo haga.";
        a="";
    }else if(ordenarr!=null && ordenarr.equals("antiguos")){
        mensaje1="LIBROS MAS ANTIGUOS";  
     mensaje2="seccion de libros antiguos.";
     a="";
    }else if(ordenarr!=null && ordenarr.equals("recientes")){
        mensaje1="LIBROS MÁS NUEVOS";  
     mensaje2="seccion de libros recientes.";
     a="";
    }
     String penalizaciones=request.getParameter("penalizacion");
    String alert = request.getParameter("alert");
 
    if (alert != null && alert.equals("prestado")) {
%>
<script>
    $(document).ready(function () {
        prestado();
    });

</script>

<%
    } 
else if (alert != null && alert.equals("noPrestado")) {
%>
<script>
    $(document).ready(function () {
        noPrestado();
    });

</script>

<%
    }

    else if(alert != null && alert.equals("devolver") ){

    int penalizacion=Integer.parseInt(request.getParameter("penalizacion"));
    if(penalizacion <1){
%>
<script>

    $(document).ready(function () {
        devolver();
    });

</script>

<%
}
    else if(penalizacion>0 && penalizacion<3){
%>
<script>

    $(document).ready(function () {
        penalizacion();
    });

</script>

<%
        } else if(penalizacion>=3){
        %>
<script>

    $(document).ready(function () {
        penalizacionCompleta();
    });

</script>

<%
        }

    }

%>


<div class="container">
    <br>
    <div class="box" style=" 
 
  background-color: #f8f1e9;
  border-radius: 3.3rem;
">
   <center>
        <h1 style="font-family: 'bold', sans-serif; font-size: 8rem;  letter-spacing: 7px;"> <%=mensaje1%></h1>
        <h2 style="font-family: 'Regular', sans-serif;font-size:4rem; margin-top: -50px;"> <%=mensaje2%></h2>
     </center>
   </div>
     <%if (a!=null && a.equals("ban")){

%>
    <div class="row">
        
        <div class="col">
            <a href="biblioteca.jsp?ordenar=alquilados&cedula=5454">
            <div class="card" style="margin-top:10px;margin-bottom:30px;margin-right:30px; border-radius: 20px;">
            <div class="card-body">
                <div class="row">
                    <div class="col-4" style="display: flex;align-items: center;justify-content: center;">
                         <img src="https://i.pinimg.com/564x/c9/1f/fb/c91ffb578ea1b307d871b5a5294fa390.jpg" width="100%">
                    </div>
                    <div class="col">
                        <h3>Tus libros</h3>
                        <p>Revisa tus libros alquilados. </p>
                    </div>
                </div>
            </div>
          </div>
             </a>
        </div>
        <div class="col">
            <a href="biblioteca.jsp?ordenar=disponibles&cedula=null">
            <div class="card" style="margin-top:10px;margin-bottom:30px;margin-right:30px; border-radius: 20px;">
            <div class="card-body">
                <div class="row">
                    <div class="col-4" style="display: flex;align-items: center;justify-content: center;">
                         <img src="https://i.pinimg.com/564x/1e/31/6a/1e316a841d19d6b98b1b1621d53701d1.jpg" width="100%">
                    </div>
                    <div class="col">
                        <h3>Disponibles</h3>
                        <p>Encuentra los disponibles. </p>
                    </div>
                </div>
            </div>
          </div>
             </a>
        </div>
        <div class="col">
            <a href="biblioteca.jsp?ordenar=recientes&cedula=null">
            <div class="card" style="margin-top:10px;margin-bottom:30px;margin-right:30px; border-radius: 20px;">
            <div class="card-body">
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
    <%}%>
    <br>
    <%@include file= "templates/Generos.jsp" %> 
    <br>
    
  
   <%=tabla%>

   <br>
</div>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>
<script src="scripts/script_login.js"></script>
<script>

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
        text: "Regresaste el libro. Tienes <%=penalizaciones%> penalizaciones !",
        footer: '<a href="#">Como solucionar?</a>'
      });
    }
     function penalizacionCompleta() {
        Swal.fire({
        icon: "warning",
        title: "Oops...",
        text: "Has devuelto el libro, pero...Tienes <%=penalizaciones%> penalizaciones ! No puedes alquilar más libros :(",
        footer: '<a href="#">Se trata de un error? Comunicate!</a>'
      });
    }


</script>

<%@include file= "templates/footer.jsp" %> 

