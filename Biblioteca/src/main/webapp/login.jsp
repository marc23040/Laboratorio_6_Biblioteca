
<%@include file= "templates/header.jsp" %>
<%@include file= "templates/lateral.jsp" %>
<%@include file= "templates/navbar.jsp" %>
<%@include file= "styles/stylelogin.jsp" %>


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


<div class="box" style=" 
 
  background-color: #f8f1e9;
  border-radius: 3.3rem;
">
   <div class="row" style="align-items: center; justify-content: center;">
       <div class="col">
           <img src="img/Libros (1).png" width="100%">
       </div>
       <div class="col" style="padding: 2rem; align-items: center; justify-content: center;">
           <br>
           <h2>2022 Uno de los 50 vendedores más populares Bestsellers </h2>
           <br>
           <p>La biblioteca es el refugio donde las palabras se convierten en aventuras, donde cada estante es una puerta a un nuevo mundo. </p>
           <a href="biblioteca.jsp" class="btn btn-outline-success" style=" display: flex;align-items: center; justify-content: center;background-color: #5e3824;-moz-border-radius: 10px; border-radius: 50%; -webkit-border-radius: 60px;margin-top: 30px; width: 200px; height: 50px;s">Mirar todo</a>
       </div>
       <div class="col">
           <div class="card" style="margin-top:30px;margin-bottom:20px;margin-right:30px;  border-radius: 20px;">
            <div class="card-body">
                <div class="row">
                    <div class="col-2" style="display: flex;align-items: center;justify-content: center;">
                         <img src="img/icono1.png" width="100%">
                    </div>
                    <div class="col">
                        <h3>50 libros infantiles</h3>
                        <p>Explora el mágico mundo de la lectura. </p>
                    </div>
                </div>
            </div>
          </div>
           <div class="card" style="margin-top:10px;margin-bottom:20px;margin-right:30px; border-radius: 20px;">
            <div class="card-body">
                <div class="row">
                    <div class="col-2" style="display: flex;align-items: center;justify-content: center;">
                         <img src="img/icono1.png" width="100%">
                    </div>
                    <div class="col">
                        <h3>30 libros clasicos.</h3>
                        <p>Explora el mágico mundo de la lectura. </p>
                    </div>
                </div>
            </div>
          </div>
           <div class="card" style="margin-top:10px;margin-bottom:30px;margin-right:30px; border-radius: 20px;">
            <div class="card-body">
                <div class="row">
                    <div class="col-2" style="display: flex;align-items: center;justify-content: center;">
                         <img src="img/icono1.png" width="100%">
                    </div>
                    <div class="col">
                        <h3>50 libros Ski-fi</h3>
                        <p>Explora el mágico mundo de la lectura. </p>
                    </div>
                </div>
            </div>
          </div>
       </div>
   </div>
</div>  
    <br>
<%@include file= "templates/Generos.jsp" %> 

    <br>
    <div class="row" style="display: flex;align-items: center;justify-content: center;margin-bottom: 40px;" >
        <div class="col">
            <div id="carouselExampleRide" class="carousel slide" data-bs-ride="true" style="max-width: 100%; margin: auto; margin-top: 2%;">
                <div class="carousel-inner" style="height: 30%;  border-radius: 3.3rem;">
                  <div class="carousel-item active">
                    <img src="img/6.jpg" class="d-block w-100" alt="...">
                  </div>
                  <div class="carousel-item">
                    <img src="img/7.jpg" class="d-block w-100" alt="...">
                  </div>
                  <div class="carousel-item">
                    <img src="img/8.jpg" class="d-block w-100" alt="...">
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
        <div class="col-sm">
            <div class="list-group">
                <a href="biblioteca.jsp" class="list-group-item list-group-item-action">Todos los libros</a>
                <a href="SvBuscarOrdenar?ordenar=alquilados&cedula=<%=session.getAttribute("cedula")%>" class="list-group-item list-group-item-action">Tus libros</a>
                <a href="gestionLibros.jsp" class="list-group-item list-group-item-action">Añadir libros</a>
                <a href="listarLibros.jsp" class="list-group-item list-group-item-action">Gestionar libros</a>
                <a href="SvBuscarOrdenar?ordenar=antiguos" class="list-group-item list-group-item-action">Libros mas antiguos</a>
                <a href="SvBuscarOrdenar?ordenar=recientes" class="list-group-item list-group-item-action">Libros más nuevos</a>
                <a href="#" class="list-group-item list-group-item-action">Sistema Penalizaciones</a>
              </div>
        </div>
    
</div>
                </div>
   
<%@include file= "templates/footer.jsp" %> 
