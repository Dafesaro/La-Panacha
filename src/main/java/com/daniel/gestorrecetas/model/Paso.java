package com.daniel.gestorrecetas.model;

public class Paso {
    private int id;
    private int recetaId;
    private String descripcion;
    private int orden;

    // Constructor vacío
    public Paso() {
    }

    // Constructor sin ID (para insertar nuevo paso)
    public Paso(int recetaId, String descripcion, int orden) {
        this.recetaId = recetaId;
        this.descripcion = descripcion;
        this.orden = orden;
    }

    // Constructor completo
    public Paso(int id, int recetaId, String descripcion, int orden) {
        this.id = id;
        this.recetaId = recetaId;
        this.descripcion = descripcion;
        this.orden = orden;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public int getRecetaId() {
        return recetaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRecetaId(int recetaId) {
        this.recetaId = recetaId;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    @Override
    public String toString() {
        return "Paso " + orden + ": " + descripcion;
    }
}