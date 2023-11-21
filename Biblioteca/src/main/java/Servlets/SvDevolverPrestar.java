/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import com.umariana.biblioteca.Biblioteca;
import com.umariana.biblioteca.Libro;
import com.umariana.biblioteca.Metodos;
import com.umariana.biblioteca.PersistenciaArchivo;
import com.umariana.biblioteca.Prestamo;
import com.umariana.biblioteca.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author maria
 */
@WebServlet(name = "SvDevolverPrestar", urlPatterns = {"/SvDevolverPrestar"})
public class SvDevolverPrestar extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


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
            // Configuración de la respuesta HTTP
            response.setContentType("text/html; charset=UTF-8");
            // Envío del campo de entrada HTML como respuesta al cliente
            response.getWriter().write(libroHtml);

        } else {
            // Maneja el caso en el que no se encuentra el libro
            response.setContentType("text/plain");
            response.setContentType("text/html; charset=UTF-8");
            // Envío de un mensaje indicando que el libro no está disponible
            response.getWriter().write("No disponible :(");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         // Extracción y conversión de parámetros del request a enteros
         int cedula =Integer.parseInt(request.getParameter("cedula"));
                int id =Integer.parseInt(request.getParameter("id"));
                int tiempo =Integer.parseInt(request.getParameter("dias"));
                
          Biblioteca libros = new Biblioteca(); //creacion de un objeto tipo biblioteca
          ServletContext context = getServletContext(); //variable necesaria para obtener el contexto del servlet   
          //se lee la informacion de los objetos ya guardados y se deserializan
          libros = PersistenciaArchivo.deserializarBiblioteca(context);
          // Encontrar el libro por su identificador (id)
          Libro libro=libros.encontrarLibro(id);
          // Analizar la penalización basada en el tiempo de préstamo y el tiempo actual
          int penalizacion=Metodos.analizarPenalizacion(libro.getPrestado().getTiempo(), tiempo);
          // Deserializar la lista de usuarios desde el contexto de la aplicación
          ArrayList<Usuarios> users = PersistenciaArchivo.deserializarUsuarios(context);
          // Encontrar al usuario por su número de cédula
          Usuarios user=Metodos.encontrarUsuario(cedula, users);
          // Actualizar la penalización del usuario
          user.setPenalizacion(user.getPenalizacion()+penalizacion);
          // Serializar la lista actualizada de usuarios de nuevo en el contexto de la aplicación
          PersistenciaArchivo.serializarUsuarios(users, context);
          // Realizar la operación de devolver el libro en la biblioteca
          libros.devolverLibro(id);

         // Serializar la biblioteca después de la operación de devolución
         PersistenciaArchivo.serializarBiblioteca(libros, context);
         // Actualizar la sesión del usuario con la nueva penalización
         request.getSession().setAttribute("penalizacion", user.getPenalizacion());
         // Redirigir a la página de la biblioteca con un mensaje de alerta y la penalización actualizada
         response.sendRedirect("biblioteca.jsp?alert=devolver&penalizacion="+user.getPenalizacion());
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
