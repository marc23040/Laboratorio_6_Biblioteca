<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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

<script src="https://code.jquery.com/jquery-3.2.1.js"></script>

</body>

</html>
