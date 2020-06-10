package com.example.gimnasio.Modelo;

import java.util.Map;

public class Usuario {

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
        Integer dni = Integer.valueOf(String.valueOf(mapa.get("dni")));
        Integer estado = Integer.valueOf(String.valueOf(mapa.get("estado")));
        Integer tipo = Integer.valueOf(String.valueOf(mapa.get("tipoUsuario")));
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
