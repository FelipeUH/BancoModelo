package Controlador;

import DAO.CuentaDAO;
import DAO.TransaccionDAO;
import Modelo.Cliente;
import Modelo.Cuenta;
import Util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "ValidarAutenticacionServlet", urlPatterns = {"/ValidarAutenticacionServlet"})
public class ValidarAutenticacionServlet extends HttpServlet {
    
    private static final double COMISION_PORCENTAJE = 0.01;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cliente usuario = (Cliente) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String contrasena = request.getParameter("password");
        double cantidad = Double.parseDouble(request.getParameter("cantidad"));
        int cuentaId = (int) session.getAttribute("cuentaId");

        if (validarContrasena(usuario.getEmail(), contrasena)) {
            procesarRetiro(request, response, cuentaId, cantidad);
        } else {
            session.setAttribute("mensaje", "Contraseña incorrecta. No se pudo realizar el retiro.");
            response.sendRedirect("cuentas.jsp");
        }
    }

    private boolean validarContrasena(String correo, String contrasena) {
        boolean valido = false;
        String query = "SELECT * FROM clientes WHERE email = ? AND contrasena = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, correo);
            statement.setString(2, contrasena);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                valido = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return valido;
    }

    private void procesarRetiro(HttpServletRequest request, HttpServletResponse response, int cuentaId, double cantidad) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CuentaDAO cuentaDAO = new CuentaDAO();
        TransaccionDAO transaccionDAO = new TransaccionDAO();
        double comision = cantidad * COMISION_PORCENTAJE;
        
        try {
            Cuenta cuenta = cuentaDAO.obtenerCuentaPorId(cuentaId);
            if (cuenta != null && cuenta.getSaldo() >= (cantidad + comision)) {
                cuenta.retirar(cantidad + comision);
                cuentaDAO.actualizarCuenta(cuenta);
                transaccionDAO.registrarTransaccion(cuentaId, -cantidad + comision, "Retiro");
                transaccionDAO.registrarTransaccion(cuentaId, -comision, "Comision");
                session.setAttribute("mensaje", "Retiro realizado con éxito.");
            } else {
                session.setAttribute("mensaje", "Saldo insuficiente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("mensaje", "Error al realizar el retiro.");
        }

        response.sendRedirect("cuentas.jsp");
    }
}

