package com.example.gimnasio.Modelo;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

public class Usuario implements Parcelable {

    private int dni;
    private String nombre;
    private String apellido;
    private String fechaRegistro;
    private String password;
    private String reppass;
    private String telefono;
    private String mail;
    private int estado;
    private int tipoUsuario;

    public Usuario(Object dni) {

    }

    public Usuario(int dni, String nombre, String apellido, String fechaRegistro, String password,
                   String reppass, String telefono, String mail, int estado, int tipoUsuario) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaRegistro = fechaRegistro;
        this.password = password;
        this.reppass = reppass;
        this.telefono = telefono;
        this.mail = mail;
        this.estado = estado;
        this.tipoUsuario = tipoUsuario;
    }

    protected Usuario(Parcel in) {
        dni = in.readInt();
        nombre = in.readString();
        apellido = in.readString();
        fechaRegistro = in.readString();
        password = in.readString();
        reppass = in.readString();
        telefono = in.readString();
        mail = in.readString();
        estado = in.readInt();
        tipoUsuario = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(dni);
        dest.writeString(nombre);
        dest.writeString(apellido);
        dest.writeString(fechaRegistro);
        dest.writeString(password);
        dest.writeString(reppass);
        dest.writeString(telefono);
        dest.writeString(mail);
        dest.writeInt(estado);
        dest.writeInt(tipoUsuario);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getReppass() {
        return reppass;
    }

    public void setReppass(String reppass) {
        this.reppass = reppass;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public static Usuario toMap(Map<String, Object> mapa) {
        if (mapa == null)
            return null;
        Usuario usuario = null;
        int dni = Integer.parseInt(String.valueOf(mapa.get("dni")));
        int estado = Integer.parseInt(String.valueOf(mapa.get("estado")));
        int tipo = Integer.parseInt(String.valueOf(mapa.get("tipoUsuario")));
        String nombre = String.valueOf(mapa.get("nombre"));
        String apellido = String.valueOf(mapa.get("apellido"));
        String mail = String.valueOf(mapa.get("mail"));
        String fechaRegistro = String.valueOf(mapa.get("fechaRegistro"));
        String contra = String.valueOf(mapa.get("password"));
        String telefono = String.valueOf(mapa.get("telefono"));
        usuario = new Usuario(dni, nombre, apellido, fechaRegistro, contra, "", telefono,
                mail, estado, tipo);
        return usuario;
    }
}
