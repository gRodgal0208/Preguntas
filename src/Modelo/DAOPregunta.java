/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import Clases.Pregunta;
import Clases.Usuario;

/**
 *
 * @author guill
 */
public class DAOPregunta implements IDAOPregunta{
    
    private static DAOPregunta dao = null;
    ArrayList<Pregunta> listaPreguntas;
    ArrayList<Usuario> listaUsuarios;
    
    Connection con;
    Statement st;
    ResultSet rs;
    
    public DAOPregunta() 
    {
       cargarBD(); 
    }
    
    public void cargarBD()
    {
        listaPreguntas = new ArrayList<Pregunta>();
        
        String usuario = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/cuestionario";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");  
        } 
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        try {
            con = DriverManager.getConnection(url, usuario, password);
            
            st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM PREGUNTA");
            
            while(rs.next())
            {
                ArrayList<String> respuestas = new ArrayList<>(Arrays.asList(rs.getString("correcta"), rs.getString("alt1"), rs.getString("alt2"), rs.getString("alt3")));
                listaPreguntas.add(new Pregunta(rs.getString("texto"), rs.getString("correcta"), respuestas));
            }
            
            listaUsuarios = new ArrayList<Usuario>();
            
            rs = st.executeQuery("SELECT * FROM USUARIO");
                
            
                while(rs.next())
                {
                    listaUsuarios.add(new Usuario(rs.getString("NOMBRE"), rs.getString("ACIERTOS"), rs.getString("FALLOS")));
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOPregunta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DAOPregunta getInstance()
    {
        if(dao == null) dao = new DAOPregunta();
        
        return dao;
    }
    
    public ArrayList<Pregunta> getPregunta()
    {
        return listaPreguntas;
    }
    
    public ArrayList<Pregunta> getPregunta(Integer n)
    {
         ArrayList<Pregunta> preguntas = listaPreguntas.stream().limit(n).collect(Collectors.toCollection(() -> new ArrayList<Pregunta>()));
        
        return preguntas;
    }

    public Boolean eliminarPregunta(String p) {
        
        try {
            System.out.println("DELETE FROM PREGUNTA WHERE TEXTO = '" + p + "'");
            st.executeUpdate("DELETE FROM PREGUNTA WHERE TEXTO = '" + p + "'");
            cargarBD();
            
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public Boolean agregarPregunta(Pregunta pregunta)
    {
        //listaPregunta.add(pregunta);
        ArrayList<String> respuestas = new ArrayList<>();
        
        for(Integer i = 0; i < pregunta.getRespuestas().size(); i++)
        {
            if(!pregunta.getRespuestas().get(i).equals(pregunta.getPreguntaCorrecta()))
               respuestas.add(pregunta.getRespuestas().get(i));
        }
        
        try {
            st.executeUpdate("INSERT INTO PREGUNTA(TEXTO, CORRECTA, ALT1, ALT2, ALT3) VALUES('" + pregunta.getPregunta() + "', '" + pregunta.getPreguntaCorrecta() + "', '" + respuestas.get(0) + "', '" + respuestas.get(1) + "', '" + respuestas.get(2) + "')");
            cargarBD();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public Boolean insertarUsuario(String usuario, String aciertos, String fallos) 
    {
        try {
            st.executeUpdate("INSERT INTO USUARIO(NOMBRE, ACIERTOS, FALLOS) VALUES('" + usuario + "', '" + aciertos + "', '" + fallos + "')");
            cargarBD();
            return true;
        } catch (SQLException ex) {
            return false;
        } 
    }
    
    public ArrayList<Usuario> buscarUsuario(String ascDesc, String usuario)
    {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        StringBuilder str = new StringBuilder("SELECT * FROM USUARIO ");
        
        if(!usuario.equals(""))
        {
            str.append("WHERE NOMBRE= '" + usuario + "'");
        }
        if(!ascDesc.equals(""))
        {
           str.append(" ORDER BY ACIERTOS " + ascDesc); 
        }

        System.err.println(str.toString());
        
            try {
                ResultSet rs = st.executeQuery(str.toString());
                
                while(rs.next())
                {
                   usuarios.add(new Usuario(rs.getString("nombre"), rs.getString("aciertos"), rs.getString("fallos")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAOPregunta.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        return usuarios;
    }
}
