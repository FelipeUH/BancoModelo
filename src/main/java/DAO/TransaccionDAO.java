package DAO;

import java.sql.*;
import java.util.Date;
import Util.DBConnection;
import Modelo.Transaccion;
import java.util.List;
import java.util.ArrayList;

public class TransaccionDAO {
    
    public boolean registrarTransaccion(int cuentaId, double monto, String tipoTransaccion) {
        String query = "INSERT INTO transaccionesbanco (cuenta_id, tipo_transaccion, monto, fecha_transaccion) values (?, ?, ?, ?)";
        
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
        
            statement.setInt(1, cuentaId);
            statement.setString(2, tipoTransaccion);
            statement.setDouble(3, monto);
            statement.setDate(4, new java.sql.Date(new Date().getTime()));
            
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Transaccion> obtenerTransaccionPorCuenta(int cuentaId) {
        String query = "SELECT * FROM transaccionesbanco WHERE cuenta_id = ? ORDER BY fecha_transaccion DESC";
        List<Transaccion> transacciones = new ArrayList<>();
        
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
        
            statement.setInt(1, cuentaId);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Transaccion transaccion = new Transaccion();
                transaccion.setCuentaId(cuentaId);
                transaccion.setId(rs.getInt("transaccion_id"));
                transaccion.setTipo(rs.getString("tipo_transaccion"));
                transaccion.setMonto(rs.getDouble("monto"));
                transaccion.setFecha(rs.getDate("fecha_transaccion"));
                
                transacciones.add(transaccion);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return transacciones;
    }
    
}
