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
 * SvDevolverPrestar
 * @author Maria- Juan- Alejandro- Juan 
 */
@WebServlet(name = "SvDevolverPrestar", urlPatterns = {"/SvDevolverPrestar"})
public class SvDevolverPrestar extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    /**
     * Prestar
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         // Obtención de parámetros del formulario 
         int id =Integer.parseInt(request.getParameter("id"));
         int cedula=Integer.parseInt(request.getParameter("cedula"));
         String tiempo=request.getParameter("tiempo");
         int penalizacion=Integer.parseInt(request.getParameter("penalizacion"));
         // Creación de un objeto Prestamo
         Prestamo prestado=new Prestamo(cedula,tiempo);
         Biblioteca libros = new Biblioteca(); //creacion de un objeto tipo biblioteca
         ServletContext context = getServletContext(); //variable necesaria para obtener el contexto del servlet   
         // Deserialización de la Biblioteca desde el archivo
         libros = PersistenciaArchivo.deserializarBiblioteca(context);
         // Variable para indicar si el libro se prestó o no
         String alert="noPrestado";
         // Verificación de la penalización
         if(penalizacion<3){
             // Si la penalización es menor a 3, se presta el libro
             libros.prestarLibro(id, prestado);
             alert="prestado";
         } 
         // Serialización de la Biblioteca actualizada y guardado en el archivo correspondiente
         PersistenciaArchivo.serializarBiblioteca(libros, context);
         // Redirección a la página "biblioteca.jsp" indicando el resultado del préstamo
         response.sendRedirect("biblioteca.jsp?alert="+alert);
    }
     /**
     * Devolver
     */
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
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
