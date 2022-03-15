/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.SpinnerNumberModel;
import Modelo.Controlador;
import Clases.Pregunta;
import Clases.Usuario;
import Vista.Ventana;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author guill
 */
public class ControladorC {
    
    private Ventana ventana;
    private Controlador controlador;
    private Pregunta p;
    private Integer contadorCorrectas, contadorFalladas;
    private Boolean penalizacion;
    private String usuario, ascDesc, ordNombre;
    
    public ControladorC(Ventana ventana)
    {
        this.ventana = ventana;
        this.controlador = new Controlador();
        
        this.contadorCorrectas = 0;
        this.contadorFalladas = 0;
        this.penalizacion = false;
        
        this.usuario = ""; 
        this.ascDesc = ""; 
        this.ordNombre = ""; 
    }
    
    
    public void rellenarTabla()
    {
        DefaultListModel listaModelo = new DefaultListModel();
        
        controlador.getPreguntas().stream().forEach(p -> listaModelo.addElement(p.getPregunta()));
        
        ventana.getListPreguntas().setModel(listaModelo);
    }
    
    public void spinner()
    {
        SpinnerNumberModel snm = new SpinnerNumberModel(3, 0, (int)controlador.getNumeroPreguntas(), 1);
        ventana.getSpinnerPreguntas().setModel(snm);
    }
    
    public void administrarPregunta()
    {
        this.p = controlador.cogerPregunta();
        
        ArrayList<String> respuestas = this.p.getRespuestas();
        
        ventana.getTxtEnunciado().setText(this.p.getPregunta());
        
            ventana.getRadio1().setText(respuestas.get(0));
            ventana.getRadio2().setText(respuestas.get(1));
            ventana.getRadio3().setText(respuestas.get(2));
            ventana.getRadio4().setText(respuestas.get(3));
    }
    
    public void elegirNPreguntas()
    {
        controlador.elegirNumeroPreguntas((Integer) ventana.getSpinnerPreguntas().getValue());
    }
    
    public void BorrarPregunta()
    {
        if(ventana.getListPreguntas().getSelectedIndex() != -1)
        {
            controlador.eliminarPregunta(ventana.getListPreguntas().getSelectedValue());
            controlador.recargarBD();
            this.rellenarTabla();
        }
    }
    
    public void siguiente()
    {
        Enumeration<AbstractButton> enu = ventana.getGrupoPreguntas().getElements();
        
        if(!controlador.getPreguntas().isEmpty())
        {
            while(enu.hasMoreElements())
            {
                AbstractButton radio = enu.nextElement();
                
                if(radio.isSelected() && radio.getText().equals(p.getPreguntaCorrecta()))
                {
                    contadorCorrectas++;
                }
            }
            ventana.getGrupoPreguntas().clearSelection();
            this.administrarPregunta();
        }
        else
        {
            ventana.getPanelPregunta().setVisible(false);
            ventana.getPanelResultado().setVisible(true);
            
            Integer fallos = (controlador.getNumeroPreguntas() - this.contadorCorrectas);
            
            if(this.penalizacion == false)
            {
                ventana.getTxtRespuesta().setText("Aciertos: " + this.contadorCorrectas + " ------- Falladas: " + fallos);
                
                controlador.insertarUsuario(usuario, this.contadorCorrectas.toString(), fallos.toString());
            }  
            else
            {
                Integer acierPenalizacion = (this.contadorCorrectas - (controlador.getNumeroPreguntas() - this.contadorCorrectas));
                
                ventana.getTxtRespuesta().setText("Aciertos: " + acierPenalizacion + " ------- Falladas: " + fallos);
                
                controlador.insertarUsuario(usuario, acierPenalizacion.toString(), fallos.toString());
            }
                
            
            
        }
    }
    
    public void agregarPregunta()
    {
        if(!ventana.getTxtPregunta().getText().equals("") &&  !ventana.getTxtResC().getText().equals("") && !ventana.getTxtResp2().getText().equals("") &&  !ventana.getTxtResp3().getText().equals("") && !ventana.getTxtResp4().getText().equals(""))
        {
            ArrayList<String> respuestaCreadas = new ArrayList<>(Arrays.asList(ventana.getTxtResC().getText(), ventana.getTxtResp2().getText(), ventana.getTxtResp3().getText(), ventana.getTxtResp4().getText()));

            Pregunta p = new Pregunta(ventana.getTxtPregunta().getText(), ventana.getTxtResC().getText(), respuestaCreadas);

            controlador.agregarPregunta(p);

            ventana.getTxtPregunta().setText("");
            ventana.getTxtResC().setText("");
            ventana.getTxtResp2().setText("");
            ventana.getTxtResp3().setText("");
            ventana.getTxtResp4().setText("");

            controlador.recargarBD();
            this.rellenarTabla();
        }
    }
    
    public void activarPenalizacion()
    {
        if(ventana.getCbPenalizacion().isSelected())
        {
            this.penalizacion = true;
        }
        else
            this.penalizacion = false;
    }
    
    public void cargartabla()
    {
        ArrayList<Usuario> lstUsuario = controlador.tablaUsuario(ascDesc, usuario);

		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("Nombre");

		modelo.addColumn("Aciertos");

		modelo.addColumn("Fallos");

		Object[] registroLeido = new Object[3];

		for (Usuario u : lstUsuario)
		{
                    registroLeido[0] = u.getNombre();

                    registroLeido[1] = u.getAcierto();

                    registroLeido[2] = u.getFallo();

			modelo.addRow(registroLeido);
		}

		ventana.getTablaUsuario().setModel(modelo);
    }
    
    
    public void ordNombre()
    {
        ordNombre = "nombre";
        cargartabla();
    }
    
    public void asc()
    {
       ascDesc = "asc";
       this.cargartabla(); 
    }
    
    public void desc()
    {
        ascDesc = "desc";
        this.cargartabla();
    }

    public void Loguear() {
        this.usuario = ventana.getTxtUsuario().getText();
    }
}
