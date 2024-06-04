package Modelo;

import java.util.Date;

public class CuentaAhorros extends Cuenta{

    public CuentaAhorros() {
    }
    
    public CuentaAhorros(int cuentaId, int clienteId, String tipo, double saldo, String estado, Date fechaApertura) {
        super(cuentaId, clienteId, tipo, saldo, estado, fechaApertura);
    }
    
    @Override
    public double cobrarComision() {
        double comision = 5000.0;
        this.setSaldo(this.getSaldo() - comision);
        return comision;
    }
    
}
