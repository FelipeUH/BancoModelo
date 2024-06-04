package Modelo;

import java.util.Date;

public class CuentaSuprema extends Cuenta {

    private final double COMISION = 50000.0;
    
    public CuentaSuprema() {
    }

    public CuentaSuprema(int cuentaId, int clienteId, String tipo, double saldo, String estado, Date fechaApertura) {
        super(cuentaId, clienteId, tipo, saldo, estado, fechaApertura);
    }
    
    @Override
    public double cobrarComision() {
        this.setSaldo(this.getSaldo() - COMISION);
        return COMISION;
    }
    
}
