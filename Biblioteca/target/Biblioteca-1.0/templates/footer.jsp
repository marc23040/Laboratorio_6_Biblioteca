<!-- Modal -->
<div class="modal fade" id="cambiarModal" tabindex="-1" aria-labelledby="cambiarModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Cambiar contraseña</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="SvGestiones2" method="POST" autocomplete="off" class="sign-up-form">
            
              <div >
                <label for="validationCustom01" class="form-label">Cedula</label>
                <input type="number" class="form-control" name="cedula" value="<%=session.getAttribute("cedula")%>" readonly required>
                <br>
              </div>
                <div >
                <label for="validationCustom01" class="form-label">Contraseña anterior</label>
                <input type="password" name="contraAnt" class="form-control" required>
                <br>
              </div>
                <div >
                <label for="validationCustom01" class="form-label">Contraseña nueva</label>
                <input type="password" name="contrasenia" class="form-control"  required>
                <br>
              </div>
                 
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
        </form>
      </div>
    </div>
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
                        <select class="form-select" name="tiempo" aria-label="¿Cuanto tiempo?" required>
                            <option value="7 dias">7 dias</option>
                            <option value="15 dias">15 dias</option>
                            <option value="1 mes">1 mes</option>
                        </select>
                       
                    </div>
                <div id="alquilar-details">
                </div>
                     <input name="cedula" value="<%=session.getAttribute("cedula")%>" hidden>     
                     <input name="penalizacion" value="<%=session.getAttribute("penalizacion")%>" hidden>       
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Volver</button>
                <button type="submit" class="btn btn-success" data-bs-dismiss="modal">Alquilar</button>
            </div>
                       </form>
        </div>
    </div>
</div>
             <!-- modal devolver -->
<div class="modal fade" id="devolverModal" tabindex="-1" aria-labelledby="devolverModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">¿Deseas devolver el libro? </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="SvDevolverPrestar" method="POST">
                    <div class="input-group mb-3">
                        <input type="number" class="form-control" placeholder="Dias que tuviste el libro"  min="1" step="1" name="dias" required>
                    <span class="input-group-text" id="basic-addon2">Dias</span>
                  </div>
                <div id="devolver-details">
                </div>
                     <input name="cedula" value="<%=session.getAttribute("cedula")%>" hidden>            
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                <button type="submit" class="btn btn-success" >Devolver</button>
            </div>
                       </form>
        </div>
    </div>
</div>

            


<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
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
    $(document).ready(function () {
        $('[data-bs-toggle="devolver"]').on('click', function () {
            var alq = $(this).data('nombre');

            $.ajax({
                url: 'SvDevolverPrestar?id=' + alq,
                method: 'GET',
                success: function (data) {
                    $('#devolver-details').html(data);
                    $('#devolverModal').modal('show'); // Muestra el modal una vez que se han cargado los detalles del libro
                },
                error: function () {
                    console.log('Error al cargar los detalles del libro.');
                }
            });
        });
    });
</script>
</body>

</html>
