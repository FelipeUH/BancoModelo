package Controlador;

import java.sql.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import Util.DBConnection;
import Modelo.Cliente;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtener los datos del formulario login
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        //Validar los datos en la base de datos
        boolean valido = validarUsuario(email, password);
        
        //Obtener los datos del usuario si es valido, y crear un objeto cliente para la sesion
        if (valido) {
            HttpSession session = request.getSession();
            String nombre = null, apellido = null, ciudad = null, telefono = null;
            int id = -1;
            
            ResultSet rs = obtenerDatosUsuario(email, password);
            try {
                rs.next();
                id = rs.getInt("cliente_id");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                ciudad = rs.getString("ciudad");
                telefono = rs.getString("telefono");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            Cliente usuario = new Cliente(nombre, apellido, ciudad, telefono, email, password);
            usuario.setId(id);
            session.setAttribute("usuario", usuario);
            response.sendRedirect("index.jsp?login=1");
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
        
    }

    private boolean validarUsuario(String correo, String contrasena){
        boolean valido = false;
        String query = "SELECT * FROM clientes WHERE email = ? AND contrasena = ?";
        
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);){
            statement.setString(1, correo);
            statement.setString(2, contrasena);
            ResultSet resultSet = statement.executeQuery();
            
            //Verificar que hay filas seleccionadas
            if (resultSet.next()) {
                valido = true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return valido;
    }
    
    private ResultSet obtenerDatosUsuario(String correo, String contrasena) {
        String query = "SELECT * FROM clientes WHERE email = ? AND contrasena = ?";
        ResultSet rs = null;
        
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);){
            statement.setString(1, correo);
            statement.setString(2, contrasena);
            rs = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
}