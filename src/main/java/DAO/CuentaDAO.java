package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.*;
import Util.DBConnection;

public class CuentaDAO {
    
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
