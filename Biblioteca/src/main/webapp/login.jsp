
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
            <h6 style="color: #b39d69;">LA MEJOR OPCION</h6>      
            <h1 style="font-family: 'bold', sans-serif; font-size: 10rem;  letter-spacing: 7px;"> BIBLIOTECA </h1>
            <h2 style="font-family: 'Regular', sans-serif;font-size:5.5rem;"> Aurora Literaria</h2>
        </div>
        <form class="d-flex" action="SvBuscarOrdenar" method="POST">
                   <div class="input-group">    
      <input class="form-control" name="buscar" type="search" placeholder="    Busca tu libro" aria-label="Search" style="  -moz-border-radius: 10px;
        -webkit-border-radius: 40px;
        border-radius: 40px;
        border: 1px solid #000000;
        height: 60px;
        padding: 0 4px 0 4px;">
      
    </div>
            <button class="btn btn-outline-success" type="submit" style="-moz-border-radius: 10px; border-radius: 50%; -webkit-border-radius: 60px;margin-left: 10px;"><i class="fa-solid fa-arrow-right" style="color: #ffffff;"></i></button>
        </form>
        <div style="margin-top: 10%;">
        <p>
            En cada libro, una puerta a la imaginación; en cada estantería, un mundo por descubrir. Bienvenidos a la biblioteca, donde las palabras cobran vida y los sueños se encuentran entre sus páginas.
        </p>
        </div>
    </div>
    <div class="col-sm"><img src="img/libros.png" width=100% height=90%></div>
</div>
</div>
<script>

</script>

<%@include file= "templates/footer.jsp" %> 
