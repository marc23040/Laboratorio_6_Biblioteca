/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import com.umariana.biblioteca.Biblioteca;
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
        int id =Integer.parseInt(request.getParameter("id"));

         Biblioteca libros = new Biblioteca(); //creacion de un objeto tipo biblioteca
         ServletContext context = getServletContext(); //variable necesaria para obtener el contexto del servlet   
         //se lee la informacion de los objetos ya guardados y se deserializan
         libros = PersistenciaArchivo.deserializarBiblioteca(context);//
         libros.devolverLibro(id);
         PersistenciaArchivo.serializarBiblioteca(libros, context);
         response.sendRedirect("biblioteca.jsp?alert=devolver");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
