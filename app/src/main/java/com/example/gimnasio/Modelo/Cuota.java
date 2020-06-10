package com.example.gimnasio.Modelo;

public class Cuota {

    private String idCuota, fechaPago;
    private String mes;
    private int dni;
    private float[] monto;

    public Cuota(String idCuota, String fechaPago, String mes, int dni, float[] monto) {
        this.idCuota = idCuota;
        this.fechaPago = fechaPago;
        this.mes = mes;
        this.dni = dni;
        this.monto = monto;
    }

    public String getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(String idCuota) {
        this.idCuota = idCuota;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public float[] getMonto() {
        return monto;
    }

    public void setMonto(float[] monto) {
        this.monto = monto;
    }
}
