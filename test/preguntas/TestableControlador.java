/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preguntas;

import Clases.Pregunta;
import Modelo.IDAOPregunta;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author guill
 */
public class TestableControlador {
    
    private IDAOPregunta dao;
    private ArrayList<Pregunta> preguntas;
    
    private Integer numeroPregunta;
            
    public IDAOPregunta cargarDAO()
    {
        return MockDAOPregunta.getInstance();
    }
    
    public TestableControlador()
    {
        dao = cargarDAO();
        
        this.preguntas = dao.getPregunta();
        
        this.numeroPregunta = this.preguntas.size();
        
        Collections.shuffle(preguntas);
    }
    
    public void recargarBD()
    {
        this.preguntas = dao.getPregunta();
    }
    
    public ArrayList<Pregunta> getPreguntas()
    {
        return preguntas;
    }
    
    public Integer getNumeroPreguntas()
    {
        return this.numeroPregunta;
    }
    
    public void elegirNumeroPreguntas(Integer n)
    {
        this.preguntas = dao.getPregunta(n);
        
        this.numeroPregunta = this.preguntas.size();
    }
    
    public Pregunta cogerPregunta()
    {
        return preguntas.remove(0);
    }
    
    public ArrayList<String> respuestasCorrectas()
    {
        ArrayList<String> respuestas = new ArrayList<>();
        
        preguntas.stream().forEach(p -> respuestas.add(p.getPreguntaCorrecta()));
        
        return respuestas;
    }
    
    public Boolean eliminarPregunta(String pregunta)
    {
        //Pregunta preg = this.preguntas.stream().filter(p -> p.getPregunta().equals(pregunta)).collect(Collectors.toList()).get(0);
       
        return dao.eliminarPregunta(pregunta);
    }

    public Boolean agregarPregunta(Pregunta p) {
        return dao.agregarPregunta(p);
    }
}
