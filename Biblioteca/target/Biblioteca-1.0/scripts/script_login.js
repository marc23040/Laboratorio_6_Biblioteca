// Seleccionar el elemento con la clase "btn"
const toggler = document.querySelector(".btn");

// Añadir un evento de clic al elemento "toggler"
toggler.addEventListener("click", function () {
    // Alternar la clase "collapsed" en el elemento con el id "sidebar"
    document.querySelector("#sidebar").classList.toggle("collapsed");
});
  