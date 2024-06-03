package Controlador;

import DAO.CuentaDAO;
import Modelo.*;

import java.util.Date;
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

@WebServlet (name = "CrearCuentaServlet", urlPatterns = {"/CrearCuentaServlet"})
public class CrearCuentaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        Cliente usuario = null;
        if (session != null) {
            usuario = (Cliente) session.getAttribute("usuario");
        }

        String tipo = request.getParameter("tipo");
        double saldoInicial = Double.parseDouble(request.getParameter("saldo"));
        
        Cuenta nuevaCuenta = null;

        switch (tipo) {
            case "Ahorros":
                nuevaCuenta = new CuentaAhorros();
                break;
            case "Corriente":
                nuevaCuenta = new CuentaCorriente();
                break;
            case "Suprema":
                nuevaCuenta = new CuentaSuprema();
                break;
        }

        if (nuevaCuenta != null) {
            nuevaCuenta.setClienteId(usuario.getId());
            nuevaCuenta.setSaldo(saldoInicial);
            nuevaCuenta.setEstado("Abierta");
            nuevaCuenta.setTipo(tipo);
            nuevaCuenta.setFechaApertura(new Date());

            CuentaDAO cuentaDAO = new CuentaDAO();
            cuentaDAO.crearCuenta(nuevaCuenta);
            response.sendRedirect("cuentas.jsp");
        } else {
            response.sendRedirect("cuentas.jsp?error=tipoInvalido");
        }
    }        
}
