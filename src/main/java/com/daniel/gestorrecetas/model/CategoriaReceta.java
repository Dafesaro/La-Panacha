package com.daniel.gestorrecetas.model;

public class CategoriaReceta {
    private int id;
    private String nombre;

    // Constructor vacío
    public CategoriaReceta() {
    }

    // Constructor sin ID (para insertar)
    public CategoriaReceta(String nombre) {
        this.nombre = nombre;
    }

    // Constructor completo
    public CategoriaReceta(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters
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
        return "CategoriaReceta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

