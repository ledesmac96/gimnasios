package com.example.gimnasio.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Persona implements Parcelable {

    private int dni;
    private String fechaTurno;
    private int estado;

    public Persona(int dni, String fechaTurno, int estado) {
        this.dni = dni;
        this.fechaTurno = fechaTurno;
        this.estado = estado;
    }

    protected Persona(Parcel in) {
        dni = in.readInt();
        fechaTurno = in.readString();
        estado = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(dni);
        dest.writeString(fechaTurno);
        dest.writeInt(estado);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel in) {
            return new Persona(in);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };

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
