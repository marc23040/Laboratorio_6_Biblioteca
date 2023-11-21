<%@include file= "templates/header.jsp" %> <!-- Incluir header -->
<%@include file= "templates/lateral.jsp" %> <!-- Incluir lateral -->
<%@include file= "templates/navbar.jsp" %> <!-- Incluir navbar-->
<%@include file= "styles/stylelogin.jsp" %> <!-- Incluir los styles -->

<!-- Container sistema de penalizaciones -->
<div class="container" >

    <br>
    <div class="box" style="background-color: #f8f1e9;border-radius: 3.3rem;box-shadow: 0 10px 30px -5px #615145;">
        <br>
        <center>
            <h1 style="font-family: 'Poppins', sans-serif; font-size: 5rem;font-weight: bold; "> Sistema de penalizaciones</h1>
        </center>
        <br>
    </div>
    <br>

    <div class="row">

        <div class="col">

            <div class="card" style="margin-top:10px;margin-bottom:30px;margin-right:30px; border-radius: 20px;">
                <div class="card-body">

                    <div class="row">
                        <div class="col-4" style="display: flex;align-items: center;justify-content: center;">

                            <img src="https://img.freepik.com/vector-gratis/banca-movil-devolver-dinero-compras-realice-transacciones-financieras-forma-remota-dispositivo-movil-ilustracion-metafora-concepto-aislado-vector_335657-2799.jpg?size=338&ext=jpg&ga=GA1.1.1826414947.1699660800&semt=sph" width="100%">
                        </div>
                        <!-- Columna con título y descripción -->
                        <div class="col">                  
                            <p>Las penalizaciones son infracciones que le damos a tu cuenta al momento de devolver un libro en mas tiempo del que pediste. </p>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <!-- Segunda Columna - "Libros Disponibles" -->
        <div class="col">

            <!-- Tarjeta con estilo y bordes redondeados -->
            <div class="card" style="margin-top:10px;margin-bottom:30px;margin-right:30px; border-radius: 20px;">
                <div class="card-body">
                    <!-- Fila dentro de la tarjeta -->
                    <div class="row">
                        <div class="col-4" style="display: flex;align-items: center;justify-content: center;">
                            <img src="https://static.vecteezy.com/system/resources/previews/021/076/234/non_2x/penalty-icon-design-free-vector.jpg" width="100%">
                        </div>
                        <!-- Columna con título y descripción -->
                        <div class="col">
                            <p>Una vez adquieres una penalizacion no puedes eliminarla, segun el tiempo varia la infracción. Consulta la tabla. </p>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <!-- Tercera Columna  -->
        <div class="col">


            <div class="card" style="margin-top:10px;margin-bottom:30px;margin-right:30px; border-radius: 20px;">
                <div class="card-body">

                    <!-- Fila dentro de la tarjeta -->
                    <div class="row">
                        <div class="col-4" style="display: flex;align-items: center;justify-content: center;">
                            <img src="https://static.vecteezy.com/system/resources/previews/016/953/565/original/penalty-card-icon-design-free-vector.jpg" width="100%">
                        </div>
                        <div class="col">

                            <p>Recuerda que al llegar a 3 penalizaciones no podrás participar en el alquiler de libros y no habrá manera de resolverlo. </p>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <!-- Tabla sistema de penalizaciones -->
    <div class="card" style="margin-top:10px;margin-bottom:30px;margin-right:30px; border-radius: 20px;">
        <div class="card-body">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Tiempo alquilado</th>
                        <th scope="col">Después de 7 dias</th>
                        <th scope="col">Después de 15 dias</th>
                        <th scope="col">Después de 30 dias</th>
                        <th scope="col">Después de 75 dias</th>
                        <th scope="col">Después de 90 dias</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">7 dias</th>

                        <td>+1 penalización</td>
                        <td>+2 penalizaciones</td>
                        <td  colspan="3"><center>+ 3 penalizaciones</center></td>

                </tr>
                <tr>
                    <th scope="row">15 dias</th>

                    <td></td>
                    <td>+1 penalizaciones</td>
                    <td>+ 2 penalizaciones</td>
                    <td colspan="2"> <center>+ 3 penalizaciones</center></td>

                </tr>
                <tr>
                    <th scope="row">30 dias</th>
                    <td></td>
                    <td></td>
                    <td>+1 penalización</td>
                    <td>+2 penalizaciones</td>
                    <td>+ 3 penalizaciones</td>
                </tr>
                </tbody>
            </table>
        </div></div>

</div>
<!-- Pie de pagina -->
<%@include file= "templates/footer2.jsp" %> 
<%@include file= "templates/footer.jsp" %> <!-- Incluir footer -->