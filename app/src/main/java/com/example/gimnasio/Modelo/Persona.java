package com.example.gimnasio.Modelo;

public class Persona {

    private int dni;
    private String fechaTurno;
    private int estado;

    public Persona(int dni, String fechaTurno, int estado) {
        this.dni = dni;
        this.fechaTurno = fechaTurno;
        this.estado = estado;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(String fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
