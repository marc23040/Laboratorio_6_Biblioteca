
<%@include file= "templates/header.jsp" %>
<%@include file= "templates/lateral.jsp" %>
<%@include file= "templates/navbar.jsp" %>
<%@include file= "styles/stylelogin.jsp" %>
<style>
        #contador {
            font-size: 24px;
            text-align: center;
            margin: 50px;
        }
    </style>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>
<script src="scripts/script_login.js"></script>
<div class="container">
<div class="row">
    <div class="col-sm">
        <div style="margin-top: 10%;">
            <br>
            <h6 style="color: #5e3824;">LA MEJOR OPCION</h6>      
            <h1 style="font-family: 'bold', sans-serif; font-size: 10rem;  letter-spacing: 7px;"> BIBLIOTECA </h1>
            <h2 style="font-family: 'Regular', sans-serif;font-size:5.5rem; margin-top: -50px;"> Aurora Literaria</h2>
        </div>
                        <form class="d-flex" action="SvBuscarOrdenar" method="POST" style="margin-top: 5%;">
                   <div class="input-group">    
         <input class="form-control" name="buscar" type="search" placeholder="    Busca tu libro" aria-label="Search" style="  -moz-border-radius: 10px;
        -webkit-border-radius: 40px;
        border-radius: 40px;
        border: 1px solid #000000;
        height: 60px;
        padding: 0 4px 0 4px;">
      
    </div>
            <button class="btn btn-outline-success" type="submit" style=" background-color: #5e3824;-moz-border-radius: 10px; border-radius: 50%; -webkit-border-radius: 60px;margin-left: 10px;"><i class="fa-solid fa-arrow-right" style="color: #ffffff;"></i></button>
        </form>
        
        <div style="margin-top: 15%;">
        <p>
            En cada libro, una puerta a la imaginación; en cada estantería, un mundo por descubrir. Bienvenidos a la biblioteca, donde las palabras cobran vida y los sueños se encuentran entre sus páginas.
        </p>
        </div>

    </div>
    <div class="col-sm" style="margin-top: 5%; margin-left: 10%"><img src="img/libros.png" width=100% height=93%></div>
</div>
    <div id="carouselExampleRide" class="carousel slide" data-bs-ride="true" style="max-width: 60%; margin: auto; margin-top: 2%;">
  <div class="carousel-inner" style="height: 30%;  border-radius: 3.3rem;
  box-shadow: 0 60px 40px -30px #615145;">
    <div class="carousel-item active">
      <img src="img/image1.jpeg" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="img/image2.jpeg" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="img/image3.jpeg" class="d-block w-100" alt="...">
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleRide" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleRide" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
</div>
<div id="contador">Tiempo restante: <span id="tiempo"></span></div>

<script>
    // Configura la fecha de finalización del temporizador (puedes ajustar esto según tus necesidades)
    var fechaFinalizacion = new Date().getTime() + 10 * 60 * 1000; // 10 minutos en milisegundos

    // Actualiza el contador de tiempo cada segundo
    var temporizador = setInterval(function () {
        // Calcula el tiempo restante
        var ahora = new Date().getTime();
        var tiempoRestante = fechaFinalizacion - ahora;

        // Calcula minutos y segundos
        var minutos = Math.floor((tiempoRestante % (1000 * 60 * 60)) / (1000 * 60));
        var segundos = Math.floor((tiempoRestante % (1000 * 60)) / 1000);

        // Muestra el tiempo restante en el elemento con el id "tiempo"
        document.getElementById("tiempo").innerHTML = minutos + "m " + segundos + "s ";

        // Si el temporizador llega a cero, detén el intervalo y muestra un mensaje
        if (tiempoRestante <= 0) {
            clearInterval(temporizador);
            document.getElementById("tiempo").innerHTML = "Tiempo expirado";
        }
    }, 1000); // 1000 milisegundos = 1 segundo
</script>
<%@include file= "templates/footer.jsp" %> 
