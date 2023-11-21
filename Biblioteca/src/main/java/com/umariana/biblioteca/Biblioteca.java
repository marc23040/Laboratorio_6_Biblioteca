/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Biblioteca. Representa la lista doblemente enlazada.
 *
 * @author Maria- Juan- Alejandro- Juan
 */
public class Biblioteca implements Serializable {

    private Nodo primero; //Cabeza del nodo
    private Nodo ultimo; //Cola del nodo

    /**
     * Constructor
     */
    public Biblioteca() {
        primero = null;
        ultimo = null;
    }

    /**
     * Insertar libro
     *
     * @param libro
     */
    public void insertar(Libro libro) {
        Nodo nuevo = new Nodo(libro, null, null);

        // Caso 1: La biblioteca está vacía
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            // Caso 2: La biblioteca no está vacía
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = nuevo;
        }
        System.out.println("se inserto exitosamente");
    }

    /**
     * Genera una tabla HTML con la información de los libros en la lista.
     *
     * @return Una cadena de texto que representa la tabla HTML.
     */
    public String tabla() {
        Nodo actual = primero; // Comenzamos desde el primer nodo
        StringBuilder tablaHtml = new StringBuilder();
        String estado = "Disponible";
        boolean encontrados = false;

        // Contenido de la tabla
        while (actual != null) {
            Libro libro = actual.getLibro();
            if (libro.getPrestado() != null) {
                estado = "Prestado a " + libro.getPrestado().getCedula();
            } else {
                estado = "Disponible";
            }

            tablaHtml.append("<tr>");
            tablaHtml.append("<td>").append(libro.getTitulo()).append("</td>");
            tablaHtml.append("<td>").append(libro.getAutor()).append("</td>");
            tablaHtml.append("<td>").append(libro.getAnoPublicacion()).append("</td>");
            tablaHtml.append("<td>").append(libro.getGenero()).append("</td>");
            tablaHtml.append("<td>").append(estado).append("</td>");
            tablaHtml.append("<td> <a href=\"#\" class=\"btn btn-outline-primary\" data-bs-toggle=\"ver\" data-bs-target=\"#exampleModal\" data-nombre=\"" + libro.getId() + "\"><i class=\"fas fa-eye\"></i> </a>");
            tablaHtml.append("<a href=\"#\" class=\"btn btn-outline-warning\"  data-bs-toggle=\"editar\" data-bs-target=\"#editarModal\" data-nombre=\"" + libro.getId() + "\"><i class=\"fas fa-pencil-alt\"></i></a>");
            tablaHtml.append("<a href=\"#\" class=\"btn btn-outline-danger deleteButton\" id=\"deleteButton\" data-titulo=\"" + libro.getId() + "\"><i class=\"fas fa-trash-alt\"></i></a></td>");
            tablaHtml.append("</tr>");
            encontrados = true;
            actual = actual.getSiguiente(); // Avanzar al siguiente nodo
        }

        // Mensaje si no hay libros
        if (!encontrados) {
            tablaHtml.append("<tr>");
            tablaHtml.append("<td colspan=\"6\">No hay libros</td>");
            tablaHtml.append("</tr>");
        }

        // Cierre de la tabla
        tablaHtml.append("</table>");

        return tablaHtml.toString();
    }

    /**
     * Encontrar libro
     *
     * @param id
     * @return
     */
    public Libro encontrarLibro(int id) {
        Nodo actual = primero; // Comenzamos desde el primer nodo

        while (actual != null) { // Recorremos la lista mientras haya nodos
            Libro libro = actual.getLibro(); // Obtenemos el libro del nodo actual
            if (libro.getId() == id) {
                return libro;
            }
            actual = actual.getSiguiente(); // Avanzamos al siguiente nodo
        }
        return null;

    }

    /**
     * Eliminar libro
     *
     * @param id
     */
    public void eliminarLibro(int id) {
        Nodo actual = primero;

        // Recorre la lista buscando el libro con el título dado
        while (actual != null) {
            if (actual.getLibro().getId() == id) {
                if (actual == primero) {
                    primero = actual.getSiguiente();
                    if (primero != null) {
                        primero.setAnterior(null);
                    }
                } else if (actual == ultimo) {
                    ultimo = actual.getAnterior();
                    if (ultimo != null) {
                        ultimo.setSiguiente(null);
                    }
                } else {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                }
                return; // Libro encontrado y eliminado
            }
            actual = actual.getSiguiente();
        }
    }

    /**
     * Edita la información de un libro en la lista.
     *
     * @param id El identificador del libro a editar.
     * @param titulo El nuevo título del libro.
     * @param autor El nuevo autor del libro.
     * @param anio El nuevo año de publicación del libro.
     * @param genero El nuevo género del libro.
     * @param foto La nueva URL de la foto de portada del libro.
     */
    public void editar(int id, String titulo, String autor, int anio, String genero, String foto) {
        Nodo actual = primero;
        boolean encontrado = false; // Bandera

        while (actual != null && !encontrado) {
            Libro libro = actual.getLibro();
            if (libro.getId() == id) { // Al encontrar el libro
                libro.setTitulo(titulo);
                libro.setAutor(autor);
                libro.setAnoPublicacion(anio);
                libro.setGenero(genero);
                libro.setFotoPortada(foto);
                encontrado = true; // Finaliza el ciclo
            }
            actual = actual.getSiguiente(); // Continua recorriendo la lista
        }
    }

    /**
     * Genera una tabla HTML con la información de los libros que coinciden con
     * el término de búsqueda.
     *
     * @param terminoBusqueda Término de búsqueda para encontrar libros por
     * título o autor.
     * @return Una cadena de texto con el código HTML de la tabla.
     */
    public String tablaBusqueda(String terminoBusqueda) {
        Nodo actual = primero; // Comenzamos desde el primer nodo
        StringBuilder tablaHtml = new StringBuilder();
        String estado = "Disponible";
        boolean encontrados = false;

        while (actual != null) {
            Libro libro = actual.getLibro();
            if (libro.getPrestado() != null) {
                estado = "Prestado a " + libro.getPrestado().getCedula();
            } else {
                estado = "Disponible";
            }

            if (libro.getTitulo().contains(terminoBusqueda) || libro.getAutor().contains(terminoBusqueda)) {
                tablaHtml.append("<tr>");
                tablaHtml.append("<td>").append(libro.getTitulo()).append("</td>");
                tablaHtml.append("<td>").append(libro.getAutor()).append("</td>");
                tablaHtml.append("<td>").append(libro.getAnoPublicacion()).append("</td>");
                tablaHtml.append("<td>").append(libro.getGenero()).append("</td>");
                tablaHtml.append("<td> <a href=\"#\" class=\"btn btn-outline-primary\" data-bs-toggle=\"ver\" data-bs-target=\"#exampleModal\" data-nombre=\"").append(libro.getId()).append("\"><i class=\"fas fa-eye\"></i> </a>");
                tablaHtml.append("<a href=\"#\" class=\"btn btn-outline-warning\"  data-bs-toggle=\"editar\" data-bs-target=\"#editarModal\" data-nombre=\"").append(libro.getId()).append("\"><i class=\"fas fa-pencil-alt\"></i></a>");
                tablaHtml.append("<a href=\"#\" class=\"btn btn-outline-danger deleteButton\" id=\"deleteButton\" data-titulo=\"").append(libro.getId()).append("\"><i class=\"fas fa-trash-alt\"></i></a></td>");
                tablaHtml.append("</tr>");
                encontrados = true;
            }
            actual = actual.getSiguiente(); // Avanzar al siguiente nodo
        }

        if (!encontrados) {
            tablaHtml.append("<tr>");
            tablaHtml.append("<td>No encontrado</td>");
            tablaHtml.append("<td></td>");
            tablaHtml.append("<td></td>");
            tablaHtml.append("<td></td>");
            tablaHtml.append("<td></td>");
            tablaHtml.append("<td></td>");
            tablaHtml.append("</tr>");
        }

        tablaHtml.append("</table>");
        return tablaHtml.toString();
    }

    /**
     * Genera un conjunto de tarjetas HTML con la información de los libros para
     * mostrar en la página de inicio de sesión.
     *
     * @return Una cadena de texto con el código HTML de las tarjetas de libros.
     */
    public String librosLogin() {
        Nodo actual = primero; // Comenzamos desde el primer nodo
        String libros = "<div class=\"row row-cols-1 row-cols-md-4 g-4\">"; // Inicia el contenedor de filas y columnas para las tarjetas
        boolean encontrado = false;

        while (actual != null) {
            Libro libro = actual.getLibro();
            libros += card(libro); // Agrega la tarjeta del libro a la cadena
            actual = actual.getSiguiente();
            encontrado = true;
        }

        // Cierra el contenedor de filas y columnas
        libros += "</div>";

        // Verifica si no se encontraron libros y, en ese caso, muestra un mensaje de no encontrado
        if (!encontrado) {
            libros = noEncontrado();
        }

        return libros;
    }

    /**
     * Genera un conjunto de tarjetas HTML con la información de los libros que
     * coinciden con el término de búsqueda.
     *
     * @param buscar Término de búsqueda para filtrar los libros.
     * @return Una cadena de texto con el código HTML de las tarjetas de libros
     * que coinciden con el término de búsqueda.
     */
    public String librosLoginBus(String buscar) {
        Nodo actual = primero; // Comenzamos desde el primer nodo

        String libros = "<div class=\"row row-cols-1 row-cols-md-4 g-4\">";
        boolean encontrado = false;

        while (actual != null) {
            Libro libro = actual.getLibro();
            // Verifica si el título o el autor del libro contiene el término de búsqueda
            if (libro.getTitulo().contains(buscar) || libro.getAutor().contains(buscar)) {
                libros += card(libro); // Agrega la tarjeta del libro a la cadena
                encontrado = true;
            }

            actual = actual.getSiguiente();
        }

        // Cierra el contenedor de filas y columnas
        libros += "</div>";

        // Verifica si no se encontraron libros y, en ese caso, muestra un mensaje de no encontrado
        if (!encontrado) {
            libros = noEncontrado();
        }

        return libros;
    }

    /**
     * Prestar libro
     *
     * @param id
     * @param prestado
     */
    public void prestarLibro(int id, Prestamo prestado) {
        Nodo actual = primero;
        boolean encontrado = false;//Bandera
        while (actual != null && !encontrado) {
            Libro libro = actual.getLibro();
            if (libro.getId() == id) {//Al encontrar la tarea
                libro.setPrestado(prestado);
                encontrado = true;//Finaliza ciclo
            }
            actual = actual.getSiguiente();//Continua recorriendo
        }
    }

    /**
     * Devolver libro
     *
     * @param id
     */
    public void devolverLibro(int id) {
        Nodo actual = primero;
        boolean encontrado = false;//Bandera
        while (actual != null && !encontrado) {
            Libro libro = actual.getLibro();
            if (libro.getId() == id) {//Al encontrar la tarea
                libro.setPrestado(null);
                encontrado = true;//Finaliza ciclo
            }
            actual = actual.getSiguiente();//Continua recorriendo
        }
    }

    public int id() {
        Nodo actual = primero;
        int id = 0;
        while (actual != null) {
            id = actual.getLibro().getId();
            actual = actual.getSiguiente();//Continua recorriendo
        }
        return id;
    }

    /**
     * Genera un conjunto de tarjetas HTML con la información de los libros
     * disponibles.
     *
     * @return Una cadena de texto con el código HTML de las tarjetas de libros
     * disponibles.
     */
    public String librosDisponibles() {
        Nodo actual = primero; // Comenzamos desde el primer nodo       
        String libros = "<div class=\"row row-cols-1 row-cols-md-4 g-4\">"; // Inicia el contenedor de filas y columnas para las tarjetas
        boolean encontrado = false;

        while (actual != null) {
            Libro libro = actual.getLibro();
            // Verifica si el libro no está prestado
            if (libro.getPrestado() == null) {
                libros += card(libro); // Agrega la tarjeta del libro a la cadena
                encontrado = true;
            }
            actual = actual.getSiguiente();
        }

        // Cierra el contenedor de filas y columnas
        libros += "</div>";

        // Verifica si no se encontraron libros disponibles y, en ese caso, muestra un mensaje de no encontrado
        if (!encontrado) {
            libros = noEncontrado();
        }

        return libros;
    }

    /**
     * Genera un conjunto de tarjetas HTML con la información de los libros
     * prestados a un usuario específico.
     *
     * @param cedula Número de cédula del usuario.
     * @return Una cadena de texto con el código HTML de las tarjetas de libros
     * prestados al usuario.
     */
    public String librosPrestados(int cedula) {
        Nodo actual = primero; // Comenzamos desde el primer nodo

        String libros = "<div class=\"row row-cols-1 row-cols-md-4 g-4\">";
        boolean encontrado = false;

        while (actual != null) {
            Libro libro = actual.getLibro();
            Prestamo info = libro.getPrestado();

            // Verifica si el libro está prestado y coincide con el número de cédula proporcionado
            if (libro.getPrestado() != null && info.getCedula() == cedula) {
                libros += "<div class=\"col\">"
                + "<div class=\"card-body\" style=\"min-height: 100%;\">"
                + "<img src=\"imagenes/" + libro.getFotoPortada() + "\" class=\"card-img-top\" alt=\"" + libro.getFotoPortada() + "\" width=\"200px\" height=\"300px\">"
                + "<center><h3 class=\"card-title\">" + libro.getTitulo() + "</h3></center>"
                + "<p class=\"card-text\"> <b> Autor: </b>" + libro.getAutor() + "</p>"
                + "<p class=\"card-text\"> <b>Año: </b>" + libro.getAnoPublicacion() + "</p>"
                + "<p class=\"card-text\"> <b>Genero: </b>" + libro.getGenero() + "</p>"
                + "<p class=\"card-text\"> Regresalo en: " + libro.getPrestado().getTiempo() + "</p>"
                + "<a href=\"#\" class=\"btn btn-primary\" data-bs-toggle=\"devolver\" data-bs-target=\"#devolverModal\" data-nombre=\"" + libro.getId() + "\" style=\"background-color: #5e3824;\">Devolver</a>"
                + "</div>"
                + "</div>"
                + "</div>";
                encontrado = true;
            }
            actual = actual.getSiguiente();
        }

        // Cierra el contenedor de filas y columnas
        libros += "</div>";

        // Verifica si no se encontraron libros prestados al usuario y, en ese caso, muestra un mensaje de no encontrado
        if (!encontrado) {
            libros = noEncontrado();
        }

        return libros;
    }

    /**
     * Genera un conjunto de tarjetas HTML con la información de los libros
     * pertenecientes a un género específico.
     *
     * @param genero El género de los libros que se desean mostrar.
     * @return Una cadena de texto con el código HTML de las tarjetas de libros
     * del género especificado.
     */
    public String librosGenero(String genero) {
        Nodo actual = primero; // Comenzamos desde el primer nodo

        String libros = "<div class=\"row row-cols-1 row-cols-md-4 g-4\">"; // Inicia el contenedor de filas y columnas para las tarjetas
        boolean encontrado = false;

        while (actual != null) {
            Libro libro = actual.getLibro();

            // Verifica si el libro pertenece al género proporcionado
            if (libro.getGenero().equals(genero)) {
                libros += card(libro);
                encontrado = true;
            }

            actual = actual.getSiguiente();
        }

        // Cierra el contenedor de filas y columnas
        libros += "</div>";

        // Verifica si no se encontraron libros del género especificado y, en ese caso, muestra un mensaje de no encontrado
        if (!encontrado) {
            libros = noEncontrado();
        }

        return libros;
    }

    /**
     * Genera un conjunto de tarjetas HTML con la información de los libros
     * pertenecientes a un género específico que coincidan con un término de
     * búsqueda.
     *
     * @param genero El género de los libros que se desean mostrar.
     * @param termino El término de búsqueda para filtrar los libros.
     * @return Una cadena de texto con el código HTML de las tarjetas de libros
     * del género especificado y que coincidan con el término de búsqueda.
     */
    public String librosGeneroBuscar(String genero, String termino) {
        Nodo actual = primero; // Comenzamos desde el primer nodo

       String libros = "<div class=\"row row-cols-1 row-cols-md-4 g-4\">"; // Inicia el contenedor de filas y columnas para las tarjetas
        boolean encontrado = false;

        while (actual != null) {
            Libro libro = actual.getLibro();

            // Verifica si el libro pertenece al género proporcionado y si coincide con el término de búsqueda
            if (libro.getGenero().equals(genero) && (libro.getTitulo().contains(termino) || libro.getAutor().contains(termino))) {
                libros += card(libro);
                encontrado = true;
            }

            actual = actual.getSiguiente();
        }

        // Cierra el contenedor de filas y columnas
        libros += "</div>";

        // Verifica si no se encontraron libros que coincidan con el género y término de búsqueda especificados, y en ese caso, muestra un mensaje de no encontrado
        if (!encontrado) {
            libros = noEncontrado();
        }

        return libros;
    }

    /**
     * Filtra los libros por año descendente
     *
     * @return Una cadena de texto con el código HTML de las tarjetas de libros
     * del género especificado y que coincidan con el término de búsqueda.
     */

    public String librosOrdenadosPorAnoDescendente() {
        // Crear una copia de la lista doblemente enlazada para no modificar la original
        Nodo actual = primero; // Comenzamos desde el primer nodo
        List<Libro> librosList = new ArrayList<>();
        boolean encontrado = false;

        while (actual != null) {
            librosList.add(actual.getLibro());
            actual = actual.getSiguiente();
        }

        // Ordenar la lista de libros por año de publicación en orden descendente
        librosList.sort((libro1, libro2) -> libro2.getAnoPublicacion() - libro1.getAnoPublicacion());

        // Generar la tabla con la lista de libros ordenada
        String libros = "<div class=\"row row-cols-1 row-cols-md-4 g-4\">";

        for (Libro libro : librosList) {
            libros += card(libro);
            encontrado = true;
        }
        libros += "</div>";
        if (!encontrado) {
            libros = noEncontrado();
        }
        return libros;
    }

    /**
     * Filtra los libros por año ascendente
     *
     * @return Una cadena de texto con el código HTML de las tarjetas de libros
     * del género especificado y que coincidan con el término de búsqueda.
     */
    public String librosOrdenadosPorAnoAscendente() {
        // Crear una copia de la lista doblemente enlazada para no modificar la original
        Nodo actual = primero; // Comenzamos desde el primer nodo
        List<Libro> librosList = new ArrayList<>();
        boolean encontrado = false;

        while (actual != null) {
            librosList.add(actual.getLibro());
            actual = actual.getSiguiente();
        }

        // Ordenar la lista de libros por año de publicación en orden ascendente
        librosList.sort((libro1, libro2) -> libro1.getAnoPublicacion() - libro2.getAnoPublicacion());

        // Generar la tabla con la lista de libros ordenada
        String libros = "<div class=\"row row-cols-1 row-cols-md-4 g-4\">";

        for (Libro libro : librosList) {
            libros += card(libro);
            encontrado = true;
        }
        libros += "</div>";
        if (!encontrado) {
            libros = noEncontrado();
        }

        return libros;
    }

    /**
     * Genera un mensaje en HTML indicando que no se encontraron elementos y
     * proporciona un enlace para contribuir.
     *
     * @return Una cadena de texto con el código HTML del mensaje de no
     * encontrado y el enlace para contribuir.
     */
    public String noEncontrado() {
        String libros = "<div class=\"box\" style=\" border-radius: 3.3rem;\">\n"
                + "<center>\n"
                + "<div class=\"card \" >\n"
                + "<div class=\"card-body\">\n"
                + "<h1 style=\"font-family: 'Poppins', sans-serif; font-size: 3rem; \"> Nada por aquí...</h1>\n"
                + "\n"
                + "<a href=\"gestionLibros.jsp\"style=\"font-family: 'Regular', sans-serif;font-size:3rem; margin-top: -50px; color:#a86b4c;\"> Contribuye!</a>\n"
                + "</div>\n"
                + "</div>\n"
                + "</center>\n"
                + "</div>";
        return libros;
    }

    /**
     * Genera el código HTML para mostrar la información de un libro en formato
     * de tarjeta.
     *
     * @param libro El objeto Libro que contiene la información del libro.
     * @return Una cadena de texto con el código HTML de la tarjeta del libro.
     */
    public String card(Libro libro) {
        // Inicio de la estructura HTML para la tarjeta del libro
        String libros = "<div class=\"col\" >"
                + "<div class=\"card\" style=\"min-height: 100%;\">"
                + "<div class=\"card-body\">"
                // Agrega la imagen del libro con la ruta de la foto obtenida del objeto Libro
                + "<img src=\"imagenes/" + libro.getFotoPortada() + "\" class=\"card-img-top\" alt=\"" + libro.getFotoPortada() + "\" >"
                // Agrega el título del libro centrado en la tarjeta
                + "<center><h3 class=\"card-title\">" + libro.getTitulo() + "</h3></center>"
                // Agrega el autor del libro como texto en la tarjeta
                + "<p class=\"card-text\"> <b> Autor: </b>" + libro.getAutor() + "</p>"
                // Agrega el año de publicación del libro como texto en la tarjeta
                + "<p class=\"card-text\"> <b>Año: </b>" + libro.getAnoPublicacion() + "</p>"
                // Agrega el género del libro como texto en la tarjeta
                + "<p class=\"card-text\"> <b>Genero: </b>" + libro.getGenero() + "</p>";

        // Verifica si el libro está disponible para alquilar
        if (libro.getPrestado() == null) {
            // Si está disponible, muestra un botón para alquilar el libro
            libros += " <center><a href=\"#\" class=\"btn btn-primary\" data-bs-toggle=\"alquilar\" data-bs-target=\"#alquilarModal\" data-nombre=\"" + libro.getId() + "\" style=\" background-color: #5e3824;\">Alquilar</a></center>";
        } else if (libro.getPrestado() != null) {
            // Si no está disponible, muestra la información de cuándo estará disponible
            libros += "<p><b>Disponible en: </b>" + libro.getPrestado().getTiempo() + "</p>";
        } else {
            // Si no está disponible y no hay información sobre cuándo estará disponible, muestra un mensaje indicando que no está disponible
            libros += "<p><b>No disponible</b>";
        }

        // Fin de la estructura HTML para la tarjeta del libro
        libros += "</div>"
                + "</div>"
                + "</div>";
        return libros;
    }
}
