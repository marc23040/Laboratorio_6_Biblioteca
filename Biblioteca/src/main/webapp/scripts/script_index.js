// Seleccionar todos los elementos con la clase "input-field"
const inputs = document.querySelectorAll(".input-field");
// Seleccionar todos los elementos con la clase "toggle"
const toggle_btn = document.querySelectorAll(".toggle");
// Seleccionar el elemento "main"
const main = document.querySelector("main");
// Seleccionar todos los elementos con la clase "bullets" y dentro de ellos, los elementos "span"
const bullets = document.querySelectorAll(".bullets span");
// Seleccionar todos los elementos con la clase "image"
const images = document.querySelectorAll(".image");

// Añadir eventos a los elementos con la clase "input-field"
inputs.forEach((inp) => {
  // Evento de enfoque
  inp.addEventListener("focus", () => {
    inp.classList.add("active");
  });
  // Evento de pérdida de enfoque
  inp.addEventListener("blur", () => {
    // Si el valor no está vacío, mantener la clase "active"; de lo contrario, quitarla
    if (inp.value !== "") return;
    inp.classList.remove("active");
  });
});

// Añadir eventos a los elementos con la clase "toggle"
toggle_btn.forEach((btn) => {
  btn.addEventListener("click", () => {
    // Alternar la clase "sign-up-mode" en el elemento "main"
    main.classList.toggle("sign-up-mode");
  });
});

// Función para mover el slider
function moveSlider() {
  // Obtener el índice del atributo "data-value" del elemento clickeado
  let index = this.dataset.value;

  // Obtener la imagen actual y mostrarla, ocultando las demás
  let currentImage = document.querySelector(`.img-${index}`);
  images.forEach((img) => img.classList.remove("show"));
  currentImage.classList.add("show");

  // Mover el grupo de texto hacia arriba o abajo dependiendo del índice
  const textSlider = document.querySelector(".text-group");
  textSlider.style.transform = `translateY(${-(index - 1) * 2.2}rem)`;

  // Cambiar la clase "active" en los bullets
  bullets.forEach((bull) => bull.classList.remove("active"));
  this.classList.add("active");
}

// Añadir eventos a los elementos con la clase "bullets"
bullets.forEach((bullet) => {
  bullet.addEventListener("click", moveSlider);
});


