/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author guill
 */
public class Usuario {
    
    private String nombre;
    private String acierto;
    private String fallo;

    public Usuario(String nombre, String acierto, String fallo) {
        this.nombre = nombre;
        this.acierto = acierto;
        this.fallo = fallo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAcierto() {
        return acierto;
    }

    public void setAcierto(String acierto) {
        this.acierto = acierto;
    }

    public String getFallo() {
        return fallo;
    }

    public void setFallo(String fallo) {
        this.fallo = fallo;
    }

    @Override
    public String toString() {
        return "nombre: " + nombre + ", acierto: " + acierto + ", fallo: " + fallo;
    }
    
    
}
