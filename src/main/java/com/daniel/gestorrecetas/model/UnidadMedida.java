package com.daniel.gestorrecetas.model;

public class UnidadMedida {
    private int id;
    private String nombre;

    // Constructor vacío
    public UnidadMedida() {
    }

    // Constructor sin ID (para inserciones)
    public UnidadMedida(String nombre) {
        this.nombre = nombre;
    }

    // Constructor completo
    public UnidadMedida(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "UnidadMedida{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}