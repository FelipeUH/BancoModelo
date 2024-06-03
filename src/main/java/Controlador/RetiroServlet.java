package Controlador;

import DAO.CuentaDAO;
import Modelo.Cuenta;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RetiroServlet", urlPatterns = {"/RetiroServlet"})
public class RetiroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int cuentaId = Integer.parseInt(request.getParameter("cuentaId"));
        double cantidad = Double.parseDouble(request.getParameter("cantidad-retirar"));
        String moneda = request.getParameter("moneda");
        
        CuentaDAO cuentaDAO = new CuentaDAO();
        Cuenta cuenta = cuentaDAO.obtenerCuentaPorId(cuentaId);
        
        if (cuenta != null) {
            
            boolean retiroValido = cuenta.retirar(cantidad, moneda);
            
            if(retiroValido) {
                cuentaDAO.actualizarCuenta(cuenta);
            } else {
                response.sendRedirect("cuentas.jsp?error=1");
            }
        }
        
        response.sendRedirect("cuentas.jsp");
        
    }

}
