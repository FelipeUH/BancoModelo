package Controlador;

import DAO.InformeDAO;
import Modelo.Cliente;
import Modelo.Transaccion;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "InformeServlet", urlPatterns = {"/InformeServlet"})
public class InformeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null ) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        InformeDAO informeDAO = new InformeDAO();
        
        try {
            List<Cliente> clientesAtendidosHoy = informeDAO.obtenerClientesAtendidosHoy();
            List<Transaccion> transaccionesDelDia = informeDAO.obtenerTransaccionesDelDia();
            double totalRecaudado = informeDAO.obtenerTotalRecaudadoHoy();
            double totalRetirado = informeDAO.obtenerTotalRetiradoHoy();

            request.setAttribute("clientesAtendidosHoy", clientesAtendidosHoy);
            request.setAttribute("transaccionesDelDia", transaccionesDelDia);
            request.setAttribute("totalRecaudado", totalRecaudado);
            request.setAttribute("totalRetirado", totalRetirado);

            request.getRequestDispatcher("informes.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp");
        }
        
    }

}
