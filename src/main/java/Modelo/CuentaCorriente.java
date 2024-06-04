package Modelo;

import java.util.Date;

public class CuentaCorriente extends Cuenta {

    private final double COMISION = 10000.0;
    
    public CuentaCorriente() {
    }

    public CuentaCorriente(int cuentaId, int clienteId, String tipo, double saldo, String estado, Date fechaApertura) {
        super(cuentaId, clienteId, tipo, saldo, estado, fechaApertura);
    }
    
    @Override
    public double cobrarComision() {
        this.setSaldo(this.getSaldo() - COMISION);
        return COMISION;
    }
    
}
