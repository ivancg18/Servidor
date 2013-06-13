/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivan.recordatorio.servidor;

import java.util.ArrayList;

/**
 *
 * @author ivan
 */
public interface RecordatorioInterfaz {
    
  void insertar(String momento, String Asunto);
  
  ArrayList<String> getCitasDelDia(String cita);
  
  String getAsunto(String momento);
  
  void borrarCita(String momento);
  
  
  }
    

