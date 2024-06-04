package Modelo;

import java.util.Date;

public class CuentaCorriente extends Cuenta {

    public CuentaCorriente() {
    }

    public CuentaCorriente(int cuentaId, int clienteId, String tipo, double saldo, String estado, Date fechaApertura) {
        super(cuentaId, clienteId, tipo, saldo, estado, fechaApertura);
    }
    
    @Override
    public double cobrarComision() {
        double comision = 10000.0;
        this.setSaldo(this.getSaldo() - comision);
        return comision;
    }
    
}
