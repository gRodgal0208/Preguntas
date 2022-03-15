/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preguntas;

import Clases.Pregunta;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author guill
 */
public class ControladorTest {
    
    public ControladorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of recargarBD method, of class Controlador.
     */
    @Test
    public void testRecargarBD() {
        System.out.println("recargarBD");
        TestableControlador instance = new TestableControlador();
        instance.recargarBD();
    }

    /**
     * Test of getPreguntas method, of class Controlador.
     */
    @Test
    public void testGetPreguntas() {
        System.out.println("getPreguntas");
        TestableControlador instance = new TestableControlador();
        ArrayList<Pregunta> result = instance.getPreguntas();
        assertNotNull(result);
    }

    /**
     * Test of getNumeroPreguntas method, of class Controlador.
     */
    @Test
    public void testGetNumeroPreguntas() {
        System.out.println("getNumeroPreguntas");
        TestableControlador instance = new TestableControlador();
        Integer expResult = 9;
        Integer result = instance.getNumeroPreguntas();
        assertEquals(expResult, result);
    }

    /**
     * Test of elegirNumeroPreguntas method, of class Controlador.
     */
    @Test
    public void testElegirNumeroPreguntas() {
        System.out.println("elegirNumeroPreguntas");
        Integer n = 3;
        TestableControlador instance = new TestableControlador();
        instance.elegirNumeroPreguntas(n);
    }

    /**
     * Test of cogerPregunta method, of class Controlador.
     */
    @Test
    public void testCogerPregunta() {
        System.out.println("cogerPregunta");
        TestableControlador instance = new TestableControlador();
        Pregunta result = instance.cogerPregunta();
        assertNotNull(result);
    }

    /**
     * Test of respuestasCorrectas method, of class Controlador.
     */
    @Test
    public void testRespuestasCorrectas() {
        System.out.println("respuestasCorrectas");
        TestableControlador instance = new TestableControlador();
        ArrayList<String> result = instance.respuestasCorrectas();
        assertNotNull(result);
    }

    /**
     * Test of eliminarPregunta method, of class Controlador.
     */
    @Test
    public void testEliminarPregunta() {
        System.out.println("eliminarPregunta");
        String pregunta = "hola";
        TestableControlador instance = new TestableControlador();
        Boolean expResult = true;
        Boolean result = instance.eliminarPregunta(pregunta);
        assertTrue(result);
    }

    /**
     * Test of agregarPregunta method, of class Controlador.
     */
    @Test
    public void testAgregarPregunta() {
        System.out.println("agregarPregunta");
        Pregunta p = new Pregunta();
        TestableControlador instance = new TestableControlador();
        Boolean expResult = null;
        Boolean result = instance.agregarPregunta(p);
        assertTrue(result);
    }
    
}
