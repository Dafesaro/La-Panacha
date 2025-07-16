package com.daniel.gestorrecetas.model;

public class Ingredientes {
    private int id;
    private String nombre;

    // Relaciones por ID (llaves foráneas)
    private int unidadMedidaId;
    private int categoriaIngredienteId;

    // Relaciones como objetos (para mostrar datos descriptivos)
    private UnidadMedida unidadMedida;
    private CategoriaIngrediente categoriaIngrediente;

    // Constructores
    public Ingredientes() {
    }

    public Ingredientes(String nombre, int unidadMedidaId, int categoriaIngredienteId) {
        this.nombre = nombre;
        this.unidadMedidaId = unidadMedidaId;
        this.categoriaIngredienteId = categoriaIngredienteId;
    }

    public Ingredientes(int id, String nombre, int unidadMedidaId, int categoriaIngredienteId) {
        this.id = id;
        this.nombre = nombre;
        this.unidadMedidaId = unidadMedidaId;
        this.categoriaIngredienteId = categoriaIngredienteId;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUnidadMedidaId() {
        return unidadMedidaId;
    }

    public void setUnidadMedidaId(int unidadMedidaId) {
        this.unidadMedidaId = unidadMedidaId;
    }

    public int getCategoriaIngredienteId() {
        return categoriaIngredienteId;
    }

    public void setCategoriaIngredienteId(int categoriaIngredienteId) {
        this.categoriaIngredienteId = categoriaIngredienteId;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public CategoriaIngrediente getCategoriaIngrediente() {
        return categoriaIngrediente;
    }

    public void setCategoriaIngrediente(CategoriaIngrediente categoriaIngrediente) {
        this.categoriaIngrediente = categoriaIngrediente;
    }

    // Método auxiliar para obtener el nombre de la unidad
    public String getUnidadMedidaNombre() {
        return unidadMedida != null ? unidadMedida.getNombre() : "Desconocida";
    }

    public String getCategoriaIngredienteNombre() {
        return categoriaIngrediente != null ? categoriaIngrediente.getNombre() : "Desconocida";
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", unidadMedida='" + getUnidadMedidaNombre() + '\'' +
                ", categoría='" + getCategoriaIngredienteNombre() + '\'' +
                '}';
    }
}