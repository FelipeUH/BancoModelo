package Controlador;

import DAO.CuentaDAO;
import DAO.TransaccionDAO;
import Modelo.Cuenta;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "DepositoServlet", urlPatterns = {"/DepositoServlet"})
public class DepositoServlet extends HttpServlet {
    
    private static final double COMISION_PORCENTAJE = 0.01;
    private static final double COMISION_MINIMO = 50000.0;
    private static final double COMISION_ALTERNATIVA = 100.0;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Obtener datos del deposito
        int cuentaId = Integer.parseInt(request.getParameter("cuentaId"));
        double cantidad = Double.parseDouble(request.getParameter("cantidad-depositar"));
        String moneda = request.getParameter("moneda");
        
        CuentaDAO cuentaDAO = new CuentaDAO();
        Cuenta cuenta = cuentaDAO.obtenerCuentaPorId(cuentaId);
        
        double cantidadEnPesos = convertirAPesos(cantidad, moneda);
        
        //Calcular comision
        double comision = 0.0;
        if (cantidadEnPesos < COMISION_MINIMO) {
            comision = COMISION_ALTERNATIVA;
        } else {
            comision = cantidadEnPesos * COMISION_PORCENTAJE;
        }
        
        //Ingresar cantidad a la cuenta y registrar transacciones
        if (cuenta != null) {
            cuenta.ingresar(cantidadEnPesos);
            cuentaDAO.actualizarCuenta(cuenta);
            
            TransaccionDAO transaccionDAO = new TransaccionDAO();
            transaccionDAO.registrarTransaccion(cuentaId, cantidadEnPesos - comision, "Ingreso");
            transaccionDAO.registrarTransaccion(cuentaId, -comision, "Comision");
            
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
