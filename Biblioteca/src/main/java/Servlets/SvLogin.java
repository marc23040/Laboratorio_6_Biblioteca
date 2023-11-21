/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import com.umariana.biblioteca.Metodos;
import com.umariana.biblioteca.PersistenciaArchivo;
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
* SvLogin
 * @author Maria- Juan- Alejandro- Juan 
 */
@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Login
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           //Obtener el contexto del servlet
        ServletContext context = getServletContext();
        
        // Obtener la cédula y la contraseña desde la solicitud
        int cedula=Integer.parseInt(request.getParameter("cedula"));
        String contrasenia=request.getParameter("contrasenia");
 
        // Deserializar la lista de usuarios desde un archivo utilizando la clase PersistenciaArchivo
        ArrayList<Usuarios> users = PersistenciaArchivo.deserializarUsuarios(context);
        // Intentar realizar el ingreso del usuario utilizando el método ingresoUsuarios
        String ingreso=Metodos.ingresoUsuarios(cedula, contrasenia, users);
        // Encontrar al usuario correspondiente en la lista
        Usuarios user= Metodos.encontrarUsuario(cedula, users);
        // Verificar el resultado del intento de ingreso
         if(ingreso.equals("no")){
           // Enviar una solicitud de despacho a index.jsp con un parámetro indicando el resultado del ingreso
            request.getRequestDispatcher("index.jsp?ingreso=" + ingreso).forward(request, response);
         }else{
            // Establecer atributos de sesión para el usuario autenticado
            request.getSession().setAttribute("usuario", ingreso);
            request.getSession().setAttribute("cedula", cedula);
             request.getSession().setAttribute("penalizacion", user.getPenalizacion());
            // Redireccionar a la página login.jsp
            response.sendRedirect("login.jsp");
         }           
    }

}
