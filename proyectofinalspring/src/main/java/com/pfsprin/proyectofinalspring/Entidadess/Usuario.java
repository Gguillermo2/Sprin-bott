package com.pfsprin.proyectofinalspring.Entidadess;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @Column(unique = true, nullable = false)

    private String correoElectronico;

    private String contraseñaUsuario;

    private String numeroCelular;

    private Boolean esAdmin = false;

    private String imagen;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)


    private List<Pqr> pqrEntries;

    // Getters y Setters
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseñaUsuario() {
        return contraseñaUsuario;
    }

    public void setContraseñaUsuario(String contraseñaUsuario) {
        this.contraseñaUsuario = contraseñaUsuario;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public Boolean getEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(Boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Pqr> getPqrEntries() {
        return pqrEntries;
    }

    public void setPqrEntries(List<Pqr> pqrEntries) {
        this.pqrEntries = pqrEntries;
    }

    public String getImagen(){
        return imagen;
    }
    public  void setImagen(String imagen){
        this.imagen = imagen;
    }
}
