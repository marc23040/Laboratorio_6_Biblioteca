/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import com.umariana.biblioteca.Biblioteca;
import com.umariana.biblioteca.Libro;
import com.umariana.biblioteca.Metodos;
import com.umariana.biblioteca.PersistenciaArchivo;
import com.umariana.biblioteca.Usuarios;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SvGestiones2
 *
 * @author Maria- Juan- Alejandro- Juan
 */
@WebServlet(name = "SvGestiones2", urlPatterns = {"/SvGestiones2"})
public class SvGestiones2 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
     /**
     * Mostrar los datos anteriores al editar
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Biblioteca libros = new Biblioteca(); //creacion de un objeto tipo biblioteca
        ServletContext context = getServletContext(); //variable necesaria para obtener el contexto del servlet
        //se lee la informacion de los objetos ya guardados y se deserializan
        libros = PersistenciaArchivo.deserializarBiblioteca(context);//
        //pedimos el titulo del libro y lo guardamos en una variable
        int id = Integer.parseInt(request.getParameter("id"));

        //creamos un nuevo libro y llamamos al metodo encontrarLibro para adquirir sus atributos
        //enviamos como parametro el titulo para que el metodo lo filtre en el array 
        Libro li = libros.encontrarLibro(id);
        // Obtención del título del libro
        String titulo = li.getTitulo();
        System.out.println(titulo);
        // Verificación si el objeto 'li' (Libro) no es nulo
        if (li != null) {
            // Sección para el caso en que 'li' (Libro) no sea nulo 
            // Construcción de una cadena que representa el formulario de edición del libro
            String libroHtml = "<div class=\"input-group mb-3\">"
                    + "<span class=\"input-group-text\" id=\"basic-addon1\">Id</span>"
                    + "<input type=\"text\" name=\"id\" id=\"id\" class=\"form-control\" aria-describedby=\"basic-addon1\" value=\"" + id + "\"  readonly>"
                    + // Sección para el título del libro
                    " </div>" + "<div class=\"input-group mb-3\">"
                    + "<span class=\"input-group-text\" id=\"basic-addon1\">Titulo</span>"
                    + "<input type=\"text\" name=\"titulo\" id=\"titulo\" class=\"form-control\" aria-describedby=\"basic-addon1\"  value=\"" + titulo + "\" required>"
                    + // Sección para el autor del libro
                    " </div>" + "<div class=\"input-group mb-3\">"
                    + "<span class=\"input-group-text\" id=\"basic-addon1\">Autor</span>"
                    + "<input type=\"text\" id=\"autor\" name=\"autor\" class=\"form-control\" aria-describedby=\"basic-addon1\" value=\"" + li.getAutor() + "\"  required>"
                    + // Sección para el año de publicación del libro
                    " </div>" + "<div class=\"input-group mb-3\">"
                    + "<span class=\"input-group-text\" id=\"basic-addon1\">Año</span>"
                    + "<input type=\"number\" id=\"anio\" name=\"anio\" class=\"form-control\" aria-describedby=\"basic-addon1\" value=\"" + li.getAnoPublicacion() + "\"  required>"
                    + // Sección para seleccionar el género del libro
                    " </div>" + "<div class=\"input-group mb-3\">"
                    + "<span class=\"input-group-text\" id=\"basic-addon1\">Genero</span>"
                    + "<select class=\"form-select\" name=\"genero\" aria-label=\"Default select example\">"
                    + "<option selected value=\"" + li.getGenero() + "\" >" + li.getGenero() + "</option>"
                    + "<option value=\"Aventuras\">Aventuras</option>"
                    + "<option value=\"Ciencia Ficcion\">Ciencia Ficción</option>"
                    + "<option value=\"Romance\">Romance</option>"
                    + "<option value=\"Fantasia\">Fantasia</option>"
                    + "<option value=\"Humor\">Humor</option>"
                    + "<option value=\"Poesia\">Poesia</option>"
                    + "<option value=\"Mitologia\">Mitologia</option>"
                    + "<option value=\"Teatro\">Teatro</option>"
                    + "<option value=\"Infantil\">Infantil</option>"
                    + "<option value=\"Escolar\">Escolar</option>"
                    + "</select>"
                    + // Sección para cargar la portada del libro
                    "</div>" + "<div class=\"input-group mb-3\">"
                    + "<span class=\"input-group-text\" id=\"basic-addon1\">Portada</span>"
                    + "<input type=\"file\" name=\"fotoPortada\" class=\"form-control\" id=\"fotoPortada\" accept=\"image/*\" required>"
                    + "</div>";

            // Configuración de la respuesta
            response.setContentType("text/html; charset=UTF-8");
            // Envío de la cadena como respuesta al cliente
            response.getWriter().write(libroHtml);
        } else {
            // Sección para el caso en el que no se encuentra el libro
            // Configuración de la respuesta
            response.setContentType("text/plain");
            response.setContentType("text/html; charset=UTF-8");
            // Envío de un mensaje indicando que el libro no está disponible
            response.getWriter().write("No disponible :(");
        }
    }
     /**
     * Cambiar contraseña
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Obtener el contexto del servlet
        ServletContext context = getServletContext();
        // Deserializar la lista de usuarios desde un archivo utilizando la clase PersistenciaArchivo
        ArrayList<Usuarios> users = PersistenciaArchivo.deserializarUsuarios(context);
        // Variable para indicar si hubo un cambio en la contraseña
        String cambio = "no";
        // Obtener la cédula, contraseña antigua y nueva contraseña
        int cedula = Integer.parseInt(request.getParameter("cedula"));
        String contraVieja = request.getParameter("contraAnt");
        String contraNueva = request.getParameter("contrasenia");
        // Verificar si el ingreso del usuario con la contraseña antigua es exitoso
        if (!Metodos.ingresoUsuarios(cedula, contraVieja, users).equals("no")) {
            // Encontrar al usuario correspondiente en la lista
            Usuarios user = Metodos.encontrarUsuario(cedula, users);
            // Establecer la nueva contraseña para el usuario
            user.setContrasena(contraNueva);
            // Serializar la lista actualizada de usuarios y guardarla en el archivo
            PersistenciaArchivo.serializarUsuarios(users, context);
            // Indicar que ha habido un cambio exitoso
            cambio = "si";
        }
        // Redireccionar a la página de inicio de sesión con el parámetro de cambio
        request.getRequestDispatcher("biblioteca.jsp?cambio=" + cambio).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
