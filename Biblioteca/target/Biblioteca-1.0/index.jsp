<%@include file= "templates/header.jsp" %>
  
<header><%@include file= "styles/styleindex.jsp" %></header>
  <%
      // Obtenci�n de par�metros
  String registro = request.getParameter("add");
  String ingreso = request.getParameter("ingreso");
  String alert= request.getParameter("alert");
   // Verificaci�n de par�metro 'registro' para notificaci�n de registro
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
  // Verificaci�n de par�metro 'ingreso' para notificaci�n de no ingreso
    if(ingreso!=null && ingreso.equals("no")){
        %>
        <script>
             $(document).ready(function () {
                noIngreso();
            });
        </script> 
    <%
    // Verificaci�n de par�metro 'alert' para notificaci�n de salida
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
      <!-- Contenedor principal con clase "box" -->
    <div class="box">
         <!-- Contenedor interno con clase "inner-box" -->
      <div class="inner-box">
          <!-- Contenedor de formularios con clase "forms-wrap" -->
        <div class="forms-wrap">
            <!-- Formulario de inicio de sesi�n -->
          <form action="SvLogin" method="POST" autocomplete="off" class="sign-in-form">
               <!-- Encabezado del formulario -->
            <div class="heading">
              <h2>Ingresa Aqui</h2>
              <h6>Registrate</h6>
              <a href="#" class="toggle">crea una cuenta</a>
            </div>

             <!-- Contenido real del formulario -->
            <div class="actual-form">
                 <!-- Campo de entrada para la c�dula -->
              <div class="input-wrap">
                <input type="number" name="cedula" minlength="4" class="input-field" autocomplete="off" required />
                <label>Cedula</label>
              </div>

              <!-- Campo de entrada para la contrase�a -->
              <div class="input-wrap">
                <input type="password" name="contrasenia" minlength="4" class="input-field" autocomplete="off" required />
                <label>Contrase�a</label>
              </div>

              <!-- Bot�n de env�o del formulario -->
              <input type="submit" value="Ingresa" class="sign-btn" />
            </div>
          </form>

          <!-- Formulario de registro -->
          <form action="SvRegister" method="POST" autocomplete="off" class="sign-up-form">
            <!-- Encabezado del formulario -->
            <div class="heading">
              <h2>Registrate</h2>
              <h6>Ya tienes unas cuenta?</h6>
              <a href="#" class="toggle">Ingresa aqui</a>
            </div>

             <!-- Contenido real del formulario -->
            <div class="actual-form">
                 <!-- Campo de entrada para el nombre -->
              <div class="input-wrap">
                <input type="text" name="nombre" minlength="4" class="input-field" autocomplete="off" required />
                <label>Nombre</label>
              </div>

              <!-- Campo de entrada para la c�dula -->
              <div class="input-wrap">
                <input type="number" name="cedula" class="input-field" autocomplete="off" required />
                <label>Cedula</label>
              </div>

               <!-- Campo de entrada para la contrase�a -->
              <div class="input-wrap">
                <input type="password" name="contrasenia" minlength="4" class="input-field" autocomplete="off" required />
                <label>Contrase�a</label>
              </div>

               <!-- Bot�n de env�o del formulario -->
              <input type="submit" value="Registrar" class="sign-btn" />

            </div>
          </form>
        </div>

        <!-- Carrusel de im�genes y texto -->
        <div class="carousel">
            <!-- Contenedor de im�genes -->
          <div class="images-wrapper">
            <img src="./img/image1.jpeg" class="image img-1 show" alt="" />
            <img src="./img/image2.jpeg" class="image img-2" alt="" />
            <img src="./img/image3.jpeg" class="image img-3" alt="" />
          </div>

             <!-- Contenedor de texto del carrusel -->
          <div class="text-slider">
              <!-- Contenedor de textos -->
            <div class="text-wrap">
                 <!-- Grupo de textos -->
              <div class="text-group">
                <h2 class="texto-carrusel">Biblioteca virtual</h2>
                <h2 class="texto-carrusel">Bienvenido a una experiencia unica</h2>
                <h2 class="texto-carrusel">Todos los libros que te imagines</h2>
              </div>
            </div>

            <!-- Indicadores de posici�n en el carrusel -->
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
    // Funci�n para mostrar notificaci�n de error cuando el usuario no est� registrado
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
  
  // Funci�n para mostrar notificaci�n de �xito cuando el usuario se registra
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
    // Funci�n para mostrar notificaci�n de error cuando el usuario no puede ingresar
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
  // Funci�n para mostrar notificaci�n de advertencia cuando el usuario sale
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
   