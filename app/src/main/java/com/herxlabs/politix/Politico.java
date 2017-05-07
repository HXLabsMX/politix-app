package com.herxlabs.politix;

/**
 * Created by HX on 06/05/2017.
 */

public class Politico {
    private String nombre, apellido, foto, estado, partido, email, twitter, facebook, youtube;

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

    public String getApellido() {
        return apellido;
    }

    public String getEstado() {
        return estado;
    }

}
