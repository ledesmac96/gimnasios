package com.example.gimnasio.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Noticia  {

    private String titulo, cuerpo, fechahora, urlImagen;
    private String id;

    public Noticia(String titulo, String cuerpo, String fechahora, String urlImagen, String id) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.fechahora = fechahora;
        this.urlImagen = urlImagen;
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getFechahora() {
        return fechahora;
    }

    public void setFechahora(String fechahora) {
        this.fechahora = fechahora;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

