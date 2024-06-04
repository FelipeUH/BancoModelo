package Servicios;

import DAO.CuentaDAO;
import DAO.TransaccionDAO;
import Modelo.Cuenta;
import java.util.List;

public class CobrarMantenimiento {
    
    public static void cobrarCuotas() {
        CuentaDAO cuentaDAO = new CuentaDAO();
        TransaccionDAO transaccionDAO = new TransaccionDAO();
        
        try {
            List<Cuenta> cuentas = cuentaDAO.obtenerTodasLasCuentas();
            
            for (Cuenta cuenta : cuentas) {
                
                double cuota = cuenta.cobrarComision();
                cuentaDAO.actualizarCuenta(cuenta);
                transaccionDAO.registrarTransaccion(cuenta.getCuentaId(), -cuota, "Mantenimiento");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
