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
@WebServlet(name = "SvAnadirVer", urlPatterns = {"/SvAnadirVer"})
public class SvAnadirVer extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
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

        // Verificar si el objeto Libro (li) no es nulo
        if (li != null) {
            // Construir una cadena HTML con información del libro
            String libroHtml = "<h2>Nombre: " + li.getTitulo() + "</h2>"
                    + "<p>Autor: " + li.getAutor() + "</p>"
                    + "<p>Año publicado: " + li.getAnoPublicacion() + "</p>"
                    + "<img src='imagenes/" + li.getFotoPortada() + "' alt='" + li.getFotoPortada() + "' width='100%'/>";
            // Establecer el tipo de contenido de la respuesta
            response.setContentType("text/html; charset=UTF-8");
            // Escribir la cadena HTML en el cuerpo de la respuesta
            response.getWriter().write(libroHtml);

        } else {
            /// Manejar el caso en el que el objeto Libro es nulo
            response.setContentType("text/plain");// Establecer el tipo de contenido como texto plano
            response.getWriter().write("Libro no encontrado"); // Escribir un mensaje indicando que el libro no está disponible
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        //Obtener el contexto del servlet
        ServletContext context = getServletContext();

        // Obtención de parámetros del formulario 
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        int anio = Integer.parseInt(request.getParameter("anio"));
        String genero = request.getParameter("genero");
        String fotoPortada = fileName;
       
        
        Biblioteca libros = new Biblioteca();
         
         // Deserialización de la biblioteca de libros desde el archivo
        libros = PersistenciaArchivo.deserializarBiblioteca(context);
        // Verificación si la biblioteca de libros es nula (primera vez)
        if (libros == null) {
            libros = new Biblioteca();
        }
        // Generación de un nuevo ID para el libro
        int id=libros.id()+1;
        // Creación de un nuevo objeto Libro
        Libro libro = new Libro(id,titulo, autor, anio, fotoPortada,genero, null);
        
        // Inserción del libro en la biblioteca
        libros.insertar(libro);
        // Serialización de la biblioteca de libros actualizada y guardado en el archivo correspondiente
        PersistenciaArchivo.serializarBiblioteca(libros, context);
        // Redirección a la página "gestionLibros.jsp" indicando que el libro fue añadido con éxito
        response.sendRedirect("gestionLibros.jsp?alert=anadido");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
