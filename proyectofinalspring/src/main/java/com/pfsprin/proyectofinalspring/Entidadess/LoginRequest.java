package com.pfsprin.proyectofinalspring.Entidadess;

public class LoginRequest {

    private String correoElectronico;
    private String contraseña;

    // Getters y setters


    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}