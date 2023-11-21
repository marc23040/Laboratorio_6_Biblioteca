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
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "SvLoginRegister", urlPatterns = {"/SvLoginRegister"})
public class SvLoginRegister extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    //REGISTER
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          //Obtener el contexto del servlet
        ServletContext context = getServletContext();
        
        // Obtener la cédula, nombre y contraseña desde la solicitud
        int cedula=Integer.parseInt(request.getParameter("cedula"));
        String nombre=request.getParameter("nombre");
        String contrasenia=request.getParameter("contrasenia");
        
        // Deserializar la lista de usuarios desde un archivo utilizando la clase PersistenciaArchivo
        ArrayList<Usuarios> users = PersistenciaArchivo.deserializarUsuarios(context);

        // Verificar si ya existe un usuario con la misma cédula
         if(Metodos.usuarioIg(cedula, users)){
             // Crear un nuevo objeto Usuarios con la información proporcionada
            Usuarios user = new Usuarios(cedula, nombre, contrasenia,0);
            
            // Agregar el nuevo usuario a la lista de usuarios
            users.add(user);

            // Serializar la lista actualizada de usuarios y guardarla en el archivo
            PersistenciaArchivo.serializarUsuarios(users, context);
            // Redireccionar a la página index.jsp con un parámetro indicando que se añadió exitosamente
            response.sendRedirect("index.jsp?add=si");
         }else{
              // Redireccionar a la página index.jsp con un parámetro indicando que no se pudo añadir
            response.sendRedirect("index.jsp?add=no");
         }          
      
    }

    //LOGIN
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
