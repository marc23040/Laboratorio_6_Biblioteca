/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import com.umariana.biblioteca.Biblioteca;
import com.umariana.biblioteca.Libro;
import com.umariana.biblioteca.PersistenciaArchivo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author maria
 */
@MultipartConfig
@WebServlet(name = "SvEliminarEditar", urlPatterns = {"/SvEliminarEditar"})
public class SvEliminarEditar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtención del ID del Libro
       int id =Integer.parseInt(request.getParameter("id"));
        // Creación de un nuevo objeto Biblioteca
        Biblioteca libros = new Biblioteca();
        // Obtención del contexto del servlet
        ServletContext context = getServletContext();
        
        // Deserialización de la Biblioteca desde el archivo
        libros = PersistenciaArchivo.deserializarBiblioteca(context);
        // Eliminación del libro con el ID proporcionado
        libros.eliminarLibro(id);
        // Serialización de la Biblioteca actualizada y guardado en el archivo correspondiente
        PersistenciaArchivo.serializarBiblioteca(libros, context);
        // Redirección a la página "listarLibros.jsp" indicando que el libro fue eliminado con éxito
        response.sendRedirect("listarLibros.jsp?alert=eliminar");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        ServletContext context = getServletContext(); //variable necesaria para obtener el contexto del servlet
         Biblioteca libros = PersistenciaArchivo.deserializarBiblioteca(context);// //creacion de un objeto tipo biblioteca
         // Obtener la parte del archivo      
        Part imagenPart = request.getPart("fotoPortada");
        System.out.println("imagenPart" + imagenPart);

        // Nombre original del archivo
        String fileName = imagenPart.getSubmittedFileName();
        System.out.println("fileName: " + fileName);

        // Directorio donde se almacenara el archivo
        String uploadDirectory = getServletContext().getRealPath("imagenes");
        System.out.println("uploadDirectory: " + uploadDirectory);

        //Ruta completa del archivo
        String filePath = uploadDirectory + File.separator + fileName;
        System.out.println("filePath: " + filePath);

        // Lectura de la imagen del formulario y escritura en un archivo en el servidor
        try (InputStream input = imagenPart.getInputStream(); OutputStream output = new FileOutputStream(filePath)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        }
        // Obtención de parámetros del formulario
        int id =Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        int anio = Integer.parseInt(request.getParameter("anio"));
        String genero = request.getParameter("genero");
        String fotoPortada = fileName;
        
        // Deserialización de la Biblioteca desde el archivo
        libros = PersistenciaArchivo.deserializarBiblioteca(context);
        // Edición del libro con los nuevos datos proporcionados
        libros.editar(id, titulo, autor, anio, genero, fotoPortada);
        // Serialización de la Biblioteca actualizada y guardado en el archivo correspondiente
        PersistenciaArchivo.serializarBiblioteca(libros, context);
      
        // Redirección a la página "listarLibros.jsp" indicando que el libro fue editado con éxito
        response.sendRedirect("listarLibros.jsp?alert=editar");
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
