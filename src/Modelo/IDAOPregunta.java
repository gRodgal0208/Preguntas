/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import Clases.Pregunta;
import Clases.Usuario;

/**
 *
 * @author guill
 */
public interface IDAOPregunta {
    
    public ArrayList<Pregunta> getPregunta();
    
    public ArrayList<Pregunta> getPregunta(Integer n);
    
    public Boolean eliminarPregunta(String p);
    
    public Boolean agregarPregunta(Pregunta pregunta);
    
    public Boolean insertarUsuario(String usuario, String aciertos, String fallos);
    
    public ArrayList<Usuario> buscarUsuario(String ordenado, String nombre);
}
