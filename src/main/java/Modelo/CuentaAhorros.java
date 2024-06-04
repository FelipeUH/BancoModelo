package Modelo;

import java.util.Date;

public class CuentaAhorros extends Cuenta{

    private final double COMISION = 5000.0;
    
    public CuentaAhorros() {
    }
    
    public CuentaAhorros(int cuentaId, int clienteId, String tipo, double saldo, String estado, Date fechaApertura) {
        super(cuentaId, clienteId, tipo, saldo, estado, fechaApertura);
    }
    
    @Override
    public double cobrarComision() {
        this.setSaldo(this.getSaldo() - COMISION);
        return COMISION;
    }
    
}
