package com.daniel.gestorrecetas.model;

import java.util.List;

public class Recetas {
    private int id;
    private String nombre;
    private int categoriaRecetaId; // Relación con categoria_receta

    // Lista de ingredientes específicos para esta receta
    private List<RecetaIngrediente> ingredientes;

    // Lista de pasos para esta receta
    private List<Paso> pasos;

    // Constructor vacío (necesario para cuando uses Scanner o DAO)
    public Recetas() {
    }

    // Constructor sin ID (para crear nueva receta)
    public Recetas(String nombre, int categoriaRecetaId, List<RecetaIngrediente> ingredientes, List<Paso> pasos) {
        this.nombre = nombre;
        this.categoriaRecetaId = categoriaRecetaId;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
    }

    // Constructor con ID (para cuando se recupera de la base de datos)
    public Recetas(int id, String nombre, int categoriaRecetaId, List<RecetaIngrediente> ingredientes, List<Paso> pasos) {
        this.id = id;
        this.nombre = nombre;
        this.categoriaRecetaId = categoriaRecetaId;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCategoriaRecetaId() {
        return categoriaRecetaId;
    }

    public List<RecetaIngrediente> getIngredientes() {
        return ingredientes;
    }

    public List<Paso> getPasos() {
        return pasos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoriaRecetaId(int categoriaRecetaId) {
        this.categoriaRecetaId = categoriaRecetaId;
    }

    public void setIngredientes(List<RecetaIngrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setPasos(List<Paso> pasos) {
        this.pasos = pasos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("🍽️ Receta: ").append(nombre).append("\n");
        sb.append("Categoría ID: ").append(categoriaRecetaId).append("\n");

        sb.append("🧂 Ingredientes:\n");
        for (RecetaIngrediente ri : ingredientes) {
            sb.append(" - ").append(ri).append("\n");
        }

        sb.append("👨‍🍳 Pasos:\n");
        for (Paso paso : pasos) {
            sb.append(" ").append(paso.getOrden()).append(". ").append(paso.getDescripcion()).append("\n");
        }

        return sb.toString();
    }
}
