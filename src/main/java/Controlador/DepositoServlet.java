package Controlador;

import DAO.CuentaDAO;
import DAO.TransaccionDAO;
import Modelo.Cuenta;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DepositoServlet", urlPatterns = {"/DepositoServlet"})
public class DepositoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int cuentaId = Integer.parseInt(request.getParameter("cuentaId"));
        double cantidad = Double.parseDouble(request.getParameter("cantidad-depositar"));
        String moneda = request.getParameter("moneda");
        
        CuentaDAO cuentaDAO = new CuentaDAO();
        Cuenta cuenta = cuentaDAO.obtenerCuentaPorId(cuentaId);
        
        if (cuenta != null) {
            cuenta.ingresar(cantidad, moneda);
            cuentaDAO.actualizarCuenta(cuenta);
            
            TransaccionDAO transaccionDAO = new TransaccionDAO();
            transaccionDAO.registrarTransaccion(cuentaId, convertirAPesos(cantidad, moneda), "Ingreso");
            
        }
        
        response.sendRedirect("cuentas.jsp");
        
    }

    private double convertirAPesos(double cantidad, String moneda) {
        switch (moneda) {
            case "dolares":
                return 3800.0 * cantidad;
            case "euros":
                return 4200.0 * cantidad;
            case "pesos":
            default:
                return cantidad;
        }
    }
    
}
