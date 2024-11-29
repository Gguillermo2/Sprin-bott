package com.pfsprin.proyectofinalspring.Entidadess;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

    @Entity
    @Table(name = "reservas")
    public class Reserva {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Integer id;

        @ManyToOne
        @JoinColumn(name = "plan_id", nullable = false)
        private Plan plan;


        @ManyToOne
        @JoinColumn(name = "usuario_id", nullable = false)
        @JsonBackReference

        private Usuario usuario;

        private LocalDate fecha;

        private String tipoReserva;

        private Boolean pagada = false;

        // Getters y Setters
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Usuario getUsuario() {
            return usuario;
        }

        public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
        }

        public LocalDate getFecha() {
            return fecha;
        }

        public void setFecha(LocalDate fecha) {
            this.fecha = fecha;
        }

        public String getTipoReserva() {
            return tipoReserva;
        }

        public void setTipoReserva(String tipoReserva) {
            this.tipoReserva = tipoReserva;
        }

        public Boolean getPagada() {
            return pagada;
        }

        public void setPagada(Boolean pagada) {
            this.pagada = pagada;
        }

        public Plan getPlan() {
            return plan;
        }

        public void setPlan(Plan plan) {
            this.plan = plan;
        }
    }
