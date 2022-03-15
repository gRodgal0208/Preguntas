/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preguntas;


import Clases.Pregunta;
import Modelo.IDAOPregunta;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author Dam
 */
public class MockDAOPregunta implements IDAOPregunta{
    
    private static MockDAOPregunta dao = null;
    ArrayList<Pregunta> listaPreguntas;
    
    public MockDAOPregunta() {
          cargarBD();
    }
    
    public static MockDAOPregunta getInstance()
    {
        if(dao == null) dao = new MockDAOPregunta();
        
        return dao;
    }
    
    public void cargarBD() {
       listaPreguntas = new ArrayList<>();
     
        
        ArrayList<String> respuesta = new  ArrayList<>(Arrays.asList("/* */", "//", "%/","/** */"));
        listaPreguntas.add(new Pregunta("Menciona cual es la estructura de un comentario para aun sola línea", "//", respuesta));//
        
        respuesta = new  ArrayList<>(Arrays.asList("Main", "Principal", "Las dos primeras son correctas", "no tiene"));
        listaPreguntas.add(new Pregunta("¿Como se llama la clase principal de java?","Principal", respuesta));//Main
        
        respuesta = new  ArrayList<>(Arrays.asList("Para poder ser accesibles en otras clases", "Para acceder desde otros paquetes", "Para la clase base para la herencia", "Para ser una súper clase"));
        listaPreguntas.add(new Pregunta("¿Para que se utiliza la clase abstract?","Para la clase base para la herencia", respuesta));
        
        respuesta = new  ArrayList<>(Arrays.asList("Declaradas", "Directas", "Por herencia", "Importadas"));
        listaPreguntas.add(new Pregunta("¿Cómo deben estar las clases public para acceder a ellas desde otras clases?","Importadas", respuesta));
        
        respuesta = new  ArrayList<>(Arrays.asList("Interface", "implements", "Extends", "Object"));
        listaPreguntas.add(new Pregunta("¿Cómo se especifica las clases que tiene una súper clase?","Extends", respuesta));  
        
        respuesta = new  ArrayList<>(Arrays.asList("Es un concepto similar al de 'array'", "Es un tipo particular de variable", "Es un modelo o plantilla a partir de la cual creamos objetos", "Es una categoria de datos ordenada secuencialmente"));
        listaPreguntas.add(new Pregunta("¿Cuál es la descripción que crees que define mejor el concepto 'clase' en la programación orientada a objetos?","Es un modelo o plantilla a partir de la cual creamos objetos", respuesta));  
        
        respuesta = new  ArrayList<>(Arrays.asList("Sus cardinalidad y su tipo", "Sus atributos y sus métodos", "La forma en que establece comunicación e intercambia mensajes", "Su interfaz y los eventos asociados"));
        listaPreguntas.add(new Pregunta(" ¿Qué elementos crees que definen a un objeto?","Sus atributos y sus métodos", respuesta));  
        
        respuesta = new  ArrayList<>(Arrays.asList("public class Componente extends Producto", "public class Componente inherit Producto", "public class Componente implements Producto", "public class Componente belong to Producto"));
        listaPreguntas.add(new Pregunta("¿Qué código de los siguientes tiene que ver con la herencia?","public class Componente extends Producto", respuesta));  
        
        respuesta = new  ArrayList<>(Arrays.asList("Duplicar una clase", "Eliminar una clase", "Crear un objeto a partir de la clase", "Conectar dos clases entre sí"));
        listaPreguntas.add(new Pregunta("¿Qué significa instanciar una clase?","Crear un objeto a partir de la clase", respuesta));  
        
        respuesta = new  ArrayList<>(Arrays.asList("Una función utilizada para intercambiar valores", "Es el sobrenombre de la versión 1.3 del JDK", "Un framework específico para Android", "Una librería para construir interfaces gráficas"));
        listaPreguntas.add(new Pregunta("En Java, ¿a qué nos estamos refiriendo si hablamos de 'Swing'?","Una librería para construir interfaces gráficas", respuesta));
        
        
    }

  
    @Override
    public ArrayList<Pregunta> getPregunta() {
        
        return listaPreguntas;
    }

    @Override
    public ArrayList<Pregunta> getPregunta(Integer n) {
        
        ArrayList<Pregunta> preguntas = listaPreguntas.stream().limit(n).collect(Collectors.toCollection(() -> new ArrayList<Pregunta>()));
        
        return preguntas;
    }

    @Override
    public Boolean eliminarPregunta(String p) {
        if(p != "") return true;
       else
           return false;
    }

    @Override
    public Boolean agregarPregunta(Pregunta pregunta) {
       if(pregunta != null) return true;
       else
           return false;
    }
}
