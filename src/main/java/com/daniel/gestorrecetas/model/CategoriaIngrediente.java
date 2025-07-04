package com.daniel.gestorrecetas.model;

public class CategoriaIngrediente {
    private int id;
    private String nombre;

    // Constructor vacío
    public CategoriaIngrediente() {
    }

    // Constructor sin ID (para inserciones)
    public CategoriaIngrediente(String nombre) {
        this.nombre = nombre;
    }

    // Constructor completo
    public CategoriaIngrediente(int id, String nombre) {
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
        return "CategoriaIngrediente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}