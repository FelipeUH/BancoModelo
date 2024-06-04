package DAO;

import Modelo.Cliente;
import Modelo.Transaccion;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import Util.DBConnection;

public class InformeDAO {
    
    public List<Cliente> obtenerClientesAtendidosHoy() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT DISTINCT c.* FROM clientes c JOIN cuentas cu ON c.cliente_id = cu.cliente_id JOIN transaccionesbanco t ON cu.cuenta_id = t.cuenta_id WHERE t.fecha_transaccion = CURDATE()";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultSet.getInt("cliente_id"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setEmail(resultSet.getString("email"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }
    
    public List<Transaccion> obtenerTransaccionesDelDia() {
        List<Transaccion> transacciones = new ArrayList<>();
        String query = "SELECT * FROM transaccionesbanco WHERE DATE(fecha_transaccion) = CURDATE()";
        
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);) {
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Transaccion transaccion = new Transaccion();
                transaccion.setId(rs.getInt("transaccion_id"));
                transaccion.setCuentaId(rs.getInt("cuenta_id"));
                transaccion.setFecha(rs.getDate("fecha_transaccion"));
                transaccion.setMonto(rs.getDouble("monto"));
                transaccion.setTipo(rs.getString("tipo_transaccion"));
                transacciones.add(transaccion);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return transacciones;
    }
    
    public double obtenerTotalRecaudadoHoy() {
        double totalRecaudado = 0;
        String query = "SELECT SUM(monto) as total FROM transaccionesbanco WHERE tipo_transaccion IN ('Mantenimiento', 'Comision') AND fecha_transaccion = CURDATE()";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                totalRecaudado = resultSet.getDouble("total") * -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRecaudado;
    }
    
    public double obtenerTotalRetiradoHoy() {
        double totalRetirado = 0;
        String query = "SELECT SUM(monto) as total FROM transaccionesbanco WHERE tipo_transaccion = 'Retiro' AND DATE(fecha_transaccion) = CURDATE()";
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                totalRetirado = resultSet.getDouble("total") * -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRetirado;
        
    }
}
