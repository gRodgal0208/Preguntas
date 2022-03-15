/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author guill
 */
public class Pregunta {
    
    String pregunta;
    String preguntaCorrecta;
    ArrayList<String> respuestas;
    
    public Pregunta(){}
    
    public Pregunta(String pregunta, String preguntaCorrecta, ArrayList<String> respuestas)
    {
        this.pregunta = pregunta;
        this.preguntaCorrecta = preguntaCorrecta;
        this.respuestas = respuestas;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getPreguntaCorrecta() {
        return preguntaCorrecta;
    }

    public void setPreguntaCorrecta(String preguntaCorrecta) {
        this.preguntaCorrecta = preguntaCorrecta;
    }

    public ArrayList<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<String> respuestas) {
        this.respuestas = respuestas;
    }
}
