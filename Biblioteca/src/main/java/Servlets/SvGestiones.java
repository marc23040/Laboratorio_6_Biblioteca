/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import com.umariana.biblioteca.Biblioteca;
import com.umariana.biblioteca.Libro;
import com.umariana.biblioteca.PersistenciaArchivo;
import com.umariana.biblioteca.Prestamo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SvGestiones
 * @author Maria- Juan- Alejandro- Juan 
 */
@WebServlet(name = "SvGestiones", urlPatterns = {"/SvGestiones"})
public class SvGestiones extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

     /**
     * Mostrar id al prestar/devolver
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Biblioteca libros = new Biblioteca(); //creacion de un objeto tipo biblioteca
        ServletContext context = getServletContext(); //variable necesaria para obtener el contexto del servlet
        
        //se lee la informacion de los objetos ya guardados y se deserializan
        libros = PersistenciaArchivo.deserializarBiblioteca(context);//
        //pedimos el titulo del libro y lo guardamos en una variable
        int id =Integer.parseInt(request.getParameter("id"));

        //creamos un nuevo libro y llamamos al metodo encontrarLibro para adquirir sus atributos
        //enviamos como parametro el titulo para que el metodo lo filtre en el array 
        Libro li = libros.encontrarLibro(id);

        // Verificación si el objeto 'li' (Libro) no es nulo
        if (li != null) {
            // Sección para el caso en que 'li' (Libro) no sea nulo 
            // Construcción de un campo de entrada HTML oculto para el ID del libro
            String libroHtml = "<input name='id' type='text' value="+id+" hidden>";
            // Configuración de la respuesta
            response.setContentType("text/html; charset=UTF-8");
            // Envío del campo de entrada HTML como respuesta al cliente
            response.getWriter().write(libroHtml);

        } else {
            // Sección para el caso en el que no se encuentra el libro
            response.setContentType("text/plain");
            response.setContentType("text/html; charset=UTF-8");
            // Envío de un mensaje indicando que el libro no está disponible
            response.getWriter().write("No disponible :(");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
