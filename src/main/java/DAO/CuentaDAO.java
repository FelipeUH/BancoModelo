package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.*;
import Util.DBConnection;

public class CuentaDAO {
    
    public List<Cuenta> obtenerTodasLasCuentas() {
        List<Cuenta> cuentas = new ArrayList<>();
        String query = "SELECT * FROM cuentas";
        
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                
                Cuenta cuenta = null;
                String tipo = resultSet.getString("tipo_cuenta");
                
                switch (tipo.toLowerCase()) {
                    case "ahorros":
                        cuenta = new CuentaAhorros();
                        break;
                    case "corriente":
                        cuenta = new CuentaCorriente();
                    case "suprema":
                        cuenta = new CuentaSuprema();
                    default:
                }
                
                cuenta.setCuentaId(resultSet.getInt("cuenta_id"));
                cuenta.setClienteId(resultSet.getInt("cliente_id"));
                cuenta.setTipo(tipo);
                cuenta.setSaldo(resultSet.getDouble("saldo"));
                cuenta.setEstado(resultSet.getString("estado"));
                cuenta.setFechaApertura(resultSet.getDate("fecha_apertura"));
                
                cuentas.add(cuenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuentas;
    }
    
    public Cuenta obtenerCuentaPorId(int cuentaId) {
        String query = "SELECT * FROM cuentas WHERE cuenta_id = ?";
        Cuenta cuenta = null;
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, cuentaId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int id = resultSet.getInt("cuenta_id");
                int usuarioId = resultSet.getInt("cliente_id");
                double saldo = resultSet.getDouble("saldo");
                String tipo = resultSet.getString("tipo_cuenta");
                Date fechaApertura = resultSet.getDate("fecha_apertura");
                String estado = resultSet.getString("estado");
                
                switch (tipo.toLowerCase()) {
                    case "ahorros":
                        cuenta = new CuentaAhorros(cuentaId, usuarioId, tipo, saldo, estado, fechaApertura);
                        break;
                    case "corriente":
                        cuenta = new CuentaCorriente(cuentaId, usuarioId, tipo, saldo, estado, fechaApertura);
                        break;
                    case "suprema":
                        cuenta = new CuentaSuprema(cuentaId, usuarioId, tipo, saldo, estado, fechaApertura);
                        break;
                }
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cuenta;
    }
    
    public List<Cuenta> obtenerCuentasPorUsuario(int usuarioId) {
        List<Cuenta> cuentas = new ArrayList<>();
        String query = "SELECT * FROM cuentas WHERE cliente_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, usuarioId);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int cuentaId = resultSet.getInt("cuenta_id");
                double saldo = resultSet.getDouble("saldo");
                String tipo = resultSet.getString("tipo_cuenta");
                Date fechaApertura = resultSet.getDate("fecha_apertura");
                String estado = resultSet.getString("estado");

                Cuenta cuenta = null;
                switch (tipo.toLowerCase()) {
                    case "ahorros":
                        cuenta = new CuentaAhorros(cuentaId, usuarioId, tipo, saldo, estado, fechaApertura);
                        break;
                    case "corriente":
                        cuenta = new CuentaCorriente(cuentaId, usuarioId, tipo, saldo, estado, fechaApertura);
                        break;
                    case "suprema":
                        cuenta = new CuentaSuprema(cuentaId, usuarioId, tipo, saldo, estado, fechaApertura);
                        break;
                }

                if (cuenta != null) {
                    cuentas.add(cuenta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuentas;
    }
    
    public boolean actualizarCuenta(Cuenta cuenta) {
        String query = "UPDATE cuentas SET saldo = ? WHERE cuenta_id = ?";
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setDouble(1, cuenta.getSaldo());
            statement.setInt(2, cuenta.getCuentaId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }

    public boolean crearCuenta(Cuenta cuenta) {
        String query = "INSERT INTO cuentas (cliente_id, tipo_cuenta, estado, saldo, fecha_apertura) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, cuenta.getClienteId());
            statement.setString(2, cuenta.getTipo());
            statement.setString(3, cuenta.getEstado());
            statement.setDouble(4, cuenta.getSaldo());
            statement.setDate(5, new java.sql.Date(cuenta.getFechaApertura().getTime()));

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
