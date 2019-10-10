package com.example.listausuarios;

public class Usuarios {

    private String nombre, apellido, correo;
    private int contador;

    public Usuarios(String nombre, String apellido, String correo, int contador){
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contador = contador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    @Override
    public String toString() {
        return "Usuarios [nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", contador=" + contador
                + "]";
    }

}
