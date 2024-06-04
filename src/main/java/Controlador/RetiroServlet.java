package Controlador;

import DAO.CuentaDAO;
import DAO.TransaccionDAO;
import Modelo.Cliente;
import Modelo.Cuenta;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

@WebServlet(name = "RetiroServlet", urlPatterns = {"/RetiroServlet"})
public class RetiroServlet extends HttpServlet {
    
    private static final double LIMITE_RETIRO = 20000000.0;
    private static final double COMISION_PORCENTAJE = 0.01;
    private static final double COMISION_MINIMO = 50000.0;
    private static final double COMISION_ALTERNATIVA = 100.0;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Obtener la sesion actual
        HttpSession session = request.getSession();
        Cliente usuario = (Cliente) session.getAttribute("usuario");

        //Verificar que el usuario este logueado
        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        //Obtener la informacion del retiro
        int cuentaId = Integer.parseInt(request.getParameter("cuentaId"));
        double cantidad = Double.parseDouble(request.getParameter("cantidad-retirar"));
        String moneda = request.getParameter("moneda");
        
        CuentaDAO cuentaDAO = new CuentaDAO();
        Cuenta cuenta = cuentaDAO.obtenerCuentaPorId(cuentaId);
        
        if (cuenta != null) {
            double cantidadEnPesos = convertirAPesos(cantidad, moneda);
            
            //Calcular la comision por transaccion
            double comision = 0.0;
            if (cantidadEnPesos < COMISION_MINIMO) {
                comision = COMISION_ALTERNATIVA;
            } else {
                comision = cantidadEnPesos * COMISION_PORCENTAJE;
            }
            
            //Verificar que la cantidad a retirar mas la comision sea valida (menor al saldo)
            boolean retiroValido = cuenta.retirar(cantidadEnPesos + comision);
            
            if(retiroValido) {
                
                //Verificar si el retiro es mayor a 20,000,000.00
                if (cantidadEnPesos > LIMITE_RETIRO) {
                    session.setAttribute("cuentaId", cuentaId);
                    session.setAttribute("cantidad", cantidadEnPesos);
                    response.sendRedirect("autenticacionAdicional.jsp");
                    return;
                }

                cuentaDAO.actualizarCuenta(cuenta);

                TransaccionDAO transaccionDAO = new TransaccionDAO();
                transaccionDAO.registrarTransaccion(cuentaId, -cantidadEnPesos + comision, "Retiro");
                transaccionDAO.registrarTransaccion(cuentaId, -comision, "Comision");
                
            } else {
                response.sendRedirect("cuentas.jsp?error=1");
            }
        } else {
        
        response.sendRedirect("cuentas.jsp");
        
        }
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
