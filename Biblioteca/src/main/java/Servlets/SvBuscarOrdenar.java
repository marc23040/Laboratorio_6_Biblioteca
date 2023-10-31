/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author maria
 */
@WebServlet(name = "SvBuscarOrdenar", urlPatterns = {"/SvBuscarOrdenar"})
public class SvBuscarOrdenar extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         //Se obtiene el parametro de busqueda
        String terminoBusqueda = request.getParameter("ordenar");
        String genero = request.getParameter("genero");
        //Redirigimos con la variable para que cambie la tabla
        String ruta="biblioteca.jsp?ordenar="+terminoBusqueda+"&cedula="+request.getParameter("cedula");
        if(genero!=null && terminoBusqueda==null){
            ruta="librosGeneros.jsp?genero="+genero;
        }
        response.sendRedirect(ruta);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          String terminoBusqueda = request.getParameter("buscar");
          String terminoTabla = request.getParameter("buscarTabla");
          String terminoGeneros = request.getParameter("buscarGeneros");
           String ruta="biblioteca.jsp?buscar="+terminoBusqueda;

          if(terminoTabla!=null){
           ruta="listarLibros.jsp?buscar="+terminoTabla;   
          } else if(terminoGeneros!=null){
            ruta="librosGeneros.jsp?buscar="+terminoGeneros+"&genero="+request.getParameter("genero");
          }
        //Redirigimos con la variable para que cambie la tabla
       
        response.sendRedirect(ruta);
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
