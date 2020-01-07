package com.example.listausuarios;

import android.net.Uri;

public class Usuarios {

    private String nombre;
    private int contador;
    public Uri photoPath;

    public Usuarios(String nombre, int contador, Uri photoPath){
        this.nombre = nombre;
        this.contador = contador;
        this.photoPath = photoPath;
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

    public Uri getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(Uri photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "Usuarios [nombre=" + nombre + ", contador=" + contador + "]";
    }

}
