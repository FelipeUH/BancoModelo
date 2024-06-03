package Modelo;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public abstract class Cuenta {
    private int cuentaId;
    private int clienteId;
    private String tipo;
    private double saldo;
    private String estado;
    private Date fechaApertura;
    protected static Map<String, Double> tipoCambio = new HashMap<>();
    
    static {
        tipoCambio.put("dolares", 3800.0);
        tipoCambio.put("euros", 4200.0);
        tipoCambio.put("pesos", 1.0);
    }

    public Cuenta() {
    }
    
    public Cuenta(int cuentaId, int clienteId, String tipo, double saldo, String estado, Date fechaApertura) {
        this.cuentaId = cuentaId;
        this.clienteId = clienteId;
        this.tipo = tipo;
        this.saldo = saldo;
        this.estado = estado;
        this.fechaApertura = fechaApertura;
    }
    
    public abstract void cobrarComision();
    
    public double convertirAPesos(double cantidad, String moneda) {
        return cantidad * tipoCambio.get(moneda);
    }
    
    public void ingresar(double cantidad, String moneda) {
        double cantidadEnPesos = convertirAPesos(cantidad, moneda);
        this.saldo += cantidadEnPesos;
    }
    
    public boolean retirar(double cantidad, String moneda) {
        if (this.saldo >= cantidad) {
            double cantidadEnPesos = convertirAPesos(cantidad, moneda);
            this.saldo -= cantidadEnPesos;
            return true;
        }
        return false;
    }
    
    public int getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(int cuentaId) {
        this.cuentaId = cuentaId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }
    
}