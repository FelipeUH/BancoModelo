package DAO;

import Modelo.Cliente;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {

    public ClienteDAO() {
    }
    
    public boolean registrarCliente(Cliente usuario) {
        String query = "INSERT INTO clientes (nombre, apellido, ciudad, telefono, email, contrasena) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getApellido());
            pst.setString(3, usuario.getCiudad());
            pst.setString(4, usuario.getTelefono());
            pst.setString(5, usuario.getEmail());
            pst.setString(6, usuario.getContrasena());

            int rowCount = pst.executeUpdate();
            return rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean actualizarCliente(int id, String nombre, String apellido, String ciudad, String telefono, String email, String contrasena) {
        String query = "UPDATE clientes SET nombre = ?, apellido = ?, ciudad = ?, telefono = ?, email = ?, contrasena = ? WHERE cliente_id = ?";
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, ciudad);
            statement.setString(4, telefono);
            statement.setString(5, email);
            statement.setString(6, contrasena);
            statement.setInt(7, id);
            
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
