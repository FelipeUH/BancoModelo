package Controlador;

import Modelo.Cliente;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.ClienteDAO;

@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})
public class RegistroServlet extends HttpServlet {

     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String ciudad = request.getParameter("ciudad");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Cliente usuario = new Cliente(nombre, apellido, ciudad, telefono, email, password);
        
        ClienteDAO usuarioDAO = new ClienteDAO();
        boolean registrado = usuarioDAO.registrarCliente(usuario);
            
        if (registrado) {
            response.sendRedirect("index.jsp?registrado=1");
        } else {
            response.sendRedirect("registro.jsp?registrado=false");
        }
    }

}