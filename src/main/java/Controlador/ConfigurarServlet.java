package Controlador;

import Modelo.Cliente;
import DAO.ClienteDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "ConfigurarServlet", urlPatterns = {"/ConfigurarServlet"})
public class ConfigurarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Cliente usuario = null;
        if (session != null) {
            usuario = (Cliente) session.getAttribute("usuario");
        }
        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String ciudad = request.getParameter("ciudad");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        ClienteDAO usuarioDAO = new ClienteDAO();
        boolean actualizado = usuarioDAO.actualizarCliente(usuario.getId(), nombre, apellido, ciudad, telefono, email, password);
        
        if (actualizado) {
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCiudad(ciudad);
            usuario.setTelefono(telefono);
            usuario.setEmail(email);
            usuario.setContrasena(password);
            session.setAttribute("usuario", usuario);
            
            response.sendRedirect("index.jsp?update=1");
        } else {
            response.sendRedirect("configuracion.jsp?error=1");
        }
        
    }

}
