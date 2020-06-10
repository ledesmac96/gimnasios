package com.example.gimnasio.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Map;

public class Turno implements Parcelable {

    private String idTurno, horaInicio, horaFin, dia, mes, anio, fechaCreacion;
    private int duracionMinutos, capacidad, id, estado;
    private ArrayList<Persona> personas;

    public Turno(int id, int estado, String horaInicio, String horaFin, String dia, String mes, String anio,
                 String fechaCreacion, int duracionMinutos, int capacidad) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.fechaCreacion = fechaCreacion;
        this.duracionMinutos = duracionMinutos;
        this.capacidad = capacidad;
        this.personas = new ArrayList<>();
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    protected Turno(Parcel in) {
        idTurno = in.readString();
        horaInicio = in.readString();
        horaFin = in.readString();
        dia = in.readString();
        mes = in.readString();
        anio = in.readString();
        fechaCreacion = in.readString();
        duracionMinutos = in.readInt();
        capacidad = in.readInt();
        id = in.readInt();
        estado = in.readInt();
        int size = in.readInt();
        personas = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Persona persona = in.readParcelable(Persona.class.getClassLoader());
            personas.add(persona);
        }
    }

    public static final Creator<Turno> CREATOR = new Creator<Turno>() {
        @Override
        public Turno createFromParcel(Parcel in) {
            return new Turno(in);
        }

        @Override
        public Turno[] newArray(int size) {
            return new Turno[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }

    public String getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(String idTurno) {
        this.idTurno = idTurno;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public static Turno toMap(Map<String, Object> map) {
        if (map == null) return null;
        Turno turno = null;
        int capacidad = Integer.parseInt(String.valueOf(map.get("capacidad")));
        int duracion = Integer.parseInt(String.valueOf(map.get("capacidad")));
        int estado = Integer.parseInt(String.valueOf(map.get("estado")));
        String anio = String.valueOf(map.get("anio"));
        String dia = String.valueOf(map.get("dia"));
        String mes = String.valueOf(map.get("mes"));
        String fechaRegistro = String.valueOf(map.get("fechaCreado"));
        String horaFin = String.valueOf(map.get("horaFin"));
        String horaInicio = String.valueOf(map.get("horaInicio"));

        turno = new Turno(0, estado, horaInicio, horaFin, dia, mes, anio, fechaRegistro, duracion, capacidad);
        return turno;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idTurno);
        dest.writeString(horaInicio);
        dest.writeString(horaFin);
        dest.writeString(dia);
        dest.writeString(mes);
        dest.writeString(anio);
        dest.writeString(fechaCreacion);
        dest.writeInt(duracionMinutos);
        dest.writeInt(capacidad);
        dest.writeInt(id);
        dest.writeInt(estado);
        dest.writeInt(personas.size());
        for (Persona p : personas) {
            dest.writeParcelable(p, flags);
        }
    }
}
