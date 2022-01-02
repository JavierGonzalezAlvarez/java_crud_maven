package org.javacrud.js.modelo;

import java.util.Date;

public class User {
    private Long id;
    private String nombre;
    private String apellido;
    private Date fechaRegistro;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido=" + apellido +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }

    public User() {
    }

    public User(Long id, String nombre, String apellido, Date fechaRegistro) {
        id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaRegistro = fechaRegistro;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}


