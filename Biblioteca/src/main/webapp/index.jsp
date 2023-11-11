<%@include file= "templates/header.jsp" %>
  
<header><%@include file= "styles/styleindex.jsp" %></header>
  <%
  String registro = request.getParameter("add");
  String ingreso = request.getParameter("ingreso");
  String alert= request.getParameter("alert");
  
    if(registro!=null && registro.equals("no")){
    %>
<script>
     $(document).ready(function () {
        noRegistrado();
    });
</script> 
    <%
    }else if(registro!=null && registro.equals("si")){
    %>
<script>
     $(document).ready(function () {
        Registrado();
    });
</script> 
    <%
    }
  
    if(ingreso!=null && ingreso.equals("no")){
        %>
        <script>
             $(document).ready(function () {
                noIngreso();
            });
        </script> 
    <%

    } if(alert!=null && alert.equals("salida")){
 %>
        <script>
             $(document).ready(function () {
                salida();
            });
        </script> 
    <%
}

  %>  
  <main>
    <div class="box">
      <div class="inner-box">
        <div class="forms-wrap">
          <form action="SvLoginRegister" method="POST" autocomplete="off" class="sign-in-form">

            <div class="heading">
              <h2>Ingresa Aqui</h2>
              <h6>Registrate</h6>
              <a href="#" class="toggle">crea una cuenta</a>
            </div>

            <div class="actual-form">
              <div class="input-wrap">
                <input type="number" name="cedula" minlength="4" class="input-field" autocomplete="off" required />
                <label>Cedula</label>
              </div>

              <div class="input-wrap">
                <input type="password" name="contrasenia" minlength="4" class="input-field" autocomplete="off" required />
                <label>Contraseña</label>
              </div>

              <input type="submit" value="Ingresa" class="sign-btn" />
            </div>
          </form>

          <form action="SvLoginRegister" method="GET" autocomplete="off" class="sign-up-form">
            <div class="heading">
              <h2>Registrate</h2>
              <h6>Ya tienes unas cuenta?</h6>
              <a href="#" class="toggle">Ingresa aqui</a>
            </div>

            <div class="actual-form">
              <div class="input-wrap">
                <input type="text" name="nombre" minlength="4" class="input-field" autocomplete="off" required />
                <label>Nombre</label>
              </div>

              <div class="input-wrap">
                <input type="number" name="cedula" class="input-field" autocomplete="off" required />
                <label>Cedula</label>
              </div>

              <div class="input-wrap">
                <input type="password" name="contrasenia" minlength="4" class="input-field" autocomplete="off" required />
                <label>Contraseña</label>
              </div>

              <input type="submit" value="Registrar" class="sign-btn" />

            </div>
          </form>
        </div>

        <div class="carousel">
          <div class="images-wrapper">
            <img src="./img/image1.jpeg" class="image img-1 show" alt="" />
            <img src="./img/image2.jpeg" class="image img-2" alt="" />
            <img src="./img/image3.jpeg" class="image img-3" alt="" />
          </div>

          <div class="text-slider">
            <div class="text-wrap">
              <div class="text-group">
                <h2 class="texto-carrusel">Biblioteca virtual</h2>
                <h2 class="texto-carrusel">Bienvenido a una experiencia unica</h2>
                <h2 class="texto-carrusel">Todos los libros que te imagines</h2>
              </div>
            </div>

            <div class="bullets">
              <span class="active" data-value="1"></span>
              <span data-value="2"></span>
              <span data-value="3"></span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>  
    
  
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

  
  <script src="scripts/script_index.js"></script>
  
  <script>
    function noRegistrado(){
      toastr.options = {
      "closeButton": false,
      "debug": false,
      "newestOnTop": false,
      "progressBar": false,
      "positionClass": "toast-top-center",
      "preventDuplicates": false,
      "onclick": null,
      "showDuration": "300",
      "hideDuration": "1000",
      "timeOut": "3000",
      "extendedTimeOut": "1000",
      "showEasing": "swing",
      "hideEasing": "linear",
      "showMethod": "fadeIn",
      "hideMethod": "fadeOut"
    }
    toastr["error"]("La cedula que ingresaste ya ha sido registrada. Intenta de nuevo!", "Error");
  }    
  function Registrado(){
      toastr.options = {
        "closeButton": false,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass":"toast-top-center",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "3000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }
    toastr["success"]("Usuario registrado exitosamente. Ingresa!", "Registrado");
  } 
    function noIngreso(){
      toastr.options = {
        "closeButton": false,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass":"toast-top-center",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "3000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }
    toastr["error"]("El usuario ingresado no coincide, vuelve a intentar", "Error");
  } 
  function salida() {
        Swal.fire({
            icon: 'warning',
            title: 'Saliste',
            text:'Esperamos verte de nuevo ;)',
            showConfirmButton: false,
            timer: 1500
        })
    }
  </script>
  
<%@include file= "templates/footer.jsp" %> 
   