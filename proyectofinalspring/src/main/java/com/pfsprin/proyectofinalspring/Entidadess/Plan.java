package com.pfsprin.proyectofinalspring.Entidadess;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "planes")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPlan tipo; // Enum TipoPlan debe ser definido.

    @Column(name = "cantidad_maxima", nullable = false)
    private Integer cantidadMaxima;

    @Column(name = "cantidad_actual", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer cantidadActual = 0;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean disponible = true;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoPlan getTipo() {
        return tipo;
    }

    public void setTipo(TipoPlan tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidadMaxima() {
        return cantidadMaxima;
    }

    public void setCantidadMaxima(Integer cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }

    public Integer getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Integer cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

}
