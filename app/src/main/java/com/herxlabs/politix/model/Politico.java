package com.herxlabs.politix.model;


/**
 * Created by HX on 06/05/2017.
 */

public class Politico {
    private String nombre;
    private String apellido;
//    private String foto;
    private String estado;
//    private String partido;
//    private String email;
//    private String twitter;
//    private String facebook;
//    private String youtube;

    public Politico() {
    }

    public Politico(String nombre, String apellido, String estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return apellido + " " + nombre;
    }
}
