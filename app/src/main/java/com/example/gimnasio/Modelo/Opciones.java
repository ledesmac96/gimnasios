package com.example.gimnasio.Modelo;

public class Opciones {

    private String titulo;
    private int icon, orientation;
    private int color, colorText = 0, sizeText =0;
    private int id, cantidad;

    public Opciones(int id, String titulo, int icon, int color, int cantidad) {
        this.titulo = titulo;
        this.icon = icon;
        this.color = color;
        this.id = id;
        this.cantidad = cantidad;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getSizeText() {
        return sizeText;
    }

    public void setSizeText(int sizeText) {
        this.sizeText = sizeText;
    }

    public int getColorText() {
        return colorText;
    }

    public void setColorText(int colorText) {
        this.colorText = colorText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
