package com.example.gimnasio.Modelo;

import java.util.Map;

public class Cuota {

    private String idCuota, fechaPago;
    private String mes;
    private int dni, id;
    private float[] monto;

    public Cuota(int id, String idCuota, String fechaPago, String mes, int dni, float[] monto) {
        this.id = id;
        this.idCuota = idCuota;
        this.fechaPago = fechaPago;
        this.mes = mes;
        this.dni = dni;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public static Cuota toMap(Map<String, Object> map) {
        if (map == null) return null;
        Cuota cuota = null;

        int id = 0;
        int dni = Integer.parseInt(String.valueOf(map.get("dni")));
        String idCuota = String.valueOf(map.get(""));
        String anio = String.valueOf(map.get("anio"));
        String dia = String.valueOf(map.get("dia"));
        String mes = String.valueOf(map.get("mes"));
        String fechaPago = dia + "/" + mes + "/" + anio;
        float monto = Float.parseFloat(String.valueOf(map.get("monto")));

        //cuota = new Cuota(id, idCuota, fechaPago, mes, dni, monto);
        return cuota;
    }
}
