package com.example.listausuarios;

public class Usuarios {

    private String nombre;
    private int contador;

    public Usuarios(String nombre, int contador){
        this.nombre = nombre;
        this.contador = contador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    @Override
    public String toString() {
        return "Usuarios [nombre=" + nombre + ", contador=" + contador + "]";
    }

}
