package Modelo;

import java.time.LocalDate;

public class Transaccion {
    private int id;
    private int cuentaId;
    private String tipo;
    private double monto;
    private LocalDate fecha;

    public Transaccion(int id, int cuentaId, String tipo, double monto, LocalDate fecha) {
        this.id = id;
        this.cuentaId = cuentaId;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public Transaccion() {
    }
    
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCuentaId() {
        return cuentaId;
    }
    public void setCuentaId(int cuentaId) {
        this.cuentaId = cuentaId;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
