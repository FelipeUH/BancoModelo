package Controlador;

import DAO.CuentaDAO;
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
        }
        
        response.sendRedirect("cuentas.jsp");
        
    }

}
