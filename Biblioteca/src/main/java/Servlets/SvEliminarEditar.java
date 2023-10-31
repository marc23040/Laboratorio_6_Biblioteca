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
       int id =Integer.parseInt(request.getParameter("id"));
        Biblioteca libros = new Biblioteca();
        ServletContext context = getServletContext();
        libros = PersistenciaArchivo.deserializarBiblioteca(context);
        libros.eliminarLibro(id);
        PersistenciaArchivo.serializarBiblioteca(libros, context);
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

        try (InputStream input = imagenPart.getInputStream(); OutputStream output = new FileOutputStream(filePath)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        }
        //se lee la informacion de los objetos ya guardados y se deserializan

        //pedimos el titulo del libro y lo guardamos en una variable
        int id =Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        int anio = Integer.parseInt(request.getParameter("anio"));
        String genero = request.getParameter("genero");
        String fotoPortada = fileName;
        
         
        libros = PersistenciaArchivo.deserializarBiblioteca(context);
        libros.editar(id, titulo, autor, anio, genero, fotoPortada);
        PersistenciaArchivo.serializarBiblioteca(libros, context);
      
        response.sendRedirect("listarLibros.jsp?alert=editar");
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
