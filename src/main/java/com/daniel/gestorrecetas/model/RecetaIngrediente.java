package com.daniel.gestorrecetas.model;

public class RecetaIngrediente {
    private int id;
    private int recetaId;
    private int ingredienteId;
    private double cantidad;

    // Relaciones como objetos para mostrar datos útiles
    private Ingredientes ingrediente;

    // Constructores
    public RecetaIngrediente() {
    }

    public RecetaIngrediente(int recetaId, int ingredienteId, double cantidad) {
        this.recetaId = recetaId;
        this.ingredienteId = ingredienteId;
        this.cantidad = cantidad;
    }

    public RecetaIngrediente(int id, int recetaId, int ingredienteId, double cantidad) {
        this.id = id;
        this.recetaId = recetaId;
        this.ingredienteId = ingredienteId;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(int recetaId) {
        this.recetaId = recetaId;
    }

    public int getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(int ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Ingredientes getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingredientes ingrediente) {
        this.ingrediente = ingrediente;
    }

    @Override
    public String toString() {
        String nombreIngrediente = (ingrediente != null) ? ingrediente.getNombre() : "Desconocido";
        String unidad = (ingrediente != null) ? ingrediente.getUnidadMedidaNombre() : "";
        return "- " + cantidad + " " + unidad + " de " + nombreIngrediente;
    }
}
