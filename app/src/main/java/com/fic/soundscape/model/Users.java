package com.fic.soundscape.model;

public class Users {
    private String
            nombre,
            email,
            contraseña;

    public String getemail() {
        return this.email;
    }

    public String getcontraseña() {
        return this.contraseña;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}